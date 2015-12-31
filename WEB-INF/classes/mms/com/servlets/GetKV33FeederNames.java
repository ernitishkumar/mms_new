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

	private KV33FeederDAO kv33FeederDAO=new KV33FeederDAO();
	private Gson gson=new Gson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("Get 33kvFeederNames Called");
		String regionName=httpServletRequest.getParameter("regionName");
		//System.out.println("Region : "+regionName);
		String circleName=httpServletRequest.getParameter("circleName");
		//System.out.println("Circle : "+circleName);
		String source=httpServletRequest.getParameter("source");
		//System.out.println("Source : "+source);

		ArrayList<KV33Feeder> locations=new ArrayList<KV33Feeder>();
		ArrayList<String> kv33FeederNames=new ArrayList<String>();
		String json = "";
		if(source==null){
			if(circleName==null){
				locations=kv33FeederDAO.getAll();
			}else if(circleName!=null){
				locations=kv33FeederDAO.getByCircle(circleName);
			}else if(regionName!=null){
				locations=kv33FeederDAO.getByRegion(regionName);
			}
			json = gson.toJson(locations);
		}else if(source!=null && source.toLowerCase().trim().equals("jtable")){
			if(circleName==null && regionName==null){
				kv33FeederNames=kv33FeederDAO.getAllKV33FeedersNameWithId();
			}else if(circleName!=null){
				kv33FeederNames=kv33FeederDAO.getKV33FeedersNameWithIdByCircle(circleName);
			}else if(regionName!=null){
				kv33FeederNames=kv33FeederDAO.getKV33FeedersNameWithIdByRegion(regionName);
			}
			json="{\"Result\":\"OK\",\"Options\":"+gson.toJson(kv33FeederNames)+"}";
		}
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
