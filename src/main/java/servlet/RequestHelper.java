package servlet;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

//import com.example.Controllers.HomeController;
import controller.LoginController;

public class RequestHelper {
	//Either sends us to a controller or sends us to a page
	//Caroline's returns void
	 public static String process(HttpServletRequest req) {
		 switch(req.getRequestURI()) {
		 case "/project-1/login.change": //the string that is returned by the controller
			 return LoginController.login(req); //the controller we go to and the function that is executed
		 case "/project-1/logout.change":
			 return LoginController.logout(req); //the controller we go to and the function that is executed
		 case "/project-1/requestDashboard.change":
			 if (req.getSession().getAttribute("role") == "employee") {
				 return "Resources/HTML/MyRequests.html";
			 } else if (req.getSession().getAttribute("role") == "finance manager") {
				 return "Resources/HTML/ManagerDashboard.html";
			 } else {
				 return "Resources/HTML/index.html";
			 }
		 case "/project-1/wrongcreds.change":
			 return "Resources/HTML/wrongcreds.html";
			 
			 
			 
			 
			 
		 case "/project-1/new.change":
			 //System.out.println("you clicked new and now you're in the request helper new.change");
			 return LoginController.goToAdd(req);
		 case "/project-1/NewRequest.change":
			 //System.out.println("now you're in requesthelper NewRequest");
			 return "Resources/HTML/NewRequest.html";
			 
		 case "/project-1/add.change":
			 return LoginController.addReimbursement(req);
		default:
			//System.out.println("In default");
			return "Resources/HTML/index.html"; //the page that we go to
		 }
		 
	 }
	 
	 public static String process(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException{
		 return "";
	 }

}