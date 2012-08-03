package com.acme.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {

	public static String modifiers2s(int m) {
		String s = "";
		if (Modifier.isAbstract(m)) {
			s += "abstract ";
		}
		if (Modifier.isPrivate(m)) {
			s += "private ";
		}
		if (Modifier.isProtected(m)) {
			s += "protected ";
		}
		if (Modifier.isPublic(m)) {
			s += "public  ";
		}
		if (Modifier.isStatic(m)) {
			s += "static ";
		}
		if (Modifier.isNative(m)) {
			s += "native ";
		}
		return s;
	}

	public static String class2s(Class<?> c) {
		String s = c.getName();
		c = c.getSuperclass();
		while (c != null) {
			s += "-->" + c.getName();
			c = c.getSuperclass();
		}
		return s;
	}

	public static String interfaces2s(Class<?> c) {
		String s = "";
		for (Class<?> iface : c.getInterfaces()) {
			if (!"".equals(s)) {
				s += ", ";
			}
			s += iface.getName();
		}
		return s;
	}

	public static String allInterfaces2s(Class<?> c) {
		String s = "";
		do {
			s += c.getName() + "-->";
			s += interfaces2s(c);
			c = c.getSuperclass();
			if (c != null) {
				s += "\n";
			}
		} while (c != null);
		return s;
	}

	public static void main(String[] args) {
		// BeanRecord r = new BeanRecord();
		// Class<?> clazz = r.getClass();
		// Class<?> clazz = BeanRecord.class;
		// Class<?> clazz = VJPanelBeanRecord.class;
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.acme.records.VJPanelBeanRecord");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		System.out.println("\nClass:");
		int cm = clazz.getModifiers();
		System.out.println(modifiers2s(cm) + clazz.getName());
		System.out.println(class2s(clazz));

		System.out.println("\nInterfaces:");
		System.out.println(interfaces2s(clazz));

		System.out.println("\nAll Interfaces:");
		System.out.println(allInterfaces2s(clazz));

		System.out.println("\nConstructors:");
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> c : constructors) {
			int mods = c.getModifiers();
			System.out.println(modifiers2s(mods) + c.getName() + " --[" + c);
		}

		System.out.println("\nFields:");
		Field[] fields = clazz.getFields();
		for (Field f : fields) {
			int mods = f.getModifiers();
			System.out.println(modifiers2s(mods) + f.getName() + " --[" + f);
		}

		System.out.println("\nMethods:");
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			int mods = m.getModifiers();
			System.out.println(modifiers2s(mods) + m.getName() + " --[" + m);
		}

	}
}
