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
import com.google.gson.*;
import com.google.gson.reflect.*;
public class EHVSSController extends HttpServlet{

	private EhvssDAO ehvssDAO=new EhvssDAO();
	private Gson gson=new Gson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("EHVSSController Started");
		String action=(String)httpServletRequest.getParameter("action");
		String regionName=(String)httpServletRequest.getParameter("region");
		//System.out.println("Action and region from request : "+action+" "+regionName);
		if(action!=null){
			//System.out.println("EHVSSController called for action : "+action);
			if(action.toLowerCase().equals("list")){
				String startIndex=(String)httpServletRequest.getParameter("jtStartIndex");
				String pageSize=(String)httpServletRequest.getParameter("jtPageSize");
				//System.out.println("Start Index : "+startIndex+" Page Size : "+pageSize);
				ArrayList<EHVSS> ehvssRecords=new ArrayList<EHVSS>();
				int count=0;
				if(regionName==null || regionName.toLowerCase().trim().equals("all")){
					ehvssRecords=ehvssDAO.getAll(startIndex,pageSize);
					count=ehvssDAO.getEhvssCount();
				}else if(regionName!=null && !regionName.toLowerCase().trim().equals("all")){
					ehvssRecords=ehvssDAO.getByRegion(regionName,startIndex,pageSize);
					count=ehvssDAO.getEhvssCountByRegion(regionName);
				}
				//System.out.println("Count of EHVSS form controller : "+count);
				String json = gson.toJson(ehvssRecords);
				JsonElement element = gson.toJsonTree(ehvssRecords,new TypeToken<ArrayList<EHVSS>>(){}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//System.out.println("JSON String is : "+json); //remove after testing
				json="{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+count+"}";
				//System.out.println("Sending Json response as : "+json);
				httpServletResponse.setContentType("application/json");
				httpServletResponse.getWriter().write(json);	
			}
			if(action.toLowerCase().equals("create")||action.toLowerCase().equals("update")){
				System.out.println("Request Recieved for create or update");
				String name=(String)httpServletRequest.getParameter("name");
				String code=(String)httpServletRequest.getParameter("code");
				String region=(String)httpServletRequest.getParameter("region");
				//System.out.println("Data for jTABLE create : "+name+" "+code+" "+region);
				EHVSS ehvss=new EHVSS(name,code,region);
				if(action.toLowerCase().equals("create")){
					ehvss=ehvssDAO.addEHVSS(ehvss);
					String json=gson.toJson(ehvss);    
					String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";          
					httpServletResponse.getWriter().print(listData);
					System.out.println("EHVSS added from EHVSS controller and last create id is : "+ehvss.getId());
				}else if(action.toLowerCase().equals("update")){
					String id=(String)httpServletRequest.getParameter("id");
					ehvss.setId(id);
					ehvss=ehvssDAO.updateEHVSS(ehvss);
					String listData="{\"Result\":\"OK\"}";        
					httpServletResponse.getWriter().print(listData);
					System.out.println("EHVSS updated from EHVSS controller and last create id is : "+ehvss.getId());
				}
			}
			if(action.toLowerCase().equals("delete")){
				if(httpServletRequest.getParameter("id")!=null){
					String id=(String)httpServletRequest.getParameter("id");
					System.out.println("Deleting EHVSS for id : "+id);
					ehvssDAO.deleteEHVSSById(id);
					String listData="{\"Result\":\"OK\"}";       
					httpServletResponse.getWriter().print(listData);
				}	
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
