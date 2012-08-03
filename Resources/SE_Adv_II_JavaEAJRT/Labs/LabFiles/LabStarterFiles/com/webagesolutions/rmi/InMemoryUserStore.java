package com.webagesolutions.rmi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webagesolutions.users.User;
import com.webagesolutions.users.UserAddException;
import com.webagesolutions.users.UserStore;

public class InMemoryUserStore implements UserStore {

	public void putUser(User aUser, String verifyPassword)
			throws UserAddException {

		/* TODO: Insert code to add user. */
		if (verifyPassword.equals(aUser.getPassword())) {
			if (index.containsKey(aUser.getEmail())) {
				userList.remove(getUser(aUser.getEmail()));
			}
			index.put(aUser.getEmail(), aUser);
			userList.add(aUser);
		} else {
			throw new UserAddException("Password incorrect.");
		}
		/* End student code. */
	}

	public Iterable<User> listAllUsers() {
		/* TODO: Begin student code. */
		return Collections.unmodifiableList(userList);
		/* End student code. */
	}

	public User getUser(String email) {
		/* TODO: Begin student code. */
		return index.get(email);
	}

	private List<User> userList = new ArrayList<User>();

	private Map<String, User> index = new HashMap<String, User>();
}
