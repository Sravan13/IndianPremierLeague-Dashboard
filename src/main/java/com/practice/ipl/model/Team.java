package com.practice.ipl.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String teamName;
	private long totalMatches;
	private long teamWins;
	
	@Transient
	private List<Match> matches;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public long getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(long teamMatches) {
		this.totalMatches = teamMatches;
	}
	public long getTeamWins() {
		return teamWins;
	}
	public void setTeamWins(long teamWins) {
		this.teamWins = teamWins;
	}
		
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public Team() {
	}
	
	public Team(String teamName, long teamMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = teamMatches;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Team name :: "+teamName+" total matches :: "+totalMatches+" teamWins :: "+teamWins;
	}
	
	
}
