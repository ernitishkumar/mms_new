package mms.com.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.Substation;
import mms.com.dao.SubstationDAO;

public class AddSubstationDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("Add Substation started");
		SubstationDAO substation = new SubstationDAO();
		Substation substationBean = (Substation) httpServletRequest.getAttribute("substationBean");
		substation.addSubstation(substationBean);
		System.out.println("Substation Added");
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
