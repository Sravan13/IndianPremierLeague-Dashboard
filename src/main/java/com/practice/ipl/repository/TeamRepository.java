package com.practice.ipl.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.ipl.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	public Team findByTeamName(String teamName);

}
