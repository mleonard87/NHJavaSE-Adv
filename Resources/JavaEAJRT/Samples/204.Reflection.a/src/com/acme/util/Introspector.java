package com.acme.util;

import java.awt.HeadlessException;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.PropertyDescriptor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Introspector {

	public static Object getPropertry(Object target, String propertyName) {
		try {
			BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(target
					.getClass());
			for (PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
				if (propertyName.equals(d.getName())) {
					System.out.println("introspector invoking "
							+ d.getReadMethod().getName());
					return d.getReadMethod().invoke(target);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
		// throw new RuntimeException("no " + propertyName +
		// " property found on "
		// + target.getClass().getName());
	}

	public static Object setPropertry(Object target, String propertyName,
			Object value) {
		try {
			BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(target
					.getClass());
			for (PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
				if (propertyName.equals(d.getName())) {
					System.out.println("introspector invoking "
							+ d.getWriteMethod().getName());
					return d.getWriteMethod().invoke(target, value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
		// throw new RuntimeException("no " + propertyName +
		// " property found on "
		// + target.getClass().getName());
	}

	public static void addListener(Object target, Object listener) {
		try {
			BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(target
					.getClass());
			for (EventSetDescriptor d : beanInfo.getEventSetDescriptors()) {
				Class<?> c = d.getListenerType();
				if (c.isAssignableFrom(listener.getClass())) {
					System.out.println("introspector registering for "
							+ d.getName());
					d.getAddListenerMethod().invoke(target, listener);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// throw new RuntimeException(
		// "no appropriate registration method found on "
		// + target.getClass().getName());
	}

	public static void removeListener(Object target, Object listener) {
		try {
			BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(target
					.getClass());
			for (EventSetDescriptor d : beanInfo.getEventSetDescriptors()) {
				Class<?> c = d.getListenerType();
				if (c.isAssignableFrom(listener.getClass())) {
					System.out.println("introspector deregistering from "
							+ d.getName());
					d.getRemoveListenerMethod().invoke(target, listener);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// throw new RuntimeException(
		// "no appropriate deregistration method found on "
		// + target.getClass().getName());
	}

	public static void main(String[] args) {
		try {
			Object o1 = Class.forName(
					"org.apache.derby.jdbc.ClientConnectionPoolDataSource40")
					.newInstance();
			Object o2 = Class.forName("com.acme.records.BeanRecord")
					.newInstance();
			Introspector.setPropertry(o2, "email", "ludwig@acme.com");
			Introspector.setPropertry(o2, "name", "Ludwig van Beethoven");
			Introspector.setPropertry(o2, "userId", "ludwig");
			Introspector.setPropertry(o2, "password", "furelise");
			Object v1 = Class.forName("com.acme.records.VJPanelBean")
					.newInstance();
			Introspector.setPropertry(v1, "model", o1);
			Object v2 = Class.forName("com.acme.records.VJPanelBean")
					.newInstance();
			Introspector.setPropertry(v2, "model", o2);

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
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
