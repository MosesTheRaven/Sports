package State;

import Subject.NotEnoughMoneyException;
import Subject.Owner;
import sports.base.SportClub;
import sports.competition.Competition;

public interface State {
    
    void resolveNewSeason(int startovne);
    void resolveNewPoints(Competition competition);
    void buyClub(SportClub club, int value) throws NotEnoughMoneyException;
}
