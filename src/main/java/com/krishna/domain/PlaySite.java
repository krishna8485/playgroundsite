package com.krishna.domain;

import java.util.List;

public interface PlaySite {
	
	public int getCapacity();
	
	public void update (PlaySite playSite) ;
	
	public boolean isAvailale();
	List<Player> getCurrentPlayers();

	void addPlayer(Player player);

}
