package mms.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mms.com.beans.Substation;
import mms.com.dao.SubstationDAO;

public class AddSubstation extends HttpServlet{

	 protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			    throws ServletException, IOException {
		 HttpSession httpSession =httpServletRequest.getSession();
		 SubstationDAO substation = new SubstationDAO();
		 Substation substationBean = (Substation) httpSession.getAttribute("substationBean");
		 substation.addSubstation(substationBean);
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
