package servlet;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.JSONController;

public class JSONRequestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
	IOException{
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/project-1/checkSession.json":
			JSONController.checkSession(req,res);
			break;
		case "/project-1/managerdashGetAll.json":
			JSONController.managerdashGetAll(req,res);
			break;
		case "/project-1/approve.json":
			JSONController.approve(req,res);
			break;
		case "/project-1/deny.json":
			JSONController.deny(req,res);
			break;
		case "/project-1/getByUser.json":
			JSONController.getByUser(req, res);
			break;
		case "/project-1/addreim.json":
			JSONController.addreim(req, res);
			break;
		default:
		}
	}

}