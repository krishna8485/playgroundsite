package com.krishna;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krishna.Business.PlayGroundBusiness;
import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;
import com.krishna.exception.NoPlaySiteException;


@Component
public class PlayGround {
	
	@Autowired 
	PlayGroundBusiness playGroundBusiness;
	
	private static Logger logger = LoggerFactory.getLogger(PlayGround.class);	
	
	/**
	 * Use this method to add new PlaySites
	 * @param playSites
	 */
	public void addPlaySites(PlaySite ... playSites) {
		//TODO: Validate input data 
		playGroundBusiness.addPlaySites(playSites);	
	
	}
	
	public Map<String, PlaySite>  getPlaySites() {
		//TODO: Validate input data 
		return playGroundBusiness.getPlaySites();	
	
	}
	
	/**
	 * Use this method to add player to Site
	 * 
	 * @param player
	 * @throws NoPlaySiteException 
	 */
	public void allotPlayer(Player player) throws NoPlaySiteException {
		//TODO: Validate input data 
		playGroundBusiness.allotPlayer(player);
		
	}	
	
	/**
	 * Remove the player from Site/Queue
	 * 
	 * @param player
	 */
	public void removePlayer(Player player) {
		//TODO: Validate input data 
		playGroundBusiness.removePlayer(player);		
	}
	
	public List<String> playSitesByKid(String ticketNumber) {
		//TODO: Validate input data 
		return playGroundBusiness.playSitesByKid(ticketNumber);
		
	}
	
	public int totalVisitorCount() {
		return playGroundBusiness.totalVisitorCount();
		
	}
	
	public Map<String, String> getUtilization() {
		//TODO: Validate input data 
		return playGroundBusiness.getUtilization();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getWaitingPlayer() {
		return playGroundBusiness.getWaitingPlayer();
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<Player> getWaitingPlayers() {
		return playGroundBusiness.getWaitingPlayers();
	}
	
	
}
