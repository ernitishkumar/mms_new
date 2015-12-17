package mms.com.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.Substation;
import mms.com.dao.SubstationDAO;
import mms.com.beans.ErrorBean;
public class AddSubstationDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("Add Substation started");
		SubstationDAO substation = new SubstationDAO();
		Substation substationBean = (Substation) httpServletRequest.getAttribute("substationBean");
		ErrorBean errorBean=new ErrorBean();
		boolean added=substation.addSubstation(substationBean,errorBean);
		if(added!=true){
			System.out.println("Unable to add Substation due to reason : "+errorBean.getErrorMessage());
			httpServletRequest.setAttribute("errorBean",errorBean);
			RequestDispatcher requestDispatcher =httpServletRequest.getRequestDispatcher("/Substation.jsp");
			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}else{
			System.out.println("Substation Added");
			httpServletResponse.sendRedirect("sucess.jsp");	
		}
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
