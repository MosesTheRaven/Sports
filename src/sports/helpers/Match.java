package sports.helpers;

import sports.base.SportClub;

public class Match {
	private int homeScore;
	private int awayScore;
	private SportClub home;
	private SportClub away;
	
	public Match(SportClub home, SportClub away, int homeScore, int awayScore){
		this.home = home;
		this.away = away;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}
	
	public String toString(){
		return home.getName() + " vs. " + away.getName() + " ---> " + homeScore + ":" + awayScore;
	}
	
	public MatchResult getResult(){
		if(homeScore > awayScore){
			return MatchResult.HOME_WON;
		}
		else if(awayScore > homeScore){
			return MatchResult.AWAY_WON;
		}
		else{
			return MatchResult.DRAW;
		}
	}
	
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public SportClub getHome() {
		return home;
	}
	public void setHome(SportClub home) {
		this.home = home;
	}
	public SportClub getAway() {
		return away;
	}
	public void setAway(SportClub away) {
		this.away = away;
	}
	
		
}
