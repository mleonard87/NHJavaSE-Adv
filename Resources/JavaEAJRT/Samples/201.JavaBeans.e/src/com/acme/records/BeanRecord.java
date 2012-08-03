package com.acme.records;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class BeanRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String userId;
	private String password;

	public BeanRecord() {
	}

	public BeanRecord(String email, String name, String userId, String password) {
		this();
		this.email = email;
		this.name = name;
		this.userId = userId;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		Object old = this.email;
		this.email = email;
		changeSupport.firePropertyChange("email", old, this.email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Object old = this.name;
		this.name = name;
		changeSupport.firePropertyChange("name", old, this.name);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		Object old = this.userId;
		this.userId = userId;
		changeSupport.firePropertyChange("userId", old, this.userId);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Object old = this.password;
		this.password = password;
		changeSupport.firePropertyChange("password", old, this.password);
	}

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public String toString() {
		return "BeanRecord" + this.hashCode() + " [email=" + email + ", name="
				+ name + ", userId=" + userId + ", password=" + password + "]";
	}
}
