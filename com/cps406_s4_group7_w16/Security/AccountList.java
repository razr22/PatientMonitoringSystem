package com.cps406_s4_group7_w16.Security;

import java.util.ArrayList;

public class AccountList {
	
	private ArrayList<Account> accountList = new ArrayList<Account>();
	
	
	/**
	 * Adds an account to the master accountList
	 * @param account	Account to be added to the list.
	 */
	public void addEntry(Account account){
		
		accountList.add(account);
	}
	
	/**
	 * Removes an entry from the AccountList.
	 */
	public void removeEntry(){
		accountList.remove(accountList.size() -1);
	}
	
	/**
	 * Method that determines if a given username and password is in the accountList.
	 * @param username	String representing the username to test.
	 * @param password	String representing the password to test.
	 * @return	1 if the username and password are listed. 0 if they are not.
	 */
	public int isValidUsernameAndPassword(String username, String password){
		for(Account a : accountList){
			if(a.getUsername() == username && a.getPassword() == password){
				return 1;
			}
		}
		
		//invalid username and password 
		return 0;
	}
}
