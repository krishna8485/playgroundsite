package com.krishna.domain;

import java.util.ArrayList;
import java.util.List;

public class Slide implements PlaySite {

	
private int capacity;

private List<Player> currentPlayers;

public Slide(int capacity) {
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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + capacity;
	result = prime * result + ((currentPlayers == null) ? 0 : currentPlayers.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Slide other = (Slide) obj;
	if (capacity != other.capacity)
		return false;
	if (currentPlayers == null) {
		if (other.currentPlayers != null)
			return false;
	} else if (!currentPlayers.equals(other.currentPlayers))
		return false;
	return true;
}

@Override
public String toString() {
	return "Slide [capacity=" + capacity + ", currentPlayers=" + currentPlayers + "]";
}

}
