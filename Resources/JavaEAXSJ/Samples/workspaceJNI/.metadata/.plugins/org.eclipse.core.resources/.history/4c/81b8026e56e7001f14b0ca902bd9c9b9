package com.mycom.users;

public class UserStoreUtils {

	public static void populate(UserStore store) {
		try {
			store.putUser(new Administrator("Susan", "Bryant", "bryants@mycom.com", 
					"bryants", "note"), "note");
			store.putUser(new RegularUser("John", "Wilson", "wilsonj@mycom.com", 
					"wilsonj", "on"), "on");
			store.putUser(new RegularUser("Scott", "Smith", "smiths@mycom.com", 
					"smiths", "distributed"), "distributed");
			store.putUser(new RegularUser("Geoff", "Gonzalez", "gonzalezg@mycom.com", 
					"gonzalezg", "computing"), "computing");
		} catch (UserAddException e) {
			e.printStackTrace();
		} 
	}
}
