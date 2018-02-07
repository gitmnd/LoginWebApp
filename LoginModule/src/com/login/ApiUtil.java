package com.login;

import java.io.IOException;
import java.io.InputStream;

import java.io.StringWriter;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.Map;

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

public class ApiUtil {
    public static final String CONTENT_TYPE1 = "Content-Type";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    private static final int timeout = 15;
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_VALUE = "application/json";
    public static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    
    public static HttpResponse executeAuthenticate(String arg) throws IOException {
        
        String uri = "http://localhost:9082/springbootapp/Authenticate/"+arg;
        URI url = null;
        try {
            url = new URI(uri);
        } catch (URISyntaxException ex) {            
            throw new IOException("URL could not be created", ex);
        } 
        
        CloseableHttpClient client = getClient();
        HttpRequestBase base = getMethod(url, METHOD_POST);
        
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Accept-Encoding","gzip, deflate");
        setHeaderValues(base, headerMap);
       
        
        HttpPost postRequest = (HttpPost)base;        
        return client.execute(postRequest);
        
    }
    
    public static HttpResponse executeCity() throws IOException {
        
        String uri = "http://localhost:9082/springbootapp/getCity/";
        URI url = null;
        try {
            url = new URI(uri);
        } catch (URISyntaxException ex) {            
            throw new IOException("URL could not be created", ex);
        } 
        
        CloseableHttpClient client = getClient();
        HttpRequestBase base = getMethod(url, METHOD_POST);
        
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headerMap.put("Content-Type","application/json;charset=UTF-8");
        headerMap.put("Accept-Encoding","gzip, deflate");
        setHeaderValues(base, headerMap);
        
        HttpPost postRequest = (HttpPost)base;        
        return client.execute(postRequest);
        
    }
    
    public static String convertStreamToString(InputStream inputStream) throws IOException {

        
        if (inputStream == null) {

            return "";
        }

        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } catch (IOException e) {


            throw e;
        }
        return writer.toString();
    }
    public static InputStream getResponseInputStream(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "UTF-8");
        return IOUtils.toInputStream(content, "UTF-8");
    }

    private static CloseableHttpClient getClient() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient client =  HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        return client;
    }
    
    
    private static void setHeaderValues(HttpRequestBase method, Map<String,String> headerMap){
        for(String key : headerMap.keySet()){
            method.setHeader(key, headerMap.get(key));
        }
    }
    
    private static HttpRequestBase getMethod(URI url, String method){
        if(method.equalsIgnoreCase(METHOD_POST)){
            return new HttpPost(url);
        }
        else if(method.equalsIgnoreCase(METHOD_PUT)){
            return new HttpPut(url);
        }
        else if(method.equalsIgnoreCase(METHOD_DELETE)){
            return new HttpDelete(url);
        }
        else{
            return new HttpGet(url);
        }
    }
}
