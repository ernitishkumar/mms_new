package mms.com.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.KV11Feeder;
import mms.com.dao.KV11FeederDAO;
import mms.com.beans.ErrorBean;
public class AddKV11FeederDetails extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("Add 11 KV Feeder details called");
		KV11FeederDAO kv11Feeder = new KV11FeederDAO();
		KV11Feeder kv11FeederBean = (KV11Feeder) httpServletRequest.getAttribute("kv11FeederBean");
		ErrorBean errorBean=new ErrorBean();
		boolean added=kv11Feeder.add11KVFeeder(kv11FeederBean,errorBean);
		if(added!=true){
			System.out.println("Unable to add 11KV Feeder due to reason : "+errorBean.getErrorMessage());
			httpServletRequest.setAttribute("errorBean",errorBean);
			RequestDispatcher requestDispatcher =httpServletRequest.getRequestDispatcher("/KV11FeederDetails.jsp");
			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}else{
			System.out.println("11 KV Feeder Added");
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
