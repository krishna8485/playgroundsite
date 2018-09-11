package com.krishna.InMemory;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;


@Scope(value = "singleton")
@Service
public class InMemory {
	
	private static Map<String, PlaySite> playSiteMap;
	
	private static Map<String, Player> playerAuditMap;
	
	private static LinkedList<Player> playersQueue;
	
	public static Map<String, PlaySite> getPlaySiteMap() {
		if (playSiteMap!=null) {
			return playSiteMap;
		} else {
			playSiteMap = new HashMap<String, PlaySite>();
			return playSiteMap;
		}
	}
	
	public static Map<String, Player> getPlayerAudit() {
		if (playerAuditMap!=null) {
			return playerAuditMap;
		} else {
			playerAuditMap = new HashMap<String, Player>();
			return playerAuditMap;
		}
	}
	
	public static LinkedList<Player> getPlayersQueue() {
		if (playersQueue!=null) {
			return playersQueue;
		} else {
			playersQueue = new LinkedList<Player>();
			return playersQueue;
		}
	}
	
	private InMemory () { 
		playSiteMap =  new HashMap<String, PlaySite>();
		playerAuditMap =  new HashMap<String, Player>();
		playersQueue = new LinkedList<Player>();
		
    }
	
	
	

}
