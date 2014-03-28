package com.wozai.cache;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zengzihao on 2014/3/25.
 */
public class HttpLoginHelper implements Serializable {
    private static String url = "http://wlkt.nuist.edu.cn/(S(mfwuze45scrhq5451idrrx55))/default.aspx";
    private static String serverUrl = "http://localhost:8080/mobile/loginByNuist.htm";
    private static String searchUrl = "http://localhost:8080/mobile/search.htm?lat=118.726503&lng=32.212224";
    private static String __VIEWSTATE = "	/wEPDwUKMTM5MjUxOTk4Nw9kFgJmD2QWHgICDxAPFgIeB1Zpc2libGVoZGQWAWZkAgMPEA8WAh8AaGRkZGQCBA8QDxYCHwBoZGRkZAIFDxAPFgIeBFRleHQFCeaVmeWKoeWkhGRkZGQCBg8QDxYCHwBoZGRkZAIHDxAPFgIfAQUG5a2m6ZmiZGRkZAIIDxAPFgIfAQUG5a2m5YqeZGRkZAIJDxAPFgIfAGhkZGRkAgoPEA8WAh8BBQbmlZnluIhkZGRkAgsPEA8WAh8BBQblrabnlJ9kZGRkAgwPEA8WAh8AaGRkZGQCDQ8QDxYCHwBoZGRkZAIODxAPFgIfAGhkZGRkAg8PEA8WAh8AaGRkZGQCEA8QDxYCHwBoZGRkZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WCQUMUmFkaW9CdXR0b240BQxSYWRpb0J1dHRvbjQFDFJhZGlvQnV0dG9uMgUMUmFkaW9CdXR0b24yBQxSYWRpb0J1dHRvbjUFDFJhZGlvQnV0dG9uNQUMUmFkaW9CdXR0b24xBQxSYWRpb0J1dHRvbjEFDFJhZGlvQnV0dG9uM8hkENze68v6MFI9hwYrSEN4tT9A";    private static String js = "RadioButton3";
    private static String Button1 = "登陆";
    public static Boolean LoginCheck(String username,String password) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Referer", url);
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("TextBox1", username));
        formparams.add(new BasicNameValuePair("TextBox2", password));
        formparams.add(new BasicNameValuePair("js", "RadioButton3"));
        formparams.add(new BasicNameValuePair("Button1", "登陆"));
        formparams.add(new BasicNameValuePair("__VIEWSTATE",__VIEWSTATE));
        UrlEncodedFormEntity urlentity;
        urlentity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPost.setEntity(urlentity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 302){
            for(Header header :  httpResponse.getAllHeaders()){
                if(header.getName().contains("Location") && header.getValue().contains("newslist")){
                    return true;
                }
            }
        }
        httpPost.abort();
        return false;
    }

    public static Boolean Login4Server(String username,String password) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serverUrl);
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("password", password));
        UrlEncodedFormEntity urlentity;
        urlentity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPost.setEntity(urlentity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = httpResponse.getEntity();
            if(entity!=null){
                InputStream is = entity.getContent();
                int l ;
                String result = "";
                byte[] buff = new byte[9192];
                while( (l = is.read(buff)) != -1){
                    result += new String(buff, 0, l, "UTF-8");
                }
                if(result.contains("SUCCESS")){
                    return true;
                }else {
                    return false;
                }
            }
        }
        httpPost.abort();
        return false;
    }

    public static Boolean Login4Search() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpPost = new HttpGet(searchUrl);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = httpResponse.getEntity();
            if(entity!=null){
                InputStream is = entity.getContent();
                int l ;
                String result = "";
                byte[] buff = new byte[9192];
                while( (l = is.read(buff)) != -1){
                    result += new String(buff, 0, l, "UTF-8");
                }
               if (result.contains("星期")){
                   return true;
               }else {
                   return false;
               }
            }
        }
        httpPost.abort();
        return false;
    }
    public static void main(String args[]) throws IOException {
        Runnable m = new Runnable(){
            @Override
            public void run() {
                int i = 0;
                int j = 0;
                Long d = 0L;
                Long maxTime= 0L;
                Long minTime = 0L;
                while(true){
                    j++;
                    try {
                        Date date = new Date();
                        if(HttpLoginHelper.Login4Server("20101309076", "19920424")&& Login4Search()){
                            i++;
                            System.out.println(i + "==========================" + j);
                        }
                        Long time  = (new Date().getTime()-date.getTime());
                        if(time > maxTime){
                            maxTime = time;
                        }
                        if(minTime ==0 || minTime > time){
                            minTime = time;
                        }
                        d += time;
                        System.out.println( "系统本次消耗用时 " + time +"MS");
                        System.out.println( "系统最大消耗用时 " + maxTime +"MS");
                        System.out.println( "系统最小消耗用时 " + minTime +"MS");
                        System.out.println( j+"次系统总消耗用时 " + d +"MS");
                        System.out.println( "系统平均消耗用时 " + (d/j) +"MS");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for(int i = 0 ; i < 100 ; i++){
            new Thread(m).start();
        }

    }
}
