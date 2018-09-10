package com.krishna.domain;

public class BallPit implements PlaySite {

	private int capacity;
	
	private int totalOccupied;

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public int getTotalOccupied() {
		return this.totalOccupied;
	}

	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
		
	}

	@Override
	public void setTotalOccupied(int totalOccupied) {
		this.totalOccupied = totalOccupied;
		
	}

	@Override
	public void update(PlaySite playSite) {		
		this.capacity = this.capacity + playSite.getCapacity();
		
	}

}
