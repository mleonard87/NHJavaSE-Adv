package com.mycom.users;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteUserStore extends Remote {
	public void putUser(User aUser, String verifyPassword)
			throws UserAddException, RemoteException;

	public Iterable<User> listAllUsers() throws RemoteException;

	public User getUser(String email) throws RemoteException;
}
