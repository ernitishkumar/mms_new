package mms.com.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.KV11Feeder;
import mms.com.dao.KV11FeederDAO;

public class AddKV11FeederDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("Add 11 KV Feeder details called");
		KV11FeederDAO kv11Feeder = new KV11FeederDAO();
		KV11Feeder kv11FeederBean = (KV11Feeder) httpServletRequest.getAttribute("kv11FeederBean");
		kv11Feeder.add11KVFeeder(kv11FeederBean);
		System.out.println("11 KV Feeder Added");
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
