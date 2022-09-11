package com.sung.campingResevation.webCrawling;

import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WangsongLake {
	
	@Value("${url}")
    private String url;
	
	ObjectMapper om;
	
	public WangsongLake() {
		om = new ObjectMapper();
	}
	
	public String execute() {
		
		String result = "";		
		
		if( getUrl() ) {
			result = "Go Reservation to 왕송호수캠핑장";
		}
		return result;
	}
	
	private boolean getUrl() {
		
	    try {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        
	        //GET 방식으로 parameter를 전달
	        HttpGet httpGet = new HttpGet(url);

	        CloseableHttpResponse response = httpclient.execute(httpGet);
	        try {
	        	int responseCode = response.getStatusLine().getStatusCode();
	        	switch(responseCode) {
	        	case 200 :
	        		String responseStr = EntityUtils.toString(response.getEntity());
	        		Map<String, Object> map = om.readValue(responseStr, Map.class);
	        		List<Map<String, Object>> list = (List)((Map)map.get("data")).get("remainSeat");
	        		for(Map<String, Object> m : list) {
	        			if( (m.get("seatGradeName").equals("카라반사이트B")) && ((int)m.get("remainCnt")>0) ) {
	        				return true;
	        			}
	        			
	        		}
	        		break;
	        	default :
	        		System.out.println("Response Code : " + responseCode);
	        		break;
	        	}
	            
	        } finally {
	            response.close();
	        }   
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false;
	}

}