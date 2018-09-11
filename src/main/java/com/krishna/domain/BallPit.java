package com.krishna.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class BallPit implements PlaySite {

	private int capacity;
	
	private List<Player> currentPlayers;
	
	private BallPit(int capacity) {
		this.capacity=capacity;
		currentPlayers=new ArrayList<Player>();
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public List<Player>  getCurrentPlayers() {
		return this.currentPlayers;
	}
	
	@Override 
	public void addPlayer(Player player) {
		this.currentPlayers.add(player);
	}

	@Override
	public void update(PlaySite playSite) {		
		this.capacity = this.capacity + playSite.getCapacity();
		
	}

	@Override
	public boolean isAvailale() {
	   if ((this.capacity-this.currentPlayers.size())>0 ) {		   
		   return true;
	   } else {
		   return false;
	   }
		
	}

}
