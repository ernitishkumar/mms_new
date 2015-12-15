package feeder.com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import feeder.com.beans.Substation;
import feeder.com.dao.SubstationDAO;

public class GetSubstationNames extends HttpServlet{

	 protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			    throws ServletException, IOException {
			        System.out.println("Get EhvssNames Called");
			        SubstationDAO substationDAO=new SubstationDAO();
			        ArrayList<Substation> locations=new ArrayList<Substation>();
			        try {
			            locations=substationDAO.getAll();
			            System.out.println("Substations are : "+locations);
			        }catch(Exception e){
			            System.out.println("Exception in class : GetSubstationNames method : processRequest() : "+e);
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
