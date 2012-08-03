package com.mycom.users;

import java.rmi.RemoteException;

public class RemoteUserStoreUtils {

	public static void populate(RemoteUserStore store) throws RemoteException {
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
