package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MasterServlet extends HttpServlet{
	//sends us to RequestHelper
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		//System.out.println("in doGet");
		HttpSession session = req.getSession(true);
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req, res);
		//RequestHelper.process(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		//System.out.println("in doPost");
		HttpSession session = req.getSession(true);
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req,res);
		//RequestHelper.process(req, res);
	}

}