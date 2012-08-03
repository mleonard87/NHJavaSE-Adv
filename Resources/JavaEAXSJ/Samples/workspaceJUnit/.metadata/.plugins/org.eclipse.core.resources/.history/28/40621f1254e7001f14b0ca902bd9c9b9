package com.mycom.security;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.sun.security.auth.callback.DialogCallbackHandler;

public class Login {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginContext lc;
		try {
			lc = new LoginContext("Login", new DialogCallbackHandler());
			lc.login();
			System.out.printf("User authenticated as %s \n", lc.getSubject());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
