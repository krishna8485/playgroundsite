package com.krishna.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

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

	private  Map<String, PlaySite> playSiteMap ;
	private  LinkedList<Player> playerQueue ;
	private  Map<String, List<String>> reportMap;
	
	@PostConstruct
	public void init() {		
		playSiteMap = inMemory.getPlaySiteMap();
		playerQueue = inMemory.getPlayersQueue();
		reportMap = inMemory.getReportMap();
	}
	
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
	 * @return
	 */
	public Map<String, PlaySite> getPlaySites() {
		return playSiteMap;
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
	
	public  void placeInQueue(Player player) {
		if (Constants.VIP.equalsIgnoreCase(player.getTicketType())) {
			//log.info("placeInQueue--VIP");
			if (playerQueue.size()==0) {
				playerQueue.add(player);				
			}else {
				//log.info("placeInQueue--VIP- new");
				String lastVOcc= checkExistingVip();
				if (!lastVOcc.contains("Y")) {
					if (playerQueue.size()==3) {
						playerQueue.addFirst(player);
					}else if (playerQueue.size()<3){
						playerQueue.add(player);
					}
				} else {
					updateQueueWithV(lastVOcc.split("-")[1], player);
					return;
				}
			}			
		} else { 
			//log.info("placeInQueue--NON VIP- new");
			playerQueue.add(player);
		}
		
	}
	
	/**
	 * 	
	 * @return
	 */
	private  String checkExistingVip() {
		int pos = 0;
		StringBuffer sb =  new StringBuffer();;
		
		for (Player player : playerQueue) {
			++pos;
			if(Constants.VIP.equalsIgnoreCase(player.getTicketType())) {
				sb=new StringBuffer();
				sb.append("Y-").append(Integer.toString(pos));
			}			
		}
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @param pos
	 * @param player
	 */
	private  void updateQueueWithV (String pos, Player player) {
		int size = playerQueue.size();	
		int nextPos = Integer.parseInt(pos)+3+1;
		if (size <3) {
			playerQueue.add(player);
		}else {
			if (size == nextPos) {
				playerQueue.add(player);
			}else if ((size > nextPos)) {
				playerQueue.add(nextPos-1, player);					
			}else {
				playerQueue.add(player);
			}
		}
	}
	
	/**
	 * 
	 * @param player
	 */
	public void removePlayer(Player player) {
		log.info("removePlayer");		
		if (playerQueue.contains(player)) {
			playerQueue.remove(player);			
		} else {
			log.info("removePlayer- Else");
			player.setOutTime(getCurrentDate());
			report(player);
			playSiteMap.remove(player.getPlaySite()).addPlayer(player);
			
		}	
		
	}
	
	private void report(Player player) {
		 if (reportMap.get(player.getTicketNumber())!=null) {
			 reportMap.get(player.getTicketNumber()).add(player.getPlaySite());
		 } else {
			 List<String> list = new ArrayList<>();
			 list.add(player.getPlaySite());
			 reportMap.put(player.getTicketNumber(), list);
		 }
	}
	
	
	
	
	/**
	 * 
	 * @param playSite
	 * @return
	 * @throws NoPlaySiteException
	 */
	private boolean checkAvailablity(String playSite) throws NoPlaySiteException {
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
	
	/**
	 * 
	 * @param player
	 * @throws NoPlaySiteException
	 */
	private void allotPlayer2Site(Player player) throws NoPlaySiteException{
		log.info("allotPlayer2Site");
		if(playSiteMap.get(player.getPlaySite()) !=null ) {
			player.setInTime(getCurrentDate());
			playSiteMap.get(player.getPlaySite()).addPlayer(player);
		} else {
			log.info("No Play Site");
			throw new NoPlaySiteException("No Play Site");
		}
		
	}

	/**
	 * 
	 * @param ticketNumber
	 * @return
	 */
	public List<String> playSitesByKid(String ticketNumber) {
		return reportMap.get(ticketNumber);
		
		
	}

	/**
	 * 
	 * @return
	 */
	public int totalVisitorCount() {
		return reportMap.size();
		
	}

	/**
	 * Return utilization 
	 * @return
	 */
	public Map<String, String> getUtilization() {
		Map<String, String> utilPercentage =  new HashMap<String, String>();
		playSiteMap.forEach((key,value) ->{
			log.info(key + " = " + value);
			int percentage = 0;/*
			if (value.getCapacity()-value.getCurrentPlayers().size() ==0) {
				percentage =100;
			} else {*/
			 percentage = (value.getCurrentPlayers().size()*100/value.getCapacity());
			//}
			utilPercentage.put(key, percentage+"%");
		});
		return utilPercentage;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getWaitingPlayer() {
		return playerQueue.pop();
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<Player>  getWaitingPlayers() {
		return playerQueue;
	}

	
}
