package com.krishna.Business;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krishna.InMemory.InMemory;
import com.krishna.common.Constants;
import com.krishna.exception.NoPlaySiteException;
import com.krishna.domain.BallPit;
import com.krishna.domain.Carousel;
import com.krishna.domain.DoubleSwing;
import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;
import com.krishna.domain.Slide;

@Component
public class PlayGroundBusiness {
	@Autowired
	InMemory inMemory;
	
	Logger log = LoggerFactory.getLogger(PlayGroundBusiness.class);
	

	Map<String, PlaySite> playSiteMap = InMemory.getPlaySiteMap();
	LinkedList<Player> playerQueue = InMemory.getPlayersQueue();
	
	/**
	 * 
	 * @param playSites
	 */
	
	public void addPlaySites(PlaySite ... playSites) {
		log.info("addPlaySites");
		
		for (PlaySite playSite:playSites) {
			
			if(playSite instanceof BallPit) {
				inMemory.updateInMemory(Constants.BALLPIT, playSite);
				
			} else if(playSite instanceof Carousel) {
				inMemory.updateInMemory(Constants.CAROUSEL, playSite);
				
			} else if(playSite instanceof Slide) {
				inMemory.updateInMemory(Constants.SLIDE, playSite);
				
			} else if(playSite instanceof DoubleSwing) {
				inMemory.updateInMemory(Constants.DOUBLESWING, playSite);				
			}			
		}	
		
	}
	
	/**
	 * 
	 * @param player
	 * @throws NoPlaySiteException
	 */
	public void allotPlayer(Player player) throws NoPlaySiteException {
		
		if (checkAvailablity(player.getPlaySite())) {

			log.info("allotPlayer--available");
			allotPlayer2Site(player);			
		} else {
			log.info("allotPlayer--placeInQueue");
			placeInQueue(player);
		}
		
		
	}
	
	/**
	 * TODO need add VIP logic 
	 * @param player
	 */
	public void placeInQueue(Player player) {
		if ("VIP".equalsIgnoreCase(player.getTicketType())) {
			log.info("placeInQueue--VIP");
			if (playerQueue.size()>0) {
				playerQueue.add(0, player);				
			}else {
				log.info("placeInQueue--VIP- new");
				playerQueue.add(player);
			}			
		} else {

			log.info("placeInQueue--NON VIP- new");
			playerQueue.add(player);
		}
		
	}
	
	public void removePlayer(Player player) {
		log.info("removePlayer");		
		if (playerQueue.contains(player)) {
			playerQueue.remove(playerQueue);			
		} else {
			log.info("removePlayer- Else");
			player.setOutTime(getCurrentDate());
			playSiteMap.remove(player.getPlaySite()).addPlayer(player);
			
		}	
		
	}
	
	public boolean checkAvailablity(String playSite) throws NoPlaySiteException {
		log.info("checkAvailablity");
		if(playSiteMap.get(playSite) !=null &&  playSiteMap.get(playSite).getCapacity()!=0) {
			if (playSiteMap.get(playSite).isAvailale()) {
			return true;
			}
		} else {
			throw new NoPlaySiteException("No PlaySite or Zero Capacity");
		}
		return false;
		
	}
	
	/**
	 * 
	 * @return
	 */
	private Date getCurrentDate() {
		long millis=System.currentTimeMillis();  
		java.util.Date date=new java.util.Date(millis); 
		log.info("getCurrentDate");
		return date;
	}
	
	public void allotPlayer2Site(Player player) throws NoPlaySiteException{
		log.info("allotPlayer2Site");
		if(playSiteMap.get(player.getPlaySite()) !=null ) {
			player.setInTime(getCurrentDate());
			playSiteMap.get(player.getPlaySite()).addPlayer(player);
		} else {
			log.info("No Play Site");
			throw new NoPlaySiteException("No Play Site");
		}
		
	}
	
	

}
