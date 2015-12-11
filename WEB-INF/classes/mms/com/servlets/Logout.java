package com.haresh.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.haresh.beans.*;
import com.haresh.utility.*;
import com.haresh.common.*;
import com.haresh.server.*;
import java.rmi.*;
import java.net.*;
import java.rmi.server.*;
import nl.captcha.Captcha;
public class Logout extends HttpServlet {
protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
throws ServletException, IOException {
HttpSession httpSession =httpServletRequest.getSession();
User user=new User();
try {
httpSession.removeAttribute("userBean");
httpSession.removeAttribute("errorBean");
httpSession.removeAttribute("messageBean");
httpSession.removeAttribute("signUpBean");
httpSession.removeAttribute("checkAvailabilityBean");
httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index.jsp?pageNumber=1");
}catch(Exception e)
{
System.out.println(e);

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
