package com.cps406_s4_group7_w16.Security;

public class AccountTester {

	public static void main(String[] args) {
		
		AccountList list = new AccountList();
		
		//testing if username and password combo is valid.
		if(list.isValidUsernameAndPassword("admin", "password") == true){
			System.out.println("Valid User!");
		}

	}
}
