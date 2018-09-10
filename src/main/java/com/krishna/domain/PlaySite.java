package com.krishna.domain;

public interface PlaySite {
	
	
	
	public int getCapacity();
	public int getTotalOccupied();
	
	public void setCapacity(int capacity);
	public void setTotalOccupied(int totalOccupied);
	
	public void update (PlaySite playSite) ;

}
