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

    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        System.out.println("Get Divisions Called");
        HttpSession httpSession =httpServletRequest.getSession();
        LocationHelper locationHelper=new LocationHelper();
        LocationDAO locationDAO=new LocationDAO();
        String circleName=httpServletRequest.getParameter("circleName");
        System.out.println("Getting Divisions for circle NAME: "+circleName);
        ArrayList<String> locations=new ArrayList<String>();
        try {
            locations=locationDAO.getDivisionByCircleName(circleName);
            System.out.println("Divisions for Circle : "+circleName+" are : "+locations);
        }catch(Exception e){
            System.out.println("Exception in class : GetDivisions method : processRequest() : "+e);
        }
        String json = new Gson().toJson(locations);
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
