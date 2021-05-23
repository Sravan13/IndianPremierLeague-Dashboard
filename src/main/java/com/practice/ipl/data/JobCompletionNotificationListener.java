package com.practice.ipl.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.practice.ipl.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  //private final JdbcTemplate jdbcTemplate;
  
  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");
      
      Map<String, Team> teamData = new HashMap<>();
      
		/*
		 * Function<Object[], Team> function = new Function<Object[], Team>() {
		 * 
		 * @Override public Team apply(Object[] t) {
		 * 
		 * return new Team((String)t[0], (long)t[1]); } };
		 */
      
      Function<Object[], Team> function =  (t)-> new Team((String)t[0], (long)t[1]);
      
      Consumer<Team>  consumer = t -> teamData.put(t.getTeamName(), t);
	
      
      entityManager.createQuery("select m.team1, count(*) from Match m group by team1",Object[].class).getResultList().stream().map( function ).forEach(consumer);
      
      entityManager.createQuery("select m.team2, count(*) from Match m group by team2",Object[].class).getResultList().stream().forEach(e->{
    	 Team team =  teamData.get(e[0]);
    	 team.setTotalMatches(team.getTotalMatches()+(long)e[1]);
      });
      
      entityManager.createQuery("select m.winner, count(*) from Match m group by matchWinner",Object[].class).getResultList().stream().forEach(e->{
     	 Team team =  teamData.get(e[0]);
     	 if(team!=null)
     	 team.setTeamWins((long)e[1]);
       });
    
      teamData.values().forEach(team->entityManager.persist(team));
      teamData.values().forEach(System.out::println);
      
      
    }
  }
}