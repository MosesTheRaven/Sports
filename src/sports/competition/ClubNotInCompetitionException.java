package sports.competition;

import sports.base.SportClub;

public class ClubNotInCompetitionException extends Exception {
	private static final long serialVersionUID = 1L;
	private SportClub club;
	
	public ClubNotInCompetitionException(SportClub club) {
		this.club = club;
	}
	
	public String toString(){
		return "Nepovolená snaha pridať príkaz týkajúci sa klubu mimo súťaže - " + club.getName();
	}

}
