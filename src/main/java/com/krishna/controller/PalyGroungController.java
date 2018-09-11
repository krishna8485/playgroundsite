package com.krishna.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krishna.InMemory.InMemory;
import com.krishna.common.Constants;
import com.krishna.domain.BallPit;
import com.krishna.domain.Carousel;
import com.krishna.domain.DoubleSwing;
import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;
import com.krishna.domain.Slide;



public class PalyGroungController {
	
	private static Logger logger = LoggerFactory.getLogger(PalyGroungController.class);
	
	Map<String, PlaySite> playSiteMap = InMemory.getPlaySiteMap();
	LinkedList<Player> playerQueue = InMemory.getPlayersQueue();
	
	public void addPlaySites(PlaySite ... playSites) {
		
		for (PlaySite playSite:playSites) {
			
			if(playSite instanceof BallPit) {
				updateInMemory(Constants.BALLPIT, playSite);
				
			} else if(playSite instanceof Carousel) {
				updateInMemory(Constants.CAROUSEL, playSite);
				
			} else if(playSite instanceof Slide) {
				updateInMemory(Constants.SLIDE, playSite);
				
			} else if(playSite instanceof DoubleSwing) {
				updateInMemory(Constants.DOUBLESWING, playSite);				
			}			
		}	
		
	}
	
	private void updateInMemory(String playsiteName,  PlaySite playSite) {
		if (playSiteMap.get(playsiteName)==null) {
			playSiteMap.put(playsiteName, playSite);
			
		} else {
			PlaySite exitingPlaySite = playSiteMap.get(playsiteName);
			exitingPlaySite.update(playSite);
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
			placeInQueue( player);
		}
		
		
	}
	
	/**
	 * 
	 * @param player
	 */
	private void placeInQueue(Player player) {
		if ("VIP".equalsIgnoreCase(player.getTicketType())) {
			
		} else {
			playerQueue.add(player);
		}
		
	}
	
	/**
	 * 
	 */
	public void removePlayer() {
		
		checkuser is queue or playsite
		
		if he in queue remove from queue
		
		or remove from playsite and assign player to playsite who is waiting in queue
		
		
	}
	
	/**
	 * 
	 * @param playSite
	 * @return
	 */
	private boolean checkAvailablity(String playSite) {
		if(playSiteMap.get(playSite) !=null && playSiteMap.get(playSite).isAvailale()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param player
	 * @throws NoPlaySiteException
	 */
	private void allotPlayer2Site(Player player) throws NoPlaySiteException{
		if(playSiteMap.get(player.getPlaySite()) !=null ) {
			playSiteMap.get(player.getPlaySite()).addPlayer(player);
		} else {
			throw new NoPlaySiteException("No Play Site");
		}
		
	}
	
	

}
