package driver;

import java.util.Scanner;
import beans.User;
import beans.UserType;
import dao.UserDao;



public class Driver {

	public static void main(String[] args) {
		System.out.println("enter first name:");
		
		String input;
		Scanner sc = new Scanner(System.in);
		while (true) {
		  input = sc.nextLine();
		  
		  User newUser = new User();
		  
		  
		  String firstName = sc.nextLine();
		  newUser.setFirstName(firstName);
		  
		  System.out.println("enter last name:");
		  String lastName = sc.nextLine();
		  newUser.setLastName(lastName);
		  
		  System.out.println("create user name:");
		  String userName = sc.nextLine();
		  newUser.setUsername(userName);
		  
		  System.out.println("create password:");
		  String password = sc.nextLine();
		  newUser.setPassword(password);
		  
		  UserType ut = new UserType();
		  System.out.println("What is your role?");
		  String role = sc.nextLine();
		  ut.setRole(role);
		  newUser.setUserType(ut);
		  
		  UserDao ud = new UserDao();
		  
		  User registered = ud.addUser(newUser);
		  if (registered != null) {
			  System.out.println("Successfully registered.");
			  
		  }
		}
	}

}
