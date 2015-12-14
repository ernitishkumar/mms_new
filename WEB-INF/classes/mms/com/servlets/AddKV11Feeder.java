package feeder.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import feeder.com.beans.KV11Feeder;
import feeder.com.dao.KV11FeederDAO;

public class AddKV11Feeder extends HttpServlet{

	 protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			    throws ServletException, IOException {
		 HttpSession httpSession =httpServletRequest.getSession();
		 KV11FeederDAO kv11Feeder = new KV11FeederDAO();
		 KV11Feeder kv11FeederBean = (KV11Feeder) httpSession.getAttribute("kv11FeederBean");
		 kv11Feeder.add11KVFeeder(kv11FeederBean);
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
