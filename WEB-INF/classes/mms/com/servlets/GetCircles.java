package mms.com.servlets;
import mms.com.dao.LocationDAO;
import mms.com.dao.LocationHelper;
import mms.com.beans.ErrorBean;
import mms.com.beans.MessageBean;
import mms.com.beans.UserLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class GetCircles extends HttpServlet {
    private Gson gson=new Gson();
    private LocationHelper locationHelper=new LocationHelper();
    private LocationDAO locationDAO=new LocationDAO();
    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        //System.out.println("Get Circles Called");
        HttpSession httpSession =httpServletRequest.getSession();
        String regionName=httpServletRequest.getParameter("regionName");
        String source=httpServletRequest.getParameter("source");
        /*
        System.out.println("Source : "+source);
        System.out.println("Getting circles for region NAME: "+regionName);*/       
        ArrayList<String> locations=new ArrayList<String>();
        if(regionName!=null && !regionName.toLowerCase().trim().equals("all")){
            locations=locationDAO.getCirclesByRegionName(regionName);
            //System.out.println("Size of Circles for Region : "+regionName+" are : "+locations.size());    
        }else{
            locations=locationDAO.getCircles();
            //System.out.println("Size of all Circles is : "+locations.size()); 
        }
        String json="";
        if(source!=null && source.toLowerCase().equals("jtable")){
           json="{\"Result\":\"OK\",\"Options\":"+gson.toJson(locations)+"}";
            //System.out.println("Sending Json response as : "+json);
       }else{
        json =gson.toJson(locations);
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
