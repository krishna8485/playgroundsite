package com.krishna.Business;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

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
	

	Map<String, PlaySite> playSiteMap = InMemory.getPlaySiteMap();
	LinkedList<Player> playerQueue = InMemory.getPlayersQueue();
	
	/**
	 * 
	 * @param playSites
	 */
	
	public void addPlaySites(PlaySite ... playSites) {
		
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
			allotPlayer2Site(player);			
		} else {
			placeInQueue(player);
		}
		
		
	}
	
	/**
	 * 
	 * @param player
	 */
	public void placeInQueue(Player player) {
		if ("VIP".equalsIgnoreCase(player.getTicketType())) {
			if (playerQueue.size()>0) {
				playerQueue.add(0, player);				
			}else {
				playerQueue.add(player);
			}			
		} else {
			playerQueue.add(player);
		}
		
	}
	
	public void removePlayer(Player player) {
		
		if (playerQueue.contains(player)) {
			playerQueue.remove(playerQueue);			
		} else {
			player.setOutTime(getCurrentDate());
			playSiteMap.remove(player.getPlaySite()).addPlayer(player);
			
		}	
		
	}
	
	public boolean checkAvailablity(String playSite) {
		if(playSiteMap.get(playSite) !=null && playSiteMap.get(playSite).isAvailale()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	private Date getCurrentDate() {
		long millis=System.currentTimeMillis();  
		java.util.Date date=new java.util.Date(millis); 
		return date;
	}
	
	public void allotPlayer2Site(Player player) throws NoPlaySiteException{
		if(playSiteMap.get(player.getPlaySite()) !=null ) {
			player.setInTime(getCurrentDate());
			playSiteMap.get(player.getPlaySite()).addPlayer(player);
		} else {
			throw new NoPlaySiteException("No Play Site");
		}
		
	}
	
	

}
