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

public class GetEhvssNamesByRegion extends HttpServlet {

    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        System.out.println("Get EhvssNames Called");
        EhvssDAO ehvssDAO=new EhvssDAO();
        ArrayList<EHVSS> locations=new ArrayList<EHVSS>();
        try {
            locations=ehvssDAO.getAll();
            System.out.println("EHVSS are : "+locations);
        }catch(Exception e){
            System.out.println("Exception in class : GetEhvssNames method : processRequest() : "+e);
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
