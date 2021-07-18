package ems;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.*;
  
public class Login extends HttpServlet 
{  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException 
    {  
      
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();        
        String username=request.getParameter("username");  
        String password=request.getParameter("password"); 
        //String domain_name=request.getParameter("domain_name"); 

        HttpSession sec=request.getSession();
        String login=DatabaseHandling.login(username,password);
        if(login!=null)
        {
            sec.setAttribute("username",username);
            sec.setAttribute("logouttype","normal");
            response.sendRedirect(request.getContextPath() + "/admin.jsp"); 
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
              
        out.close();  
    }  
}  