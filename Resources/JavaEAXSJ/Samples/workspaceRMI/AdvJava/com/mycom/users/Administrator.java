package com.mycom.users;

public class Administrator extends RegularUser implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468570109075845889L;

	public Administrator() {}
	
	public Administrator(String fName, String lName, String eml, String uName,
			String pswd) {
		super(fName, lName, eml, uName, pswd);
	}

	public Administrator(String lName, String eml, String uName, String pswd) {
		super(lName, eml, uName, pswd);
	}

	@Override
	public boolean isAdministrator() {
		return true;
	}

}
