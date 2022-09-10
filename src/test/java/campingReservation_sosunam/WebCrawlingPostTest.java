package campingReservation_sosunam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

public class WebCrawlingPostTest {

	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0";

	private final static String urlPost = "https://forest.maketicket.co.kr/camp/reserve/calendar.jsp";

	// main class
	public static void main(String[] args) throws Exception {
		sendPost();
	}

	// HTTP Post request
	private static void sendPost() throws Exception {

		Map<String, String> postData = new HashMap<>();
		postData.put("idkey", "5M4280");
		postData.put("gd_seq", "GD91");
		postData.put("yyyymmdd", "20220910");
		postData.put("sd_date", "20220909");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Cookie", "SERVERID=web-server1; SCOUTER=z50osp37bfrhcd; _ga=GA1.3.157294394.1662646530; _gid=GA1.3.987354086.1662646530; JSESSIONID=2C2793ECA3AA38230564E4327F2B769B; SERVERID=web-server1; SCOUTER=z50osp37bfrhcd; _ga=GA1.3.157294394.1662646530; JSESSIONID=BFAD40CC900136A89E07AC879C964666; _gid=GA1.3.1230256279.1662812607; _gat=1");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
		headers.put("Host", "forest.maketicket.co.kr");
		headers.put("Origin", "https://forest.maketicket.co.kr");
		headers.put("Referer", "https://forest.maketicket.co.kr/ticket/GD91");
		headers.put("X-Requested-With", "XMLHttpRequest");

		Document doc = Jsoup.connect(urlPost).ignoreContentType(true).headers(headers).userAgent(USER_AGENT).data(postData).post();
		List<Node> list =  doc.getElementById("calendar_24").child(1).childNodes();

		for(Node n : list) {
			for(Node n2 : n.childNodes()) {
				int val = Integer.parseInt(n2.firstChild().firstChild().toString());
				if(val > 0	) {
					
				}
			}
		}
	}

}
/*

Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6
Connection: keep-alive
Content-Length: 59
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
Cookie: SERVERID=web-server1; SCOUTER=z50osp37bfrhcd; _ga=GA1.3.157294394.1662646530; _gid=GA1.3.987354086.1662646530; JSESSIONID=2C2793ECA3AA38230564E4327F2B769B; SERVERID=web-server1; SCOUTER=z50osp37bfrhcd; _ga=GA1.3.157294394.1662646530; JSESSIONID=BFAD40CC900136A89E07AC879C964666; _gid=GA1.3.1230256279.1662812607; _gat=1
Host: forest.maketicket.co.kr
Origin: https://forest.maketicket.co.kr
Referer: https://forest.maketicket.co.kr/ticket/GD91
sec-ch-ua: "Chromium";v="104", " Not A;Brand";v="99", "Google Chrome";v="104"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: same-origin
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36
X-Requested-With: XMLHttpRequest
*/