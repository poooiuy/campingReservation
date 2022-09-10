package campingReservation_sosunam;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawlingTest {

//	static String url = "https://soseonamcamp.dytc.or.kr:455/reservation.asp?location=002";
	static String url = "https://camp.dytc.or.kr:452/include/reservation/rsv_list.asp?hcode=A&fcode=1&ycode=2022&mcode=9";
	
	public static void main(String[] args) throws IOException {
		
		Document doc = Jsoup.connect(url).post();
//		Document doc = Jsoup.connect(url.concat(getUrl())).get();
		System.out.println(doc.toString());
	}
	
	public static String getUrl() {
		String result = "";
		
		result = "?wh_year=2022&wh_month=9&man=1&x=43&y=11";
		return result;
	}

	
}


/*
https://soseonamcamp.dytc.or.kr:455//images/main/main_visual01.jpg
https://camp.dytc.or.kr:452/images/common/banner_03.jpg
https://camp.dytc.or.kr:452/images/main/main_visual01.jpg
*/