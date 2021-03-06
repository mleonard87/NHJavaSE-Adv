package com.mycom.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.mycom.users.RemoteUserStore;
import com.mycom.users.RemoteUserStoreUtils;
import com.mycom.users.User;

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

			/* Look up Susan Bryant. */
			System.out.println("Susan Bryant's user record is:");
			User bryant = store.getUser("bryants@mycom.com");
			if (bryant == null) {
				System.out.println("--Unknown--");
			} else {
				bryant.printTo(System.out);
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
