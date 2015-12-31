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
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.*;
public class KV11FeederController extends HttpServlet{
	private KV11FeederDAO kv11FeederDAO=new KV11FeederDAO();
	private Gson gson=new Gson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("KV11FeederController Started");
		String action=(String)httpServletRequest.getParameter("action");
		String regionName=(String)httpServletRequest.getParameter("region");
		String circleName=(String)httpServletRequest.getParameter("circle");
		String divisionName=(String)httpServletRequest.getParameter("division");
		String substation=(String)httpServletRequest.getParameter("substationID");
		//System.out.println("Action and region from request : "+action+" "+regionName+" "+circleName+" "+divisionName+" "+substation);
		if(action!=null){
			////System.out.println("EHVSSController called for action : "+action);
			if(action.toLowerCase().equals("list")){
				String startIndex=(String)httpServletRequest.getParameter("jtStartIndex");
				String pageSize=(String)httpServletRequest.getParameter("jtPageSize");
				////System.out.println("Start Index : "+startIndex+" Page Size : "+pageSize);
				ArrayList<KV11Feeder> kv11FeederRecords=new ArrayList<KV11Feeder>();
				int count=0;
				if((regionName==null|| regionName.toLowerCase().trim().equals("all")) && circleName==null && divisionName==null && substation==null){
					kv11FeederRecords=kv11FeederDAO.getAll(startIndex,pageSize);
				    count=kv11FeederDAO.getKV11FeedersCount();
				}else if((regionName!=null && !regionName.toLowerCase().trim().equals("all")) && circleName==null && divisionName==null && substation==null){
					kv11FeederRecords=kv11FeederDAO.getByRegion(regionName,startIndex,pageSize);
				    count=kv11FeederDAO.getKV11FeedersCountByRegion(regionName);
				}else if(regionName==null && circleName!=null && divisionName==null && substation==null){
					kv11FeederRecords=kv11FeederDAO.getByCircle(circleName,startIndex,pageSize);
				    count=kv11FeederDAO.getKV11FeedersCountByCircle(circleName);
				}else if(regionName==null && circleName==null &&  divisionName!=null && substation==null){
					kv11FeederRecords=kv11FeederDAO.getByDivision(divisionName,startIndex,pageSize);
				    count=kv11FeederDAO.getKV11FeedersCountByDivision(divisionName);
				}else if(regionName==null && circleName==null &&  divisionName==null && substation!=null){
					kv11FeederRecords=kv11FeederDAO.getBySubstationId(substation,startIndex,pageSize);
				    count=kv11FeederDAO.getKV11FeedersCountBySubstationId(substation);
				}
				////System.out.println("Count of EHVSS form controller : "+count);
				String json = gson.toJson(kv11FeederRecords);
				JsonElement element = gson.toJsonTree(kv11FeederRecords,new TypeToken<ArrayList<KV11Feeder>>(){}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				////System.out.println("JSON String is : "+json); //remove after testing
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
				String dc=(String)httpServletRequest.getParameter("dc");
				String feederType=(String)httpServletRequest.getParameter("feederType");
				String substationID=(String)httpServletRequest.getParameter("substationID");
				if(substationID.trim().indexOf("ID:")>=0){
					substationID=substationID.substring(substationID.indexOf("(ID:")+4,substationID.lastIndexOf(")"));
				}
				////System.out.println("Data for jTABLE create : "+name+" "+code+" "+region);
				KV11Feeder kv11Feeder=new KV11Feeder(name,code,region,circle,division,dc,substationID,feederType);
				if(action.toLowerCase().equals("create")){
					kv11Feeder=kv11FeederDAO.add11KVFeeder(kv11Feeder);
					String json=gson.toJson(kv11Feeder);    
					String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";          
					httpServletResponse.getWriter().print(listData);
					//System.out.println("kv33Feeder added from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}else if(action.toLowerCase().equals("update")){
					String id=(String)httpServletRequest.getParameter("id");
					kv11Feeder.setId(id);
					kv11Feeder=kv11FeederDAO.update11KVFeeder(kv11Feeder);
					String listData="{\"Result\":\"OK\"}";        
					httpServletResponse.getWriter().print(listData);
					////System.out.println("kv33Feeder updated from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}
			}else if(action.toLowerCase().equals("delete")){
				//System.out.println("Coming for delete");
				if(httpServletRequest.getParameter("id")!=null){
					String id=(String)httpServletRequest.getParameter("id");
					//System.out.println("ID for delete : "+id);
					kv11FeederDAO.deleteById(id);
					String listData="{\"Result\":\"OK\"}";       
					httpServletResponse.getWriter().print(listData);
				}else{
					//System.out.println("Error coming here");
					String error="{\"Result\":\"ERROR\",\"Message\":"+"Unable to delete"+"}";
					httpServletResponse.getWriter().print(error);
				}	
			}
			
		}else{
			//System.out.println("Error coming here");
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
