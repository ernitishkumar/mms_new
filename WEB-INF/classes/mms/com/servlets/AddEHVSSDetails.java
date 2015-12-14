package feeder.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import feeder.com.beans.EHVSS;
import feeder.com.dao.EhvssDAO;

public class AddEHVSSDetails extends HttpServlet {

	 protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			    throws ServletException, IOException {
		 HttpSession httpSession =httpServletRequest.getSession();
		 EhvssDAO ehvss = new EhvssDAO();
		 EHVSS ehvssBean = (EHVSS) httpSession.getAttribute("EhvssBean");
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
