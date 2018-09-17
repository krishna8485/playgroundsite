package com.krishna.InMemory;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;


@Scope(value = "singleton")
@Service
public class InMemory {
	
	private ConcurrentHashMap<String, PlaySite> playSiteMap;
	
	private ConcurrentHashMap<String, List<String>> reportingMap;
	
	private  LinkedList<Player> playersQueue;
	
	/**
	 * 
	 * @return
	 */
	public Map<String, PlaySite> getPlaySiteMap() {
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
	public Map<String, List<String>>getReportMap() {
		if (reportingMap!=null) {
			return reportingMap;
		} else {
			reportingMap = new ConcurrentHashMap<String, List<String>>();
			return reportingMap;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public  LinkedList<Player> getPlayersQueue() {
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
	public InMemory () { 
		playSiteMap =  new ConcurrentHashMap<String, PlaySite>();/*
		 List<String> playSites = 
		         Collections.synchronizedList(new ArrayList());*/
		reportingMap =  new ConcurrentHashMap<String,  List<String>>();
		playersQueue = new LinkedList<Player>();
		
    }
	
	/**
	 * 
	 * @param playsiteName
	 * @param playSite
	 */
	public  void updateInMemory(String playsiteName,  PlaySite playSite) {
		if (playSiteMap.get(playsiteName)==null) {
			playSiteMap.put(playsiteName, playSite);
			
		} else {
			PlaySite exitingPlaySite = playSiteMap.get(playsiteName);
			exitingPlaySite.update(playSite);
		}
	}
	

}
