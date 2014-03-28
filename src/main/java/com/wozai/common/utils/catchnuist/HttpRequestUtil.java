package com.wozai.common.utils.catchnuist;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

/**
 * Http������
 * 
 * @author 
 *
 */
public class HttpRequestUtil {

	public static final String REQUEST_TYPE_GET = "get";
	public static final String REQUEST_TYPE_POST = "post";
	public static final DefaultHttpClient httpClient = new DefaultHttpClient();
	public static final Map<String,String> p = new HashMap<String, String>();
	public static final Map<String,Object> info = new HashMap<String, Object>();
	static {
        p.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0");
        p.put("Host","wlkt.nuist.edu.cn");
        p.put("Connection","keep-alive");
        p.put("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        p.put("Accept-Encoding","gzip, deflate");
        p.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	}

	public static HttpResponse request(String uri,UrlEncodedFormEntity urlentity, String type, String encoding,SimpleLogin simpleLogin) throws ClientProtocolException, IOException{
		String result = "";
		HttpResponse httpResponse = null;
		httpClient.setCookieStore(simpleLogin.cookie);
		// GET��ʽ����
		if(HttpRequestUtil.REQUEST_TYPE_GET.equals(type)){
			HttpGet httpGet = new HttpGet(uri);
			httpResponse = httpClient.execute(httpGet);
			info.put("page", getPage(httpResponse,encoding));
			httpGet.releaseConnection();
		// POST��ʽ����
		}else if(HttpRequestUtil.REQUEST_TYPE_POST.equals(type)){
			HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader("Referer", uri);
			for(Object key : p.keySet()){
				httpPost.addHeader((String)key, (String)p.get(key));
			}
			// �����������
			httpPost.setEntity(urlentity);
			httpResponse = httpClient.execute(httpPost);
            simpleLogin.cookie = httpClient.getCookieStore();
			info.put("page", getPage(httpResponse,encoding));
			httpPost.releaseConnection();
		}
//		Map<String,String> map = new HashMap<String,String>();
//		// ��ȡ��������
//		HttpEntity entity = httpResponse.getEntity();
//		if(entity!=null){
//			InputStream is = entity.getContent();
//			int l ;
//			byte[] buff = new byte[9192];
//			while( (l = is.read(buff)) != -1){
//				result += new String(buff, 0, l, encoding);
//			}
//		}
//		httpResponse.getStatusLine();
//		map.put("page", result);
//		StringUtils.setParam(map,result);
//		return map;
		return httpResponse;
	}
	public static String getPage(HttpResponse httpResponse,String encoding) throws IllegalStateException, IOException{
		StringBuffer result = new StringBuffer();
		HttpEntity entity = httpResponse.getEntity();
		if(entity!=null){
			InputStream is = entity.getContent();
			int l ;
			byte[] buff = new byte[9192];
			while( (l = is.read(buff)) != -1){
				result.append(new String(buff, 0, l, encoding));
			}
		}
		info.put("page", result);
		return result.toString();
	}
	public static Map<String,Object> getPage(String url,HttpResponse httpResponse,SimpleLogin simpleLogin) throws IllegalStateException, IOException, InterruptedException{
		Map<String,Object> info = new HashMap<String, Object>();
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			info.put("httpResponse", httpResponse);
			info.put("url", url);
			return info;
		}
		if(httpResponse.getStatusLine().getStatusCode() == 302){
			Header[] headers = httpResponse.getAllHeaders();
			String lastUrl = "";
			for(Header header : headers){
				if(header.getName().equals("Location")){
					lastUrl = header.getValue();
					info.put("url", simpleLogin.URL+lastUrl);
				}
			}
			httpResponse = request(simpleLogin.URL+lastUrl, null, REQUEST_TYPE_GET, "UTF-8",simpleLogin);
			info.put("httpResponse", httpResponse);
			if(httpResponse.getStatusLine().getStatusCode() == 302){
				return getPage(simpleLogin.URL+lastUrl,httpResponse,simpleLogin);
			}
			return info;
		}
		return null;
	}
}