package mms.com.servlets;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import mms.com.beans.KV33Feeder;
import mms.com.dao.KV33FeederDAO;

public class GetKV33FeederNames extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		System.out.println("Get 33kvFeederNames Called");
		KV33FeederDAO kv33FeederDAO=new KV33FeederDAO();
		String circleName=httpServletRequest.getParameter("circleName");
		ArrayList<KV33Feeder> locations=new ArrayList<KV33Feeder>();
		try {
			if(circleName==null){
				locations=kv33FeederDAO.getAll();
			}else{
				locations=kv33FeederDAO.getByCircle(circleName);
			}
			System.out.println("KV 33 Feeders for circle "+circleName+" are : "+locations.size());
		}catch(Exception e){
			System.out.println("Exception in class : Get33kvFeederNames method : processRequest() : "+e);
		}
		String json = new Gson().toJson(locations);
		System.out.println("JSON String is : "+json);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.getWriter().write(json);
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
