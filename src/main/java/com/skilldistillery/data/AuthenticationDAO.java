package com.skilldistillery.data;

public interface AuthenticationDAO {
	public User createUser(User user);
	public boolean usernameIsUnique(String username);
	public User validUsername(String username);
	public boolean validPassword(User user);
}
