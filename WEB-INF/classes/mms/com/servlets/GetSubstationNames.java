package mms.com.servlets;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import mms.com.beans.Substation;
import mms.com.dao.SubstationDAO;

public class GetSubstationNames extends HttpServlet{
	private SubstationDAO substationDAO=new SubstationDAO();
	private Gson gson=new Gson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws ServletException, IOException {
		//System.out.println("Get Substation Names Called");
		String regionName=httpServletRequest.getParameter("regionName");
		//System.out.println("Region : "+regionName);
		String circleName=httpServletRequest.getParameter("circleName");
		//System.out.println("Circle : "+circleName);
		String divisionName=httpServletRequest.getParameter("divisionName");
		//System.out.println("Division : "+divisionName);
		String source=httpServletRequest.getParameter("source");
		//System.out.println("Source : "+source);
		ArrayList<Substation> locations=new ArrayList<Substation>();
		ArrayList<String> substationNames=new ArrayList<String>();
		String json = "";
		if(source==null){
			if(regionName==null&&circleName==null&&divisionName==null){
				locations=substationDAO.getAll();
			}else if(circleName!=null){
				locations=substationDAO.getByCircle(circleName);
			}else if(regionName!=null){
				locations=substationDAO.getByRegion(regionName);
			}else if(divisionName!=null){
				locations=substationDAO.getByDivision(divisionName);
			}
			json = gson.toJson(locations);
		}else if(source!=null && source.toLowerCase().trim().equals("jtable")){
			if(circleName==null && regionName==null && divisionName==null){
				substationNames=substationDAO.getAllSubstationsNameWithId();
			}else if(circleName!=null){
				substationNames=substationDAO.getSubstationsNameWithIdByCircle(circleName);
			}else if(regionName!=null){
				substationNames=substationDAO.getSubstationsNameWithIdByRegion(regionName);
			}else if(divisionName!=null){
				substationNames=substationDAO.getSubstationsNameWithIdByDivision(divisionName);
			}
			json="{\"Result\":\"OK\",\"Options\":"+gson.toJson(substationNames)+"}";
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
