package dao;


import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import beans.Reimbursement;
import beans.User;
import beans.UserType;



/**
 * UserDAO reads/writes to a relational database
 */

public class UserDao {
	
	private String url;
	private String username;
	private String password;
	
	public UserDao() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		Properties p = new Properties();
		try {
		p.load(is);
		this.url = (String) p.getProperty("url");
		this.username = (String) p.getProperty("username");
		this.password = (String) p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public User addUser(User user) {
		try {
			
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "INSERT INTO ers_users (user_username, user_password, user_first_name, user_last_name, user_email, user_role_id) values(?,?,?,?,?,?)"; // RETURNING id INTO uId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			if (user.getUserType() == null) {
				//ps.setInt(6, 0);
			} else {
				ps.setInt(6, user.getUserType().getId());
			}
			
			
			ps.execute();
			ps.close();
			
			Connection conn2 = DriverManager.getConnection(this.url, this.username, this.password);
			String sql2 = "SELECT * from ers_users WHERE user_username = '" + user.getUsername() + "';";
			Statement s = conn2.createStatement();
			ResultSet rs = s.executeQuery(sql2);
			while(rs.next()) {
				user.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}

	public User getUser(Integer userId) {
		User u = new User();
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_users WHERE user_id = " + userId;
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				if(rs.getString(6) != null) {
					u.setEmail(rs.getString(6));
				}
				UserType uType = new UserType();
				uType.setId(rs.getInt(7));
				u.setUserType(uType);
			}
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		ReimbursementDao rd = new ReimbursementDao();
		List<Reimbursement> rl = new ArrayList<Reimbursement>();
		rl = rd.getReimbursementsByUser(u);
		u.setreimbs(rl);
		return u;
	}

	public User getUser(String uname, String pass) {
		System.out.println("in userdao getUser");
		User u = new User();
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_users WHERE user_username = '" + uname + "' AND user_password = '" + pass +"';";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				if(rs.getString(6) != null) {
					u.setEmail(rs.getString(6));
				}
				UserType uType = new UserType();
				uType.setId(rs.getInt(7));
				u.setUserType(uType);
			}
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		// get reimbursements
		System.out.println("user id = " + u.getId());
		return u;
	}

	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_users";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				if(rs.getString(6) != null) {
					u.setEmail(rs.getString(6));
				}
				UserType uType = new UserType();
				uType.setId(rs.getInt(7));
				u.setUserType(uType);
				
				allUsers.add(u);
			}
			conn.close();
			//get reimbursements
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allUsers;
	}

	public User updateUser(User user) {
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "UPDATE ers_users set" +
			" user_username = '" + user.getUsername() +
			"', user_password = '" + user.getPassword() +
			"', user_first_name = '" + user.getFirstName() +
			"', user_last_name = '" + user.getLastName() +
			"', user_email = '" + user.getEmail() +
			"', user_role_id = " + user.getUserType().getId() +
			" WHERE user_id = " + user.getId() + ";";
			Statement s = conn.createStatement();
			s.execute(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean removeUser(User u) {
		try {
			Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			String sql = "DELETE FROM ers_users WHERE user_id = " + u.getId() + ";";
			Statement s = conn.createStatement();
			s.execute(sql);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
