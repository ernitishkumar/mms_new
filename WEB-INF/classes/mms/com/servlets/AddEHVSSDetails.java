package mms.com.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.EHVSS;
import mms.com.dao.EhvssDAO;
public class AddEHVSSDetails extends HttpServlet {

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("AddEHVSSDetails started");
		EhvssDAO ehvss = new EhvssDAO();
		EHVSS ehvssBean = (EHVSS) httpServletRequest.getAttribute("ehvssBean");
		ehvss.addEHVSS(ehvssBean);
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
