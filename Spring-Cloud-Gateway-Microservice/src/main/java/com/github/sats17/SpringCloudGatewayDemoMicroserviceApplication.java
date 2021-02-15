package com.github.sats17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.sats17.dao.HeroLayer;
import com.github.sats17.dao.VillanLayer;
import com.github.sats17.model.Heros;
import com.github.sats17.model.Villans;

@SpringBootApplication
public class SpringCloudGatewayDemoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayDemoMicroserviceApplication.class, args);
	}
	
	@Autowired
	HeroLayer heros;
	
	
	
	@Bean
	public void setHeros() {
		Heros test = new Heros();
		String[] fName = { "satish", "bruse", "iron", "bat", "rocky" };
		String[] lName = { "kumbhar", "lee", "man", "man", "bhai" };
		String[] carreer = { "Developer", "fighter", "scientist", "hero", "brand" };
		for (int i = 0; i < 5; i++) {
			test.setId(i + 1);
			test.setFirst_name(fName[i]);
			test.setLast_name(lName[i]);
			test.setCareer(carreer[i]);
			heros.save(test);
		}
	}
	
	@Autowired
	VillanLayer villans;
	
	@Bean
	public void setVillans() {
		Villans test = new Villans();
		String[] fName = {"satish", "chong", "loki", "joker", "garuda"};
		String[] lName = {"kumbhar", "ching", "asgardian", "depressed", "reddy"};
		String[] job = {"hacker", "destroyer", "theif", "slice-theif", "don"};
		for(int i = 0; i<5; i++) {
			test.setId(i+1);
			test.setFirst_name(fName[i]);
			test.setLast_name(lName[i]);
			test.setJob(job[i]);
			villans.save(test);
		}
	}
	


}
