package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.ReimType;
import beans.Reimbursement;
import beans.User;
import dao.ReimbursementDao;

public class JSONController {
	
	public static void checkSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		//ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		System.out.println("in JSONController.checkSession");
		System.out.println(req.getSession().getAttribute("role"));
		String role = String.valueOf(req.getSession().getAttribute("role"));
		res.getWriter().write(new ObjectMapper().writeValueAsString(req.getSession().getAttribute("user")));
	}
	
	public static void managerdashGetAll(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		System.out.print("in JSONController managerdashGetAll. Session is: ");
		System.out.println(req.getSession().getAttribute("role"));
		List<Reimbursement> rList = new ArrayList<Reimbursement>();
		ReimbursementDao rd = new ReimbursementDao();
		rList = rd.getReimbursements();
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
	
	public static void approve(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		Reimbursement r = new Reimbursement();
		ReimbursementDao rd = new ReimbursementDao();
		r.setReimbId(Integer.valueOf(req.getParameter("id")));
		r.setStatus(1);
		User u = new User();
		u = (User) req.getSession().getAttribute("user");
		r.setResolver(u); 
		rd.updateReimbursement(r);
		res.sendRedirect("Resources/HTML/ManagerDashboard.html");
	}
	
	public static void deny(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		Reimbursement r = new Reimbursement();
		ReimbursementDao rd = new ReimbursementDao();
		r.setReimbId(Integer.valueOf(req.getParameter("id")));
		r.setStatus(0);
		User u = new User();
		u = (User) req.getSession().getAttribute("user");
		r.setResolver(u); 
		rd.updateReimbursement(r);
		res.sendRedirect("Resources/HTML/ManagerDashboard.html");
	}
	
	
	//Populates table on MyRequests.html
	public static void getByUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> rList = new ArrayList<Reimbursement>();
		ReimbursementDao rd = new ReimbursementDao();
		User u = new User();
		u = (User) req.getSession().getAttribute("user");
		rList = rd.getReimbursementsByUser(u);
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
	
	
	//Adds new reimbursement request
	public static void addreim(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		System.out.println("the type is: " + req.getParameter("type"));
		Reimbursement r = new Reimbursement();
		ReimbursementDao rd = new ReimbursementDao();
		r.setStatus(2);
		r.setAmount(Double.parseDouble(req.getParameter("amount")));
		r.setDescription(req.getParameter("description"));
		ReimType t = new ReimType();
		t.setId(Integer.valueOf(req.getParameter("type")));
		r.setType(t);
		User u = new User();
		u.setId(Integer.valueOf(req.getParameter("id")));
		r.setAuthor(u);
		rd.addReimbursement(r);
		res.sendRedirect("Resources/HTML/MyRequests.html");
	}

}