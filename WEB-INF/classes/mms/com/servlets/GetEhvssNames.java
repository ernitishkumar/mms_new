package mms.com.servlets;
import mms.com.dao.EhvssDAO;
import mms.com.beans.ErrorBean;
import mms.com.beans.MessageBean;
import mms.com.beans.UserLogin;
import mms.com.beans.EHVSS;
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

public class GetEhvssNames extends HttpServlet {
    private EhvssDAO ehvssDAO=new EhvssDAO();
    private Gson gson=new Gson();
    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        //System.out.println("Get EhvssNames Called");
        String regionName=httpServletRequest.getParameter("regionName");
        //System.out.println("Region : "+regionName);
        String circleName=httpServletRequest.getParameter("circleName");
        //System.out.println("Circle : "+circleName);
        String source=httpServletRequest.getParameter("source");
        //System.out.println("Source : "+source);
        ArrayList<EHVSS> locations=new ArrayList<EHVSS>();
        ArrayList<String> ehvssNames=new ArrayList<String>();
        try {
            String json="";
            if(source==null){
                if((regionName==null||regionName.toLowerCase().trim().equals("all")) && circleName==null) {
                    locations=ehvssDAO.getAll();
                }else if(regionName!=null){
                    locations=ehvssDAO.getByRegion(regionName);
                }else if(circleName!=null){
                    locations=ehvssDAO.getByCircle(circleName);
                }
            //System.out.println("EHVSS for region :"+regionName+" is : "+locations);
                json = gson.toJson(locations);
                
            }else if(source!=null && source.toLowerCase().trim().equals("jtable")){
                if(regionName==null && circleName==null) {
                    ehvssNames=ehvssDAO.getAllEhvssNamesWithId();
                }else if(regionName!=null){
                    ehvssNames=ehvssDAO.getEhvssNamesWithIdByRegion(regionName);
                }else if(circleName!=null){
                    ehvssNames=ehvssDAO.getEhvssNamesWithIdByCircle(circleName);
                }

                json="{\"Result\":\"OK\",\"Options\":"+gson.toJson(ehvssNames)+"}";
                //System.out.println("Sending Json response for ehvss names as : "+json);
            }
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(json);
        }catch(Exception e){
            System.out.println("Exception in class : GetEhvssNames method : processRequest() : "+e);
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
