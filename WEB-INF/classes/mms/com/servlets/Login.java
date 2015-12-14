package mms.com.servlets;
import mms.com.dao.UserLoginDAO;
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

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    throws ServletException, IOException {
        System.out.println("User authentication started");
        HttpSession httpSession =httpServletRequest.getSession();
        UserLoginDAO userLoginDAO=new UserLoginDAO();

        try {
            UserLogin userBean=(UserLogin)httpSession.getAttribute("userBean");
            UserLogin user=userLoginDAO.getByUserID(userBean.getUserLoginid());
            if(user==null){
                System.out.println("User authentication failed for user : "+userBean.getUserLoginid());
                httpSession.removeAttribute("userBean");
                ErrorBean errorBean=new ErrorBean("Invalid username/password");
                httpServletRequest.setAttribute("errorBean",errorBean);
                RequestDispatcher requestDispatcher=httpServletRequest.getRequestDispatcher("/index.jsp?ui=i");
                requestDispatcher.forward(httpServletRequest,httpServletResponse);
            }else{
                System.out.println("User authentication successful for user : "+userBean.getUserLoginid());
                httpSession.setAttribute("userBean",user);
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/home.jsp");
            }
        }catch(Exception e){
            System.out.println("Exception in class : Login method : processRequest() : "+e);
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
