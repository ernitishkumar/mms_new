package mms.com.servlets;
import mms.com.dao.UserLoginDAO;
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

        HttpSession httpSession =httpServletRequest.getSession();

        try {
            UserLogin userBean=(UserLogin)httpSession.getAttribute("userBean");

            if(user==null){
                httpSession.removeAttribute("userBean");
                ErrorBean errorBean=new ErrorBean("Invalid username/password");
                httpServletRequest.setAttribute("errorBean",errorBean);
                RequestDispatcher requestDispatcher=httpServletRequest.getRequestDispatcher("/index.jsp?ui=i");
                requestDispatcher.forward(httpServletRequest,httpServletResponse);
            }else{
                user=serverInterface.getUserByUserName(userBean.getUserName());
                userBean.setId(user.getId());
                userBean.setFirstName(user.getFirstName());
                userBean.setLastName(user.getLastName());
                userBean.setUserName(user.getUserName());
                userBean.setPwd(user.getPassword());
                userBean.setEmailId(user.getEMailAddress());
                userBean.setDateOfBirth(user.getDateOfBirth());
                userBean.setSex(user.getSex());
                userBean.setHintQuestion(user.getHintQuestion());
                userBean.setHintAnswer(user.getHintAnswer());
                userBean.setCountry(user.getCountry());
                userBean.setActivationStatus(user.getActivationStatus());
                httpSession.setAttribute("userBean",userBean);

                if(userBean.getActivationStatus().equals("A"))
                { 

                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/HomePage.jsp?ui=h");
                }
                else
                {

                    User user1=serverInterface.getUserByUserName(userBean.getUserName());

                    user1.setActivationStatus("A");
                    serverInterface.upDateProfile(user1);
                    userBean.setActivationStatus("A");	  
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/EditUserProfile.jsp");
                }
            }
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
