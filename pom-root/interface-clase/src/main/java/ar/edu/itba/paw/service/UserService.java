package ar.edu.itba.paw.service;

import ar.edu.itba.paw.User;

public interface UserService {
	/**
	 * Provides a User given an id
	 * @param id User id
	 * @return User with associated id or null if it doesn't exists.
	 */
	public User findById(int id);

	/**
	 * Creates a new user into the database.
	 * @param username Name of the new user
	 * @param password Password of the new user
	 * @return The created user
	 */
	public User create(String username, String password);

	public User findByName(String username);
}
