package mms.com.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.KV33Feeder;
import mms.com.dao.KV33FeederDAO;
import mms.com.beans.ErrorBean;

public class AddKV33FeederDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("AddKV33FeederDetails Started ");
		KV33FeederDAO kv33Feeder = new KV33FeederDAO();
		KV33Feeder kv33FeederBean = (KV33Feeder) httpServletRequest.getAttribute("kv33FeederBean");
		System.out.println("33KV Division "+kv33FeederBean.getDivision());
		ErrorBean errorBean=new ErrorBean();
		boolean added=kv33Feeder.addKV33Feeder(kv33FeederBean,errorBean);
		if(added!=true){
			System.out.println("Unable to add 33KV Feeder due to reason : "+errorBean.getErrorMessage());
			httpServletRequest.setAttribute("errorBean",errorBean);
			RequestDispatcher requestDispatcher =httpServletRequest.getRequestDispatcher("/KV33FeederDetails.jsp");
			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}else{
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
