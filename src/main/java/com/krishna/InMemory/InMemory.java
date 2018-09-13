package com.krishna.InMemory;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;


@Scope(value = "singleton")
@Service
public class InMemory {
	
	private static ConcurrentHashMap<String, PlaySite> playSiteMap;
	
	private static ConcurrentHashMap<String, Player> playerAuditMap;
	
	private static LinkedList<Player> playersQueue;
	
	/**
	 * 
	 * @return
	 */
	public static Map<String, PlaySite> getPlaySiteMap() {
		if (playSiteMap!=null) {
			return playSiteMap;
		} else {
			playSiteMap = new ConcurrentHashMap<String, PlaySite>();
			return playSiteMap;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String, Player> getPlayerAudit() {
		if (playerAuditMap!=null) {
			return playerAuditMap;
		} else {
			playerAuditMap = new ConcurrentHashMap<String, Player>();
			return playerAuditMap;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static LinkedList<Player> getPlayersQueue() {
		if (playersQueue!=null) {
			return playersQueue;
		} else {
			playersQueue = new LinkedList<Player>();
			return playersQueue;
		}
	}
	
	/**
	 * 
	 */
	private InMemory () { 
		playSiteMap =  new ConcurrentHashMap<String, PlaySite>();
		playerAuditMap =  new ConcurrentHashMap<String, Player>();
		playersQueue = new LinkedList<Player>();
		
    }
	
	/**
	 * 
	 * @param playsiteName
	 * @param playSite
	 */
	public void updateInMemory(String playsiteName,  PlaySite playSite) {
		if (playSiteMap.get(playsiteName)==null) {
			playSiteMap.put(playsiteName, playSite);
			
		} else {
			PlaySite exitingPlaySite = playSiteMap.get(playsiteName);
			exitingPlaySite.update(playSite);
		}
	}
	

}
