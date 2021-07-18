package com.practice.ipl.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ipl.model.Match;
import com.practice.ipl.model.Team;
import com.practice.ipl.repository.MatchRepository;
import com.practice.ipl.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	
	TeamRepository teamRepository;
	MatchRepository matchRepository;

	public TeamController(TeamRepository teamRepository,MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}
	
	@GetMapping({"/team/{teamName}"})
	public Team getTeam(@PathVariable("teamName") String teamName) {
		Team team =  teamRepository.findByTeamName(teamName);
		List<Match> matchs =  matchRepository.findLatestMatchesByTeam(teamName, 4);
		team.setMatches(matchs);
		return team;
	}
	
	@GetMapping({"/team/{teamName}/matches"})
	public List<Match> getTeam(@PathVariable("teamName") String teamName, @RequestParam int year) {
		
		 LocalDate startDate =  LocalDate.of(year, 1, 1);
		 LocalDate endDate =   LocalDate.of(year+1, 1, 1);
		
		List<Match> matchs =  matchRepository.getByTeamBetweenDates(teamName, startDate, endDate);
		return matchs;
	}
	
	

}
