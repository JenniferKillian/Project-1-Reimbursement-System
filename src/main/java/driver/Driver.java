package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.ReimType;
import beans.Reimbursement;
import beans.User;
import beans.UserType;
import dao.ReimbursementDao;
import dao.UserDao;



public class Driver {

	public static void main(String[] args) {
		//User u = new User();
		//u.setId(4);
		//ReimbursementDao rd = new ReimbursementDao();
		//Reimbursement r = new Reimbursement();
		//r.setReimbId(1);
		//r.setResolver(u);
		//r.setStatus(1);
		//rd.removeReimbursement(r);
		/*
		List<Reimbursement> rr = rd.getReimbursementsByUser(u);
		for (Reimbursement r : rr) {
			System.out.println(r.getSubmitted());
			System.out.println(r.getAuthor().getPassword());
		}
		//System.out.println(r.getSubmitted());
		//System.out.println(r.getAuthor().getPassword());
		*/
		UserDao ud = new UserDao();
		User u = ud.getUser(5);
		System.out.println(u.getLastName());
		
		Reimbursement r = new Reimbursement();
		r.setAmount(1085.98);
		r.setDescription("LAP TOP");
		r.setAuthor(u);
		ReimType t = new ReimType();
		t.setId(4);
		r.setType(t);
		ReimbursementDao rd = new ReimbursementDao();
		Reimbursement added = new Reimbursement();
		added = rd.addReimbursement(r);
		if (added != null) {
			System.out.println(added.getReimbId());
		}
		/*
		List<User> ulist = new ArrayList<User>();
		UserDao ud = new UserDao();
		ulist = ud.getAllUsers();
		for (User u : ulist) {
			System.out.println(u.getFirstName());
		}
		/*
		User u = new User();
		u.setId(6);
		UserDao ud = new UserDao();
		ud.removeUser(u);
		
		User u = new User();
		u.setId(6);
		u.setUsername("jlk");
		u.setPassword("p");
		u.setFirstName("jessie");
		u.setLastName("k");
		u.setEmail("email");
		UserType ut = new UserType();
		ut.setId(1);
		u.setUserType(ut);
		UserDao ud = new UserDao();
		ud.updateUser(u);
		*/
		/*
		System.out.println("enter first name:");
		User newUser = new User();
		Scanner sc = new Scanner(System.in);
		while (true) {
		  
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
		  System.out.println("Are you an employee? Enter 1 for yes, 2 for no.");
		  String role = sc.nextLine();
		  ut.setId(Integer.valueOf(role));
		  newUser.setUserType(ut);
		  
		  UserDao ud = new UserDao();
		  
		  User registered = ud.addUser(newUser);
		  if (registered != null) {
			  System.out.println("Successfully registered.");
			  System.out.println(registered.getId());
		  }
		  
		//sc.close();
		}
		*/
	}

}
