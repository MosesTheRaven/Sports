package State;

import Subject.NotEnoughMoneyException;
import Subject.Owner;
import sports.base.SportClub;
import sports.competition.Competition;

public class InsolventState implements State{
    Owner owner;

    public InsolventState(Owner owner) {
        this.owner = owner;
    }
    @Override
    public void resolveNewSeason(int startovne) {
        //literally does nothing :D 
    }

    @Override
    public void resolveNewPoints(Competition competition) {
        for(SportClub club : owner.getClubs()) {
            //sutazi som pridal boolean hasClub aby som zistil, ci je klub v sutazi
            if(competition.hasClub(club)) owner.addToKapital(1000*club.getPoints());
        }
    }

    @Override
    public void buyClub(SportClub club, int value) throws NotEnoughMoneyException {
        throw new NotEnoughMoneyException();
    }
    
}
