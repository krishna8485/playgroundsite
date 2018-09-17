package com.krishna.domain;

import java.util.Date;

public class Player {
	private String name;
	private String age;
	private String ticketNumber;
	private String ticketType;
	private String playSite;
	private Date inTime;
	private Date outTime;
	

	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	/*private List<String> playedSites;*/
	
	public String getPlaySite() {
		return playSite;
	}
	public void setPlaySite(String playSite) {
		this.playSite = playSite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	/*
	public List<String> getPlayedSites() {
		return playedSites;
	}
	public void setPlayedSites(List<String> playedSites) {
		this.playedSites = playedSites;
	}*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playSite == null) ? 0 : playSite.hashCode());
		result = prime * result + ((ticketType == null) ? 0 : ticketType.hashCode());
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
		Player other = (Player) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playSite == null) {
			if (other.playSite != null)
				return false;
		} else if (!playSite.equals(other.playSite))
			return false;
		if (ticketType == null) {
			if (other.ticketType != null)
				return false;
		} else if (!ticketType.equals(other.ticketType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", age=" + age + ", ticketType=" + ticketType + ", playSite=" + playSite + "]";
	}

}
