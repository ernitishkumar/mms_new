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
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.*;
public class KV33FeederController extends HttpServlet{

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("KV33FeederController Started");
		String action=(String)httpServletRequest.getParameter("action");
		if(action!=null){
			////System.out.println("EHVSSController called for action : "+action);
			KV33FeederDAO kv33FeederDAO=new KV33FeederDAO();
			Gson gson=new Gson();
			if(action.toLowerCase().equals("list")){
				String startIndex=(String)httpServletRequest.getParameter("jtStartIndex");
				String pageSize=(String)httpServletRequest.getParameter("jtPageSize");
				////System.out.println("Start Index : "+startIndex+" Page Size : "+pageSize);
				ArrayList<KV33Feeder> kv33FeederRecords=new ArrayList<KV33Feeder>();
				kv33FeederRecords=kv33FeederDAO.getAll(startIndex,pageSize);
				int count=kv33FeederDAO.getKV33FeederCount();
				////System.out.println("Count of EHVSS form controller : "+count);
				String json = new Gson().toJson(kv33FeederRecords);
				JsonElement element = gson.toJsonTree(kv33FeederRecords,new TypeToken<ArrayList<KV33Feeder>>(){}.getType());
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
				String ehvssId=(String)httpServletRequest.getParameter("ehvssID");
				System.out.println("EHVSS ID from request : "+ehvssId);
				if(ehvssId.trim().indexOf("ID:")>=0){
					ehvssId=ehvssId.substring(ehvssId.indexOf("(ID:")+4,ehvssId.lastIndexOf(")"));
				}
				System.out.println("EHVSS ID from request after Parsing : "+ehvssId);
				////System.out.println("Data for jTABLE create : "+name+" "+code+" "+region);
				KV33Feeder kv33Feeder=new KV33Feeder(name,code,region,circle,ehvssId);
				if(action.toLowerCase().equals("create")){
					kv33Feeder=kv33FeederDAO.addKV33Feeder(kv33Feeder);
					String json=gson.toJson(kv33Feeder);    
					String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";          
					httpServletResponse.getWriter().print(listData);
					//System.out.println("kv33Feeder added from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}else if(action.toLowerCase().equals("update")){
					String id=(String)httpServletRequest.getParameter("id");
					kv33Feeder.setId(id);
					kv33Feeder=kv33FeederDAO.updateKV33Feeder(kv33Feeder);
					String listData="{\"Result\":\"OK\"}";        
					httpServletResponse.getWriter().print(listData);
					////System.out.println("kv33Feeder updated from kv33Feeder controller and last create id is : "+kv33Feeder.getId());
				}
			}else if(action.toLowerCase().equals("delete")){
				//System.out.println("Coming for delete");
				if(httpServletRequest.getParameter("id")!=null){
					String id=(String)httpServletRequest.getParameter("id");
					//System.out.println("ID for delete : "+id);
					kv33FeederDAO.deleteKV33FeederById(id);
					String listData="{\"Result\":\"OK\"}";       
					httpServletResponse.getWriter().print(listData);
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
