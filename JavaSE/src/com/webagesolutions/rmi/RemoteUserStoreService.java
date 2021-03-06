package com.webagesolutions.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.webagesolutions.users.RemoteUserStore;
import com.webagesolutions.users.User;
import com.webagesolutions.users.UserAddException;
import com.webagesolutions.users.UserStore;

public class RemoteUserStoreService extends UnicastRemoteObject implements RemoteUserStore
{
  /**
   * 
   */
  private static final long serialVersionUID = -6356283187896577764L;
  UserStore userStore = new InMemoryUserStore();
  
  public RemoteUserStoreService() throws RemoteException
  {
    super();
  }
  
  @Override
  public User getUser(String email) throws RemoteException
  {
    return userStore.getUser(email);
  }
  
  @Override
  public void putUser(User aUser, String verifyPassword)
      throws UserAddException, RemoteException
  {
    userStore.putUser(aUser, verifyPassword);    
  }
  
  @Override
  public Iterable<User> listAllUsers() throws RemoteException
  {
    return userStore.listAllUsers();
  }
  
  public static void main(String[] args)
  {
    try {
      RemoteUserStoreService service = new RemoteUserStoreService();
      Naming.bind("UserStore", service);
      System.out.println("UserStore service has been exported.");
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
