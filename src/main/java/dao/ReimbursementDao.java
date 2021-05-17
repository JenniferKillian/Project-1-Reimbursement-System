package dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import beans.Reimbursement;
import beans.User;
import beans.UserType;
import beans.ReimType;



/**
 * ReimbursementDao reads/writes to a database
 */
public class ReimbursementDao {
	
	private String url;
	private String username;
	private String password;
	
	public ReimbursementDao() {
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

	public Reimbursement addReimbursement(Reimbursement a) {
		
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id) values(?,?,?,?,?) RETURNING reimb_id;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, a.getAmount());
			ps.setString(2, a.getDescription());
			ps.setInt(3, a.getAuthor().getId());
			ps.setInt(4, 2); // 2 correlates to status "pending"
			ps.setInt(5, a.getType().getId());
			
			ps.execute();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int rId = rs.getInt(1);
			
			//while(rs.next()) {
				//int rId = rs.getInt(1);
			//}
			
			a.setReimbId(rId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}

	public Reimbursement getReimbursement(Integer actId) {
		Reimbursement r = new Reimbursement();
		
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_reimbursement WHERE reimb_id = " + actId;
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				//rs.get*()can take the column number or the name of the column in double quotes
				r.setReimbId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getTimestamp(3).toLocalDateTime());
				if (rs.getTimestamp(4) != null) {
					r.setResolved(rs.getTimestamp(4).toLocalDateTime());
				}
				r.setDescription(rs.getString(5));
				User u = new User();
				u.setId(rs.getInt(6));
				r.setAuthor(u);
				if (rs.getInt(7) > 0) {
					User u2 = new User();
					u2.setId(rs.getInt(7));
					r.setResolver(u2);
				}
				r.setStatus(rs.getInt(8));
				ReimType rt = new ReimType();
				rt.setId(rs.getInt(9));
				r.setType(rt);
				
				
			}
			
			sql = "SELECT * from ers_users WHERE user_id = " + r.getAuthor().getId() + ";";
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while(rs.next()) {
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
				
				r.setAuthor(u);
			}
			
			if (r.getResolver() != null) {
				sql = "SELECT * from ers_users WHERE user_id = " + r.getResolver().getId() + ";";
				s = conn.createStatement();
				rs = s.executeQuery(sql);
				while(rs.next()) {
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
					
					r.setResolver(u);
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return r;
	}

	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> allReimbursements = new ArrayList<Reimbursement>();
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_reimbursement";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				Reimbursement r = new Reimbursement();
				r.setReimbId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getTimestamp(3).toLocalDateTime());
				if (rs.getTimestamp(4) != null) {
					r.setResolved(rs.getTimestamp(4).toLocalDateTime());
				}
				r.setDescription(rs.getString(5));
				User u = new User();
				u.setId(rs.getInt(6));
				r.setAuthor(u);
				if (rs.getInt(7) > 0) {
					User u2 = new User();
					u2.setId(rs.getInt(7));
					r.setResolver(u2);
				}
				r.setStatus(rs.getInt(8));
				ReimType rt = new ReimType();
				rt.setId(rs.getInt(9));
				r.setType(rt);
				
				allReimbursements.add(r);
			}
			
			for(Reimbursement r : allReimbursements) {
				sql = "SELECT * from ers_users WHERE user_id = " + r.getAuthor().getId() + ";";
				s = conn.createStatement();
				rs = s.executeQuery(sql);
				while(rs.next()) {
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
					
					r.setAuthor(u);
				}
				
				if (r.getResolver() != null) {
					sql = "SELECT * from ers_users WHERE user_id = " + r.getResolver().getId() + ";";
					s = conn.createStatement();
					rs = s.executeQuery(sql);
					while(rs.next()) {
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
						
						r.setResolver(u);
					}
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allReimbursements;
	}
	
	public List<Reimbursement> getReimbursementsByUser(User author) {
		int userId = author.getId();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try {
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "SELECT * from ers_reimbursement WHERE reimb_author = " + userId + ";";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				Reimbursement r = new Reimbursement();
				r.setReimbId(rs.getInt(1));
				r.setAmount(rs.getDouble(2));
				r.setSubmitted(rs.getTimestamp(3).toLocalDateTime());
				if (rs.getTimestamp(4) != null) {
					r.setResolved(rs.getTimestamp(4).toLocalDateTime());
				}
				r.setDescription(rs.getString(5));
				User u = new User();
				u.setId(rs.getInt(6));
				r.setAuthor(u);
				if (rs.getInt(7) > 0) {
					User u2 = new User();
					u2.setId(rs.getInt(7));
					r.setResolver(u2);
				}
				r.setStatus(rs.getInt(8));
				ReimType rt = new ReimType();
				rt.setId(rs.getInt(9));
				r.setType(rt);
				
				reimbursements.add(r);
			}
			
			for(Reimbursement r : reimbursements) {
				sql = "SELECT * from ers_users WHERE user_id = " + r.getAuthor().getId() + ";";
				s = conn.createStatement();
				rs = s.executeQuery(sql);
				while(rs.next()) {
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
					
					r.setAuthor(u);
				}
				
				if (r.getResolver() != null) {
					sql = "SELECT * from ers_users WHERE user_id = " + r.getResolver().getId() + ";";
					s = conn.createStatement();
					rs = s.executeQuery(sql);
					while(rs.next()) {
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
						
						r.setResolver(u);
					}
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	public Reimbursement updateReimbursement(Reimbursement a) {
		
		
		try {
			/*
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			
			String sql = "UPDATE ers_reimbursement set(reimb_resolver, reimb_status_id) values (?,?) WHERE reimb_id = " + a.getReimbId() + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, a.getResolver().getId());
			
			ps.setInt(2, a.getStatus());
			
			ps.execute();
			ps.close();
			/*/
			Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
			String sql = "UPDATE ers_reimbursement set" +
			
			" reimb_resolver = " + a.getResolver().getId() +
			", reimb_status_id = " + a.getStatus() +
			" WHERE reimb_id = " + a.getReimbId() + ";";
			Statement s = conn.createStatement();
			s.execute(sql);
			
		} catch (SQLException e) {
			System.out.println("error in update reimbursement");
			e.printStackTrace();
		}
		
		
		
		return a;
	}
	
	public boolean removeReimbursement(Reimbursement a) {
		try {
			Connection conn = DriverManager.getConnection(this.url,this.username,this.password);
			String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = " + a.getReimbId() + ";";
			Statement s = conn.createStatement();
			s.execute(sql);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
