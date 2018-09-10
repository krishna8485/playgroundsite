package com.krishna.InMemory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.krishna.domain.PlaySite;
import com.krishna.domain.Player;

public class InMemory {
	
	private static Map<String, PlaySite> playSiteMap;
	
	private static Map<String, Player> playerMap;
	
	public static Map<String, PlaySite> getPlaySiteMap() {
		if (playSiteMap!=null) {
			return playSiteMap;
		} else {
			return new HashMap<String, PlaySite>();
		}
	}
	
	public static Map<String, Player> getplayerMap() {
		if (playerMap!=null) {
			return playerMap;
		} else {
			return new HashMap<String, Player>();
		}
	}
	
	private InMemory () { 
		playSiteMap =  new HashMap<String, PlaySite>();
		playerMap =  new HashMap<String, Player>();
		
    }
	
	    
	/*public void addPlaySite(String playSiteName, PlaySite playSite) {    	
    	playSiteMap.put(playSiteName, playSite);
    }
	    
	public List<PlaySite> getPlaySite(String playSiteName) {
	    return playSiteMap.get(playSiteName);
	}*/
	
	
	/*public Map<String, List<PlaySite>> getInstance() {
		if (playSiteMap!=null) {
			return playSiteMap;
		} else {
			return new HashMap<String, List<PlaySite>>();
		}
	}
	
	private InMemory () { 
		//playSiteList = new ArrayList<PlaySite>();
		playSiteMap =  new HashMap<String, List<PlaySite>>();
		
    }
	
    private Map<String, List<PlaySite>> playSiteMap;
    //private static List<PlaySite> playSiteList;
    
    public void setPlaySite(String playSiteName, PlaySite playSite) {    	
    	playSiteMap.get(playSiteName).add(playSite);
    }
    
    public List<PlaySite> getPlaySite(String playSiteName) {
        return playSiteMap.get(playSiteName);
    }
	*/
	

}
