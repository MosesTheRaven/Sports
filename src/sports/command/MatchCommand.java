package sports.command;

import java.util.ArrayList;
import java.util.List;

import sports.base.SportClub;
import sports.helpers.Match;

public class MatchCommand extends Command {
	private Match match;
	
	public MatchCommand(SportClub home, SportClub away, int homeScore, int awayScore){
		match = new Match(home, away, homeScore, awayScore);
	}
	
	public String toString(){
		return "<MATCH> " + match.toString();
	}
	
	@Override
	public void execute() {
		switch (match.getResult()) {
		case HOME_WON:
			match.getHome().addPoints(2);
			break;
		case AWAY_WON:
			match.getAway().addPoints(2);
			break;
		case DRAW:
			match.getHome().addPoints(1);
			match.getAway().addPoints(1);
		}
	}

	@Override
	public List<SportClub> getClubs() {
		List<SportClub> clubs = new ArrayList<>();
		clubs.add(match.getHome());
		clubs.add(match.getAway());
		
		return clubs;
	}
	
}
