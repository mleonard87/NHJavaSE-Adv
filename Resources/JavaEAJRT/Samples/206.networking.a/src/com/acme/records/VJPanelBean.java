package com.acme.records;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class VJPanelBean extends JPanel {
	private static final long serialVersionUID = 1L;
	// private static final ResourceBundle bundle = ResourceBundle
	// .getBundle("com.acme.records.messages");
	private Controller controller;
	private Map<String, Document> documents = new HashMap<String, Document>();
	private Map<Document, String> documentNames = new HashMap<Document, String>();
	private Object model;

	private class Controller implements DocumentListener,
			PropertyChangeListener {
		private Thread guiThread = null;

		@Override
		public void insertUpdate(DocumentEvent e) {
			update(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			update(e);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			update(e);
		}

		private void update(DocumentEvent e) {
			this.guiThread = Thread.currentThread();
			Document document = e.getDocument();
			try {
				// retrieve view
				String newValue = document.getText(0, document.getLength());
				// update model
				com.acme.util.Introspector.setPropertry(model,
						documentNames.get(document), newValue);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.guiThread = null;
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Document document = documents.get(evt.getPropertyName());
			if (guiThread != Thread.currentThread()) {
				String newValue = "unknown";
				// retrieve model
				newValue = com.acme.util.Introspector.getPropertry(model,
						evt.getPropertyName()).toString();
				try {
					// update view
					((AbstractDocument) document).replace(0,
							document.getLength(), newValue, null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public VJPanelBean() {
		this.setLayout(new GridBagLayout());
	}

	public VJPanelBean(Object model) {
		this();
		setModel(model);
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		try {
			if (controller == null) {
				controller = new Controller();
			}
			if (this.model != null) {
				com.acme.util.Introspector.removeListener(model, controller);
			}
			this.model = model;
			com.acme.util.Introspector.addListener(model, controller);
			for (Document document : this.documentNames.keySet()) {
				document.removeDocumentListener(controller);
			}
			documents.clear();
			documentNames.clear();
			this.removeAll();
			GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0, 0,
					GridBagConstraints.EAST, 10, new Insets(4, 4, 4, 4), 10, 10);
			BeanInfo beanInfo = Introspector.getBeanInfo(model.getClass());
			for (PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
				if (d.getPropertyType() == String.class) {
					try {
						c.gridx = 0;
						String name = d.getName();
						String value = (String) d.getReadMethod().invoke(model,
								(Object[]) null);
						JTextField t = new JTextField(20);
						// String labelValue = bundle
						// .getString("VJPanelBeanRecord." + d.getName())
						// + ": ";
						String labelValue = d.getName() + ": ";
						this.add(new JLabel(labelValue), c);
						c.gridx = 1;
						this.add(t, c);
						documents.put(name, t.getDocument());
						documentNames.put(t.getDocument(), name);
						((AbstractDocument) t.getDocument()).replace(0, t
								.getDocument().getLength(), value, null);
						t.getDocument().addDocumentListener(controller);
						c.gridy++;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
