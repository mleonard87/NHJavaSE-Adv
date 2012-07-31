package com.webagesolutions.users;

import com.webagesolutions.collections.InMemoryUserStore;

public class TestInMemoryUserStore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Create an InMemoryUserStore. */
		UserStore store=new InMemoryUserStore();
		
		/* Load some default users. */
		UserStoreUtils.populate(store);
		
		/* Print them out. */
		for (User user: store.listAllUsers()) {
			user.printTo(System.out);
			System.out.println();
		}
		
		/* Look up Jim Waldo. */
		System.out.println("Jim Waldo's user record is:");
		User waldo=store.getUser("waldoj@sun.com");
		if (waldo==null) {
			System.out.println("--Unknown--");
		} else {
			waldo.printTo(System.out);
		}
	}

}
