import { React } from 'react';
import {Link} from 'react-router-dom';

export  const MatchDetailCard = ({teamName,match}) =>  {

    if(!match) return null;

    const otherTeam = teamName == match.team1 ? match.team2 : match.team1;
    const otherTeamLink = `/teams/${otherTeam}`;

    return (
      <div className="TeamPage">
        <h1>Team Names</h1>
        <h3>Latest matches</h3>
        <h4>Match Details</h4>
        <h1> vs <Link to={otherTeamLink}>{otherTeam} </Link></h1>
        <h2> {match.date}</h2>
        <h3> at {match.venue}</h3>
        <h3>{match.winner} won by {match.resultMargin} {match.result}</h3>
      </div>
    );
  };