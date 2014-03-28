package com.wozai.cache;

import org.springframework.stereotype.Component;

/**
 * Created by zengzihao on 2014/3/25.
 */
@Component
public class LoginValueMap {
    private static String url = "http://wlkt.nuist.edu.cn/(S(1lca2b55efak4xupiuof3n2g))/default.aspx";
    private static String serverUrl = "http://localhost:8080/mobile/loginByNuist.htm";
    private static String __VIEWSTATE = "/wEPDwUKMTM5MjUxOTk4Nw9kFgJmD2QWHgICDxAPFgIeB1Zpc2libGVoZGQWAWZkAgMPEA8WAh8AaGRkZGQCBA8QDxYCHwBoZGRkZAIFDxAPFgIeBFRleHQFCeaVmeWKoeWkhGRkZGQCBg8QDxYCHwBoZGRkZAIHDxAPFgIfAQUG5a2m6ZmiZGRkZAIIDxAPFgIfAQUG5a2m5YqeZGRkZAIJDxAPFgIfAGhkZGRkAgoPEA8WAh8BBQbmlZnluIhkZGRkAgsPEA8WAh8BBQblrabnlJ9kZGRkAgwPEA8WAh8AaGRkZGQCDQ8QDxYCHwBoZGRkZAIODxAPFgIfAGhkZGRkAg8PEA8WAh8AaGRkZGQCEA8QDxYCHwBoZGRkZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WCQUMUmFkaW9CdXR0b240BQxSYWRpb0J1dHRvbjQFDFJhZGlvQnV0dG9uMgUMUmFkaW9CdXR0b24yBQxSYWRpb0J1dHRvbjUFDFJhZGlvQnV0dG9uNQUMUmFkaW9CdXR0b24xBQxSYWRpb0J1dHRvbjEFDFJhZGlvQnV0dG9uM8hkENze68v6MFI9hwYrSEN4tT9A";    private static String TextBox1;

    private static String js = "RadioButton3";
    private static String Button1 = "登陆";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        LoginValueMap.url = url;
    }

    public static String getServerUrl() {
        return serverUrl;
    }

    public static void setServerUrl(String serverUrl) {
        LoginValueMap.serverUrl = serverUrl;
    }

    public static String get__VIEWSTATE() {
        return __VIEWSTATE;
    }

    public static void set__VIEWSTATE(String __VIEWSTATE) {
        LoginValueMap.__VIEWSTATE = __VIEWSTATE;
    }

    public static String getTextBox1() {
        return TextBox1;
    }

    public static void setTextBox1(String textBox1) {
        TextBox1 = textBox1;
    }

    public static String getJs() {
        return js;
    }

    public static void setJs(String js) {
        LoginValueMap.js = js;
    }

    public static String getButton1() {
        return Button1;
    }

    public static void setButton1(String button1) {
        Button1 = button1;
    }
}
