package com.webagesolutions.users;

import java.io.PrintStream;

public class RegularUser implements User {
	private String firstName;

	private String lastName;

	private String email;

	private String userName;

	private String password;

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#setEmail(java.lang.String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#setUserName(java.lang.String)
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RegularUser() {}
	
	/* Constructor using direct member access.
	public RegularUser(String lName, String eml, String uName, String pswd) {
		lastName = lName;
		email = eml;
		userName = uName;
		password = pswd;
	}
	*/
	
	/* Better idea to use setters. */
	public RegularUser(String lName, String eml, String uName, String pswd) {
		setLastName(lName);
		setEmail(eml);
		setUserName(uName);
		setPassword(pswd);
	}

	/* Original constructor
	public RegularUser(String fName, String lName, String eml, String uName,
			String pswd) {
		firstName = fName;
		lastName = lName;
		email = eml;
		userName = uName;
		password = pswd;
	}
	*/
	
	/* Constructor using this() */
	public RegularUser(String fName, String lName, String eml, String uName,
			String pswd) {
		this(lName, eml, uName, pswd);
		firstName = fName;
	}
	
	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#isAdministrator()
	 */
	public boolean isAdministrator() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.webagesolutions.lab03.User#printTo(java.io.PrintStream)
	 */
	public void printTo(PrintStream out) {
		out.printf("First Name: %s\n", getFirstName());
		out.printf("Last Name: %s\n", getLastName());
		out.printf("E-Mail: %s\n", getEmail());
		out.printf("User Name: %s\n", getUserName());
		out.printf("Password: %s\n", getPassword());
		if (isAdministrator()) {
			out.printf("This user is an Administrator\n");
		} else {
			out.printf("This user is a RegularUser\n");			
		}
	}
}
