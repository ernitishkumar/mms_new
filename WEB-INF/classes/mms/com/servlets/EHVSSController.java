package mms.com.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.EHVSS;
import mms.com.dao.EhvssDAO;
import mms.com.beans.ErrorBean;
import java.util.ArrayList;
import com.google.gson.Gson;
public class EHVSSController extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("EHVSSController Started");
		String action=(String)httpServletRequest.getParameter("action");
		if(action!=null){
			System.out.println("EHVSSController called for action : "+action);
			EhvssDAO ehvssDAO=new EhvssDAO();
			if(action.toLowerCase().equals("list")){
				ArrayList<EHVSS> ehvssRecords=new ArrayList<EHVSS>();
				ehvssRecords=ehvssDAO.getAll();
				String json = new Gson().toJson(ehvssRecords);
				//System.out.println("JSON String is : "+json); //remove after testing
				json="{\"Result\":\"OK\",\"Records\":"+json+"}";
				//System.out.println("Sending Json response as : "+json);
				httpServletResponse.setContentType("application/json");
				httpServletResponse.getWriter().write(json);	
			}
			if(action.toLowerCase().equals("create")){
				System.out.println("Request Recieved for create");
			}
			
		}else{
			String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
			httpServletResponse.getWriter().print(error);
		}

	}

public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	processRequest(request, response);
} 
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	processRequest(request, response);
}
}
