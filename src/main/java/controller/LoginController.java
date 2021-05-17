package controller;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import beans.ReimType;
import beans.Reimbursement;
import dao.ReimbursementDao;
import dao.UserDao;

public class LoginController {
	
	public static String login(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDao ud = new UserDao();
		User u = new User();
		u = ud.getUser(username, password);
		if(u.getId() <= 0) {
			return "wrongcreds.change";
		}else {
			req.getSession().setAttribute("role", u.getUserType().getRole());
			req.getSession().setAttribute("user", u);
			return "/requestDashboard.change";
		}
	}
	
	public static String logout(HttpServletRequest req) {
		System.out.println("in logincontroller logout");
		req.getSession().invalidate();
		//req.getSession().setAttribute("role", "");
		//req.getSession().setAttribute("user", "");
		return "/requestDashboard.change";
	}
	
	public static String managerSession(HttpServletRequest req) throws JsonProcessingException,
	IOException{
		//ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		System.out.println("in JSONController.checkSession");
		System.out.println(req.getSession().getAttribute("role"));
		String role = String.valueOf(req.getSession().getAttribute("role"));
		
		return "/checkSession.change";
	}
	
	public static String goToAdd(HttpServletRequest req) {
		return "/NewRequest.change";
		}
	
	public static String addReimbursement(HttpServletRequest req) {
		String id = req.getParameter("id");
		String amount = req.getParameter("amount");
		String description = req.getParameter("description");
		Reimbursement r = new Reimbursement();
		r.setAmount(Double.parseDouble(amount));
		r.setDescription(description);
		User u = new User();
		u.setId(Integer.parseInt(id));
		r.setAuthor(u);
		ReimType t = new ReimType();
		t.setId(1); //ToDo: get from drop down
		r.setType(t);
		ReimbursementDao rd = new ReimbursementDao();
		rd.addReimbursement(r);
		return "/requestDashboard.change";
		}
	}






/*webxml

<servlet>
	<servlet-name>MasterServlet</servlet-name>
	<servlet-class>com.example.servlet.MasterServlet</servlet-class>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>MasterServlet</servlet-name>
	<url-pattern>*.change</url-pattern>
	<url-pattern>/api/*</url-pattern>
	<!-- You can have multiple mappings for one servlet
		* is a wildcard -->
</servlet-mapping>

<servlet>
	<servlet-name>JSONMasterServlet</servlet-name>
	<servlet-class>com.example.json.JSONMasterServlet</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>JSONMasterServlet</servlet-name>
	<url-pattern>*.json</url-pattern>
</servlet-mapping>

*/