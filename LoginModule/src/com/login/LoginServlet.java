package com.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.io.StringWriter;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
 

public class LoginServlet extends HttpServlet {
    private static final String CONTENT_TYPE =
        "text/html; charset=windows-1252";
   
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException,
                                                             IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession();
        response.setContentType(CONTENT_TYPE);
        
        HttpResponse response1 = ApiUtil.executeAuthenticate("Prasad");
            
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>LoginServlet</title></head>");
        out.println("<body>");
        out.println("<form>");
        out.println("<p><b>Login success</b> </p>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("<br>");
        out.println("<a href=\"getcity\">Get Cities</a>");
        out.println("</form>");
        out.println("</body></html>");
        out.print("HTTP Response from Authenticate API:====>"+response1);
         
        InputStream is=null;
        is = ApiUtil.getResponseInputStream(response1);
        String jsonResponse =ApiUtil.convertStreamToString(is);     
        //out.println("Authenticate API jsonResponse:===>"+jsonResponse);

        out.close();

        
        //request.getRequestDispatcher("../Login.html").include(request, response);

    }
    
    


}
