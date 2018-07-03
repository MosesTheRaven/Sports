package sports.command;

import java.util.ArrayList;
import java.util.List;

import sports.base.SportClub;

public class PenalizationCommand extends Command{
	private SportClub club;
	private int points;
	
	public PenalizationCommand(SportClub club, int points){
		this.club = club;
		this.points = points;
	}
	
	public void execute() {
		club.removePoints(points);
	}

	@Override
	public List<SportClub> getClubs() {
		List<SportClub> clubs = new ArrayList<>();
		clubs.add(club);
		
		return clubs;
	}
	
	public String toString(){
		return "<PEN> " + club.getName() + " - " + points + " bodov";
	}
}
