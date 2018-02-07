package com.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.http.HttpResponse;

public class GetCity extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        
        HttpResponse response1 = ApiUtil.executeCity();
        
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Get City API Invoked</title></head>");
        out.println("<body>");
        out.println("<form>");
        out.println("<a href=\"loginservlet\">Home</a>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("<br>");       
        out.println("</form>");
        out.println("</body></html>");
        out.print("HTTP Response from Get City API API:====>"+response1);
        
        
        InputStream is=null;
        is = ApiUtil.getResponseInputStream(response1);
        String jsonResponse =ApiUtil.convertStreamToString(is);     
        out.println("City API jsonResponse:===>"+jsonResponse);
    }
}
