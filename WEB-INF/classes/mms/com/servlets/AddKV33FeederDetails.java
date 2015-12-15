package mms.com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mms.com.beans.KV33Feeder;
import mms.com.dao.KV33FeederDAO;

public class AddKV33FeederDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("AddKV33FeederDetails Started ");
		KV33FeederDAO kv33Feeder = new KV33FeederDAO();
		KV33Feeder kv33FeederBean = (KV33Feeder) httpServletRequest.getAttribute("kv33FeederBean");
		System.out.println("KV33 Name : "+kv33FeederBean.getName());
		System.out.println("KV33 Code : "+kv33FeederBean.getCode());
		System.out.println("KV33 Location : "+kv33FeederBean.getLocation());
		System.out.println("KV33 REgion : "+kv33FeederBean.getRegion());
		System.out.println("KV33 Circle : "+kv33FeederBean.getCircle());
		System.out.println("KV33 Division : "+kv33FeederBean.getDivision());
		System.out.println("KV33 EHVSS ID : "+kv33FeederBean.getEhvssID());
		
		kv33Feeder.addKV33Feeder(kv33FeederBean);
//		System.out.println("KV33Feeder Details added successfully");
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
