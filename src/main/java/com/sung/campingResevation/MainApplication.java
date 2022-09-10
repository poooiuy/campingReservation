package com.sung.campingResevation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.sung.campingResevation.notifyLine.SendMessage;
import com.sung.campingResevation.webCrawling.JaraIsland;

@SpringBootApplication
@EnableScheduling
public class MainApplication {
	
	@Value("${jaraIsland.execute}")
    private Boolean jaraIslandExecute;
	
	@Value("${wangsongLake.execute}")
    private Boolean wangsongLakeExecute;
	
	@Autowired
	JaraIsland jaraIsland;
	
	@Autowired
	SendMessage sendMessage;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
	@Scheduled(fixedRateString="${interval}")
	private void invoke() {
		String result = "";
		System.out.println("==================>   Invoked at : " + new Date());
		
		if(jaraIslandExecute) {
			result = jaraIsland.execute();
			if(!result.equals("")) {
				sendMessage.send(result);
			}
		}
		
		String result = callWebService.execute();
		if(!result.equals("")) {
			sendMessage.send(result);
		}
	}
	

}
