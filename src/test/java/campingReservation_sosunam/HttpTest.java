package campingReservation_sosunam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpTest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String url = "";
//		getUrl(url);
		
		call();
		
	}
	

	
	public static void call() throws ClientProtocolException, IOException {
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
		cookie.setDomain("soseonamcamp.dytc.or.kr");
		cookie.setPath("/");
		cookieStore.addCookie(cookie);
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

		HttpPost request = new HttpPost("https://soseonamcamp.dytc.or.kr:455/reservation.asp?location=002");
//		HttpPost request = new HttpPost("https://camp.dytc.or.kr:452/reservation.asp?location=001&hcode=A");
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("wh_year", "2022"));
	    params.add(new BasicNameValuePair("wh_month", "9"));
	    params.add(new BasicNameValuePair("man", "1"));
	    request.setEntity(new UrlEncodedFormEntity(params));
	    

		CloseableHttpResponse response = client.execute(request);
	    
		int responseCode = response.getStatusLine().getStatusCode();
		
		String responseStr = EntityUtils.toString(response.getEntity());
		System.out.println(responseStr);

//		assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
	}
	
	
	private static boolean getUrl(String url) {
		
		ObjectMapper om = new ObjectMapper();
		
	    try {
	        
	        HttpClient httpClient = new HttpClient();
			httpClient.start();
			Request request = httpClient.POST("https://soseonamcamp.dytc.or.kr:455/reservation.asp?location=002");
			request.header(HttpHeader.CONTENT_TYPE, "application/x-www-form-urlencoded");
	        request.content(new StringContentProvider("{\"wh_year\":\"2022\",\"wh_month\":\"9\",\"man\":\"1\"}","utf-8"));
			ContentResponse contentRes = request.send();
			String res = contentRes.getContentAsString();
	        System.out.println(res);
	        
	        
	        
	        try {
//	        	int responseCode = response.getStatusLine().getStatusCode();
	        	int responseCode =200;
	        	switch(responseCode) {
	        	case 200 :
	        		/*
	        		String responseStr = EntityUtils.toString(response.getEntity());
	        		Map<String, Object> map = om.readValue(responseStr, Map.class);
	        		List<Map<String, Object>> list = (List)((Map)map.get("data")).get("remainSeat");
	        		for(Map<String, Object> m : list) {
	        			if( (m.get("seatGradeName").equals("카라반사이트B")) && ((int)m.get("remainCnt")>0) ) {
	        				return true;
	        			}
	        			
	        		}
	        		*/
	        		break;
	        	default :
	        		System.out.println("Response Code : " + responseCode);
	        		break;
	        	}
	            
	        } finally {
//	            response.close();
	        }   
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false;
	}

}
