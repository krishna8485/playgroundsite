package com.krishna;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.krishna.InMemory.InMemory;
import com.krishna.domain.BallPit;
import com.krishna.domain.Carousel;
import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;
import com.krishna.domain.Slide;
import com.krishna.exception.NoPlaySiteException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaygroundsiteApplicationTests {


	@Autowired
	PlayGround playGround;
	
	@Autowired
	InMemory inMemory;
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void playGroundTest() {
		inMemory.getPlayersQueue().clear();
		
		PlaySite [] playSites = { new Slide(1)};
		playGround.addPlaySites(playSites);
		
		assertEquals(playGround.getPlaySites().size(), 3);
		
	}
	
	@Test(expected = NoPlaySiteException.class)
	public void allotPlayer() throws NoPlaySiteException {
		inMemory.getPlayersQueue().clear();
		PlaySite [] playSites = { new Slide(0)};
		playGround.addPlaySites(playSites);
		Player slidePlayer = new Player();
		slidePlayer.setPlaySite("slide");
		playGround.allotPlayer(slidePlayer);
		
	}
	
	@Test
	public void allotPlayerScenario1() {
		inMemory.getPlayersQueue().clear();
		
		PlaySite [] playSites = { new Slide(1)};
		playGround.addPlaySites(playSites);
		

		assertEquals(inMemory.getPlaySiteMap().get("SLIDE").getCapacity(),1);
		assertEquals(inMemory.getPlaySiteMap().get("SLIDE").getCurrentPlayers().size(), 0);
		
		Player slidePlayer = new Player();
		slidePlayer.setPlaySite("SLIDE");
		slidePlayer.setTicketType("NONVIP");
		slidePlayer.setName("balu");
		
		try {
			playGround.allotPlayer(slidePlayer);
		} catch (NoPlaySiteException e) {
			assertEquals(e.getMessage(), "No PlaySite or Zero Capacity") ;
		}
		
		Player slidePlayer1 = new Player();
		slidePlayer1.setPlaySite("SLIDE");
		slidePlayer1.setTicketType("NONVIP");
		slidePlayer1.setName("krishna");
		try {
			playGround.allotPlayer(slidePlayer);
		} catch (NoPlaySiteException e) {
			assertEquals(e.getMessage(), "No PlaySite or Zero Capacity") ;
		}
		
		LinkedList<Player> linkedList= inMemory.getPlayersQueue();
		
		
		assertEquals(linkedList.size(), 1);
		
	}
	
	@Test
	public void allotPlayerScenario2() {
		
		inMemory.getPlayersQueue().clear();
		
		PlaySite [] playSites = { new BallPit(1)};
		playGround.addPlaySites(playSites);
		
		assertEquals(inMemory.getPlaySiteMap().get("BALLPIT").getCapacity(),1);
		assertEquals(inMemory.getPlaySiteMap().get("BALLPIT").getCurrentPlayers().size(), 0);
		
		try {
	
			Player slidePlayer = new Player();
			slidePlayer.setPlaySite("BALLPIT");
			slidePlayer.setTicketType("NONVIP");
			slidePlayer.setName("krishna1");
			playGround.allotPlayer(slidePlayer);
			
	
			Player slidePlayer1 = new Player();
			slidePlayer1.setTicketType("NONVIP");
			slidePlayer1.setName("krishna2");
			slidePlayer1.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer1);
			
			
			Player slidePlayer2 = new Player();
			slidePlayer2.setTicketType("NONVIP");
			slidePlayer2.setName("krishna3");
			slidePlayer2.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer2);
			
			Player slidePlayer3 = new Player();
			slidePlayer3.setTicketType("VIP");
			slidePlayer3.setName("balu");
			slidePlayer3.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer3);
			
			Player slidePlayer4 = new Player();
			slidePlayer4.setTicketType("VIP");
			slidePlayer4.setName("balu2");
			slidePlayer4.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer4);
			
			
			Player slidePlayer5 = new Player();
			slidePlayer5.setTicketType("NONVIP");
			slidePlayer5.setName("krishna4");
			slidePlayer5.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer5);
			
			
			Player slidePlayer6 = new Player();
			slidePlayer6.setTicketType("VIP");
			slidePlayer6.setName("balu3");
			slidePlayer6.setPlaySite("BALLPIT");
			playGround.allotPlayer(slidePlayer6);


		}catch (NoPlaySiteException e) {
			assertEquals(e.getMessage(), "No PlaySite or Zero Capacity") ;
		}
		
		//System.out.println(playGround.getWaitingPlayers().toString());
		
		assertEquals(playGround.getWaitingPlayer().toString(), "Player [name=krishna2, age=null, ticketType=NONVIP, playSite=BALLPIT]");
		
	}
	
	@Test
	public void allotPlayerScenario3() {
		
		inMemory.getPlayersQueue().clear();
		
		PlaySite [] playSites = { new Carousel(1)};
		playGround.addPlaySites(playSites);
		
		assertEquals(inMemory.getPlaySiteMap().get("CAROUSEL").getCapacity(),1);
		assertEquals(inMemory.getPlaySiteMap().get("CAROUSEL").getCurrentPlayers().size(), 0);
		
		try {
			
			Player slidePlayer = new Player();
			slidePlayer.setPlaySite("CAROUSEL");
			slidePlayer.setTicketType("NONVIP");
			slidePlayer.setName("krishna");
			playGround.allotPlayer(slidePlayer);
	
			Player slidePlayer0 = new Player();
			slidePlayer0.setPlaySite("CAROUSEL");
			slidePlayer0.setTicketType("NONVIP");
			slidePlayer0.setName("krishna1");
			playGround.allotPlayer(slidePlayer0);
			
	
			Player slidePlayer1 = new Player();
			slidePlayer1.setTicketType("NONVIP");
			slidePlayer1.setName("krishna2");
			slidePlayer1.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer1);
			
			
			Player slidePlayer2 = new Player();
			slidePlayer2.setTicketType("NONVIP");
			slidePlayer2.setName("krishna3");
			slidePlayer2.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer2);
			
			Player slidePlayer3 = new Player();
			slidePlayer3.setTicketType("VIP");
			slidePlayer3.setName("balu");
			slidePlayer3.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer3);
			
			Player slidePlayer4 = new Player();
			slidePlayer4.setTicketType("VIP");
			slidePlayer4.setName("balu2");
			slidePlayer4.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer4);
			
			
			Player slidePlayer5 = new Player();
			slidePlayer5.setTicketType("NONVIP");
			slidePlayer5.setName("krishna4");
			slidePlayer5.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer5);
			
			
			Player slidePlayer6 = new Player();
			slidePlayer6.setTicketType("VIP");
			slidePlayer6.setName("balu3");
			slidePlayer6.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer6);


		}catch (NoPlaySiteException e) {
			assertEquals(e.getMessage(), "No PlaySite or Zero Capacity") ;
		}
		
		//System.out.println(playGround.getWaitingPlayers().toString());
		
		assertEquals(playGround.getWaitingPlayer().toString(), "Player [name=balu, age=null, ticketType=VIP, playSite=CAROUSEL]");
		
		//System.out.println(playGround.getUtilization().toString());
	}
	
	@Test
	public void allotPlayerScenario4() {
		
		inMemory.getPlayersQueue().clear();
		
		PlaySite [] playSites = { new Carousel(1)};
		playGround.addPlaySites(playSites);
		
		assertEquals(inMemory.getPlaySiteMap().get("CAROUSEL").getCapacity(),2);
		assertEquals(inMemory.getPlaySiteMap().get("CAROUSEL").getCurrentPlayers().size(), 1);
		
		try {
			
			Player slidePlayer = new Player();
			slidePlayer.setPlaySite("CAROUSEL");
			slidePlayer.setTicketType("NONVIP");
			slidePlayer.setName("krishna");
			playGround.allotPlayer(slidePlayer);
	
			Player slidePlayer0 = new Player();
			slidePlayer0.setPlaySite("CAROUSEL");
			slidePlayer0.setTicketType("NONVIP");
			slidePlayer0.setName("krishna1");
			playGround.allotPlayer(slidePlayer0);
			
	
			Player slidePlayer1 = new Player();
			slidePlayer1.setTicketType("NONVIP");
			slidePlayer1.setName("krishna2");
			slidePlayer1.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer1);
			
			
			Player slidePlayer2 = new Player();
			slidePlayer2.setTicketType("NONVIP");
			slidePlayer2.setName("krishna3");
			slidePlayer2.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer2);
			
			Player slidePlayer3 = new Player();
			slidePlayer3.setTicketType("VIP");
			slidePlayer3.setName("balu");
			slidePlayer3.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer3);
			
			Player slidePlayer4 = new Player();
			slidePlayer4.setTicketType("VIP");
			slidePlayer4.setName("balu2");
			slidePlayer4.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer4);
			
			
			Player slidePlayer5 = new Player();
			slidePlayer5.setTicketType("NONVIP");
			slidePlayer5.setName("krishna4");
			slidePlayer5.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer5);
			
			
			Player slidePlayer6 = new Player();
			slidePlayer6.setTicketType("VIP");
			slidePlayer6.setName("balu3");
			slidePlayer6.setPlaySite("CAROUSEL");
			playGround.allotPlayer(slidePlayer6);


		}catch (NoPlaySiteException e) {
			assertEquals(e.getMessage(), "No PlaySite or Zero Capacity") ;
		}
		
		assertEquals(playGround.getWaitingPlayers().toString(), "[Player [name=balu, age=null, ticketType=VIP, playSite=CAROUSEL], Player [name=krishna1, age=null, ticketType=NONVIP, playSite=CAROUSEL], Player [name=krishna2, age=null, ticketType=NONVIP, playSite=CAROUSEL], Player [name=krishna3, age=null, ticketType=NONVIP, playSite=CAROUSEL], Player [name=balu2, age=null, ticketType=VIP, playSite=CAROUSEL], Player [name=krishna4, age=null, ticketType=NONVIP, playSite=CAROUSEL], Player [name=balu3, age=null, ticketType=VIP, playSite=CAROUSEL]]");
		assertEquals(playGround.getUtilization().toString(), "{CAROUSEL=100%, SLIDE=100%, BALLPIT=100%}");
		//System.out.println(playGround.getUtilization().toString());
	}


}
