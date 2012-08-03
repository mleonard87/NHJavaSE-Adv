package com.webagesolutions.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Reflection
{
  public static List<Class<?>> allTypes(Class<?> c)
  {
    List<Class<?>> list = new ArrayList<Class<?>>();
    do {
      list.add(c);
      list.addAll(Arrays.asList(c.getInterfaces()));
      c = c.getSuperclass();
    } while (c != null);
    return list;
  }

  private static Method findMethod(int depth, Class<?> targetClass,
      String methodName, Class<?>[] types)
  {
    try {
      return targetClass.getMethod(methodName, types);
    } catch (Exception e) {
    }
    Class<?>[] nestedTypes = Arrays.copyOf(types, types.length);
    for (int i = depth; i < types.length; i++) {
      for (Class<?> t : allTypes(types[i])) {
        nestedTypes[i] = t;
        if (i == types.length - 1) {
          try {
            return targetClass.getMethod(methodName, nestedTypes);
          } catch (Exception e) {
          }
        } else {
          Method m = findMethod(depth + 1, targetClass, methodName, nestedTypes);
          if (m != null) {
            return m;
          }
        }
      }
    }
    if (depth == 0) {
      throw new RuntimeException("problem finding " + methodName + " on "
          + targetClass.getName());
    } else {
      return null;
    }
  }

  public static Object invoke(Object target, String methodName, Object... args)
  {
    Class<?> targetClass = target.getClass();
    if (target instanceof Class) {
      targetClass = (Class<?>) target;
    }
    Class<?>[] types = new Class<?>[args.length];
    int i = 0;
    for (Object arg : args) {
      types[i++] = arg.getClass();
    }
    try {
      Method m = findMethod(0, targetClass, methodName, types);
      return m.invoke(target, args);
    } catch (Exception e) {
      throw new RuntimeException("problem invoking " + methodName + " on "
          + targetClass.getName(), e);
    }
  }

  public static Object invoke(Class<?> targetClass, String methodName,
      Object... args)
  {
    try {
      Method m = targetClass.getMethod(methodName);
      return m.invoke(targetClass, args);
    } catch (Exception e) {
      throw new RuntimeException("problem invoking " + methodName + " on "
          + targetClass.getName(), e);
    }
  }

  public static Object invoke(String className, String methodName,
      Object... args)
  {
    try {
      Class<?> c = Class.forName(className);
      return invoke(c, methodName, args);
    } catch (Exception e) {
      throw new RuntimeException("problem finding class " + className, e);
    }
  }

  private static Constructor<?> findConstructor(int depth,
      Class<?> targetClass, Class<?>[] types)
  {
    try {
      return targetClass.getConstructor(types);
    } catch (Exception e) {
    }
    Class<?>[] nestedTypes = Arrays.copyOf(types, types.length);
    for (int i = depth; i < types.length; i++) {
      for (Class<?> t : allTypes(types[i])) {
        nestedTypes[i] = t;
        if (i == types.length - 1) {
          try {
            System.out.println(t);
            return targetClass.getConstructor(nestedTypes);
          } catch (Exception e) {
          }
        } else {
          Constructor<?> c = findConstructor(depth + 1, targetClass,
              nestedTypes);
          if (c != null) {
            return c;
          }
        }
      }
    }
    if (depth == 0) {
      throw new RuntimeException("problem finding constructor for "
          + targetClass.getName());
    } else {
      return null;
    }
  }

  public static Object construct(Class<?> targetClass, Object... args)
  {
    Class<?>[] types = new Class<?>[args.length];
    int i = 0;
    for (Object arg : args) {
      types[i++] = arg.getClass();
    }
    try {
      Constructor<?> c = findConstructor(0, targetClass, types);
      return c.newInstance(args);
    } catch (Exception e) {
      throw new RuntimeException("problem constructing class "
          + targetClass.getName(), e);
    }
  }

  public static Object construct(String className, Object... args)
  {
    try {
      Class<?> c = Class.forName(className);
      return construct(c, args);
    } catch (Exception e) {
      throw new RuntimeException("problem finding class " + className, e);
    }
  }

  public static void main(String[] args)
  {
    // System.out.println(invoke(java.lang.System.class, "getProperties"));
    // System.out.println(invoke("java.lang.System", "getProperties"));
    // System.out.println(construct(BeanRecord.class));
    // System.out.println(construct("com.webagesolutions.records.BeanRecord"));
    // Construct with zero arg constructor.
    Object r = construct("com.webagesolutions.records.BeanRecord");
    // Construct with argument constructor.
    Object r1 = construct("com.webagesolutions.records.BeanRecord",
        "ludwig@acme.com", "Ludwig van Beethoven", "dude", "haydn");
    // Invoke and instance method.
    System.out.println(invoke(r, "getEmail"));
    System.out.println(invoke(r1, "getEmail"));

    // System.out.println(allTypes(VJPanelBean.class));
    // Invoke an instance method with arguments
    invoke(r1, "setEmail", "dude@acme.com");
    System.out.println(invoke(r1, "getEmail"));

    // Invoke a method with dynamically typed arguments
    System.out.println(invoke(r1, "addPropertyChangeListener",
        new PropertyChangeListener()
        {
          @Override
          public void propertyChange(PropertyChangeEvent evt)
          {
            System.out.println("anon property change: " + evt);
          }
        }));
    // Verify the property change
    invoke(r1, "setPassword", "hmmm");

    Object o = construct("org.apache.derby.jdbc.ClientConnectionPoolDataSource40");
    Object v1 = construct("com.webagesolutions.records.VJPanelBean", o);
    Object v2 = construct("com.webagesolutions.records.VJPanelBean", r);

    JFrame f = new JFrame();
    f.setContentPane((JPanel) v1);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);

    f = new JFrame();
    f.setContentPane((JPanel) v2);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);

    try {
      // Some arrays
      Object records = Array.newInstance(
          Class.forName("com.webagesolutions.records.BeanRecord"), 10);

      for (int i = 0; i < Array.getLength(records); i++) {
        String userId = "ludwig" + i;
        String email = userId + "@acme.com";
        Array.set(
            records,
            i,
            construct("com.webagesolutions.records.BeanRecord", email,
                "Ludwig van Beethoven", userId, "haydn"));
      }
      
      for (int i = 0; i < Array.getLength(records); i++) {
        System.out.println(Array.get(records, i));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
