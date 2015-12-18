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

    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        System.out.println("Get Circles Called");
        Gson gson=new Gson();
        HttpSession httpSession =httpServletRequest.getSession();
        LocationHelper locationHelper=new LocationHelper();
        LocationDAO locationDAO=new LocationDAO();
        String regionName=httpServletRequest.getParameter("regionName");
        String source=httpServletRequest.getParameter("source");
        System.out.println("Source : "+source);
        System.out.println("Getting circles for region NAME: "+regionName);
        ArrayList<String> locations=new ArrayList<String>();
        try {
            locations=locationDAO.getCirclesByRegionName(regionName);
            System.out.println("Circles for Region : "+regionName+" are : "+locations);
        }catch(Exception e){
            System.out.println("Exception in class : GetCircles method : processRequest() : "+e);
        }
        if(source!=null && source.toLowerCase().equals("jtable")){

            int i=1;
            String optionData="";
            for(String circle:locations){
             optionData+="{\"DisplayText\":\""+circle+"\",\"Value\":\""+i+"\"},";
             i++;
         }
         optionData=optionData.substring(0,optionData.length()-1);
         System.out.println("Circles in json format : "+optionData);
         String json="{\"Result\":\"OK\",\"Options\":["+optionData+"]}";
         System.out.println("Sending Json response as : "+json);
         httpServletResponse.setContentType("application/json");
         httpServletResponse.getWriter().write(json);
     }else{
        String json = new Gson().toJson(locations);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(json);
    }
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
