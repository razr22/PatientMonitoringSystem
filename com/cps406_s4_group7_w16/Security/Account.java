package com.cps406_s4_group7_w16.Security;

/**
 * Class that defines an Account data type. Holds a username and password.
 * @author Paul Martins
 *
 */
public class Account {
	
	private String username;
	private String password;
	
	/**
	 * Class constructor that initiliazes and username and password using strings.
	 * @param user	String to be used for a username.
	 * @param pass	String to be used for a password.
	 */
	public Account(String user, String pass){
		this.setUsername(user);
		this.setPassword(pass);
	}
	
	/**
	 * Getter method for an accounts username.
	 * @return	String representing the accounts username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Setter method for an accounts username.
	 * @param username	String to represent the accounts username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter method for an accounts password.
	 * @return	String representing the accoutns password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Setter method for an accounts password
	 * @param password	String to represent the accounts password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
