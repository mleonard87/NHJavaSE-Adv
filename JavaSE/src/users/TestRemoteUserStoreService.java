package com.webagesolutions.users;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestRemoteUserStoreService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Create an InMemoryUserStore. */
		try {
			RemoteUserStore store = (RemoteUserStore) Naming
					.lookup("//localhost/UserStore");
			System.out.printf("Got the remote user store.\n\n");
			/* Load some default users. */
			System.out.printf("Populating the remote user store...");
			RemoteUserStoreUtils.populate(store);
			System.out.printf("done\n\n");
			
			/* Print them out. */
			for (User user : store.listAllUsers()) {
				user.printTo(System.out);
				System.out.println();
			}

			/* Look up Jim Waldo. */
			System.out.println("Jim Waldo's user record is:");
			User waldo = store.getUser("waldoj@sun.com");
			if (waldo == null) {
				System.out.println("--Unknown--");
			} else {
				waldo.printTo(System.out);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
