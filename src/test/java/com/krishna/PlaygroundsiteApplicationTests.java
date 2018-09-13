package com.krishna;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.krishna.InMemory.InMemory;
import com.krishna.controller.PlayGround;
import com.krishna.domain.PlaySite;
import com.krishna.domain.Slide;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaygroundsiteApplicationTests {


	@Autowired
	PlayGround playGround;
	
	/*@Autowired
	InMemory inMemory;*/
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void playGroundTest() {
		PlaySite [] playSites = { new Slide(1)};
		playGround.addPlaySites(playSites);
		System.out.println(InMemory.getPlaySiteMap().values().toString());
	}
	

}
