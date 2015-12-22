package mms.com.servlets;
import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms.com.beans.Substation;
import mms.com.dao.SubstationDAO;
import mms.com.beans.ErrorBean;
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.*;
public class SubstationController extends HttpServlet{

	private SubstationDAO substationDAO=new SubstationDAO();
	private Gson gson=new Gson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("SubstationController Started");
		String action=(String)httpServletRequest.getParameter("action");
		if(action!=null){
			//System.out.println("SubstationController called for action : "+action);
			if(action.toLowerCase().equals("list")){
				String startIndex=(String)httpServletRequest.getParameter("jtStartIndex");
				String pageSize=(String)httpServletRequest.getParameter("jtPageSize");
				//System.out.println("Start Index : "+startIndex+" Page Size : "+pageSize);
				ArrayList<Substation> substations=new ArrayList<Substation>();
				substations=substationDAO.getAll(startIndex,pageSize);
				int count=substationDAO.getSubstationCount();
				//System.out.println("Count of Substation form controller : "+count);
				String json = gson.toJson(substations);
				JsonElement element = gson.toJsonTree(substations,new TypeToken<ArrayList<Substation>>(){}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//System.out.println("JSON String from Substation Controller is : "+json); //remove after testing
				json="{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+count+"}";
				////System.out.println("Sending Json response as : "+json);
				httpServletResponse.setContentType("application/json");
				httpServletResponse.getWriter().write(json);	
			}else if(action.toLowerCase().equals("create")||action.toLowerCase().equals("update")){
				////System.out.println("Request Recieved for create or update");
				String name=(String)httpServletRequest.getParameter("name");
				String code=(String)httpServletRequest.getParameter("code");
				String region=(String)httpServletRequest.getParameter("region");
				String circle=(String)httpServletRequest.getParameter("circle");
				String division=(String)httpServletRequest.getParameter("division");
				String kv33FeederID=(String)httpServletRequest.getParameter("kv33FeederID");
				if(kv33FeederID.trim().indexOf("ID:")>=0){
					kv33FeederID=kv33FeederID.substring(kv33FeederID.indexOf("(ID:")+4,kv33FeederID.lastIndexOf(")"));
				}
				//System.out.println("33KVFeeder id from SubstationController : "+kv33FeederID); 
				////System.out.println("Data for jTABLE create : "+name+" "+code+" "+region);
				Substation substation=new Substation(name,code,region,circle,division,kv33FeederID);
				if(action.toLowerCase().equals("create")){
					substation=substationDAO.addSubstation(substation);
					String json=gson.toJson(substation);    
					String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";          
					httpServletResponse.getWriter().print(listData);
					////System.out.println("kv33Feeder added from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}else if(action.toLowerCase().equals("update")){
					String id=(String)httpServletRequest.getParameter("id");
					substation.setId(id);
					substation=substationDAO.updateSubstation(substation);
					String listData="{\"Result\":\"OK\"}";        
					httpServletResponse.getWriter().print(listData);
					////System.out.println("kv33Feeder updated from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}
			}else if(action.toLowerCase().equals("delete")){
				if(httpServletRequest.getParameter("id")!=null){
					String id=(String)httpServletRequest.getParameter("id");
					substationDAO.deleteSubstationById(id);
					String listData="{\"Result\":\"OK\"}";       
					httpServletResponse.getWriter().print(listData);
				}	
			}
			
		}else{
			//System.out.println("Error in SubstationController");
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
