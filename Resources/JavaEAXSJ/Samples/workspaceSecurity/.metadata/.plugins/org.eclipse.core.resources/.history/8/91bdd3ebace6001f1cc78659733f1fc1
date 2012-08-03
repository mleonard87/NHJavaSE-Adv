package com.mycom.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.mycom.users.InMemoryUserStore;
import com.mycom.users.RemoteUserStore;
import com.mycom.users.User;
import com.mycom.users.UserAddException;
import com.mycom.users.UserStore;

public class RemoteUserStoreService extends UnicastRemoteObject implements
		RemoteUserStore {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9134276742369334068L;
	UserStore userStore = new InMemoryUserStore();

	protected RemoteUserStoreService() throws RemoteException {
		super();
	}

	@Override
	public void putUser(User aUser, String verifyPassword)
			throws UserAddException, RemoteException {
		// TODO Auto-generated method stub
		userStore.putUser(aUser, verifyPassword);

	}

	@Override
	public Iterable<User> listAllUsers() throws RemoteException {
		// TODO Auto-generated method stub
		return userStore.listAllUsers();
	}

	@Override
	public User getUser(String email) throws RemoteException {
		// TODO Auto-generated method stub
		return userStore.getUser(email);
	}
public static void main(String args[]){
	try {
		RemoteUserStoreService service = new RemoteUserStoreService();
		Naming.bind("UserStore", service);
		System.out.println("UserStore service is available!");
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AlreadyBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
