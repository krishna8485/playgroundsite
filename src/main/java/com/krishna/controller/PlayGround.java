package com.krishna.controller;

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
	
	//private static Logger logger = LoggerFactory.getLogger(PlayGround.class);	
	
	public void addPlaySites(PlaySite ... playSites) {
		//TODO: Add Data Validation 
		playGroundBusiness.addPlaySites(playSites);	
	
	}
	
	
	/**
	 * 
	 * @param player
	 * @throws NoPlaySiteException 
	 */
	public void allotPlayer(Player player) throws NoPlaySiteException {
		
		//TODO: Add Data Validation 
		playGroundBusiness.allotPlayer(player);
		
	}
	
	/**
	 * 
	 * @param player
	 */
	public void placeInQueue(Player player) {
		
		//TODO: Add Data Validation 
		playGroundBusiness.placeInQueue(player);
	}
	
	/**
	 * 
	 * @param player
	 */
	public void removePlayer(Player player) {
		
		//TODO: Add Data Validation 
		playGroundBusiness.removePlayer(player);
		
	}
	
	/**
	 * 
	 * @param playSite
	 * @return
	 * @throws NoPlaySiteException 
	 */
	public boolean checkAvailablity(String playSite) throws NoPlaySiteException {
		//TODO: Add Data Validation 
		return playGroundBusiness.checkAvailablity(playSite);
		
	}
	
	/**
	 * 
	 * @param player
	 * @throws NoPlaySiteException
	 */
	private void allotPlayer2Site(Player player) throws NoPlaySiteException{
		playGroundBusiness.allotPlayer2Site(player);
	}
	
}
