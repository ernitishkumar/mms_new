package mms.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mms.com.beans.KV33Feeder;
import mms.com.dao.KV33FeederDAO;

public class AddKV33Feeder extends HttpServlet{

	 protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			    throws ServletException, IOException {
		 HttpSession httpSession =httpServletRequest.getSession();
		 KV33FeederDAO kv33Feeder = new KV33FeederDAO();
		 KV33Feeder kv33FeederBean = (KV33Feeder) httpSession.getAttribute("kv33FeederBean");
		 kv33Feeder.addKV33Feeder(kv33FeederBean);
		 httpServletResponse.sendRedirect("sucess.jsp");
	 }
			 
	@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
  processRequest(request, response);
} 

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
  processRequest(request, response);
}
}
