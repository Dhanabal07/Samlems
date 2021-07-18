package ems;
import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.*;  
import javax.servlet.*;  
  
public class XFrameHeader implements Filter{  
  
    public void init(FilterConfig arg0) throws ServletException 
    {

    }  
          
    public void doFilter(ServletRequest req, ServletResponse resp,  
        FilterChain chain) throws IOException, ServletException
         {  
 		 HttpServletRequest request=(HttpServletRequest)req;
         HttpServletResponse response=(HttpServletResponse)resp;
         response.addHeader("Content-Security-Policy", "frame-ancestors localhost:8080");
         chain.doFilter(request,response);
        }  
    public void destroy() {

    }  
} 