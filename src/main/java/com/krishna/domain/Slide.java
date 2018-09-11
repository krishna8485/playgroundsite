package com.krishna.domain;

import java.util.List;

public class Slide implements PlaySite {
	
private int capacity;
	
	private int totalOccupied;

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public List<Player> getTotalOccupied() {
		return this.totalOccupied;
	}

	

	@Override
	public void update(PlaySite playSite) {
		// TODO Auto-generated method stub
		
	}

}
