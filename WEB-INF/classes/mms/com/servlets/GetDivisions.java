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
import com.google.gson.Gson;

public class GetDivisions extends HttpServlet {
    private Gson gson=new Gson();
    private LocationHelper locationHelper=new LocationHelper();
    private LocationDAO locationDAO=new LocationDAO();
    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //System.out.println("Get Divisions Called");
        HttpSession httpSession =httpServletRequest.getSession();
        String regionName=httpServletRequest.getParameter("regionName");
        String source=httpServletRequest.getParameter("source");
        String circleName=httpServletRequest.getParameter("circleName");
        /*System.out.println("Getting Divisions Source : "+source);
        System.out.println("Getting Divisions for region NAME: "+regionName);
        System.out.println("Getting Divisions for circle NAME: "+circleName);*/
        ArrayList<String> locations=new ArrayList<String>();
        if(circleName==null && regionName==null){
         locations=locationDAO.getAllDivisions();
     }else if(circleName!=null){
         locations=locationDAO.getDivisionByCircleName(circleName);
     }else if(regionName!=null){
        locations=locationDAO.getDivisionByRegionName(regionName);
    }
    //System.out.println("Divisions for Circle : "+circleName+" are : "+locations);
    String json = "";
    if(source==null){
        json = gson.toJson(locations);
    }else if(source.toLowerCase().trim().equals("jtable")){
        json="{\"Result\":\"OK\",\"Options\":"+gson.toJson(locations)+"}";
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
