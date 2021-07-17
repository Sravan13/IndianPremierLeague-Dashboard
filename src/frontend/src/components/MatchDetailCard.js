import { React } from 'react';

export  const MatchDetailCard = ({match}) =>  {

    if(!match) return null;

    return (
      <div className="TeamPage">
        <h1>Team Names</h1>
        <h3>Latest matches</h3>
        <h4>Match Details</h4>
        <h4>{match.team1} vs {match.team2}</h4>
      </div>
    );
  };