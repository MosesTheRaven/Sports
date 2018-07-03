package State;

import Subject.NotEnoughMoneyException;
import Subject.Owner;
import sports.base.SportClub;
import sports.competition.Competition;

public class NormalState implements State{
    Owner owner;

    public NormalState(Owner owner) {
        this.owner = owner;
    }
    
    
    /*
    tu predpokladam, ze majitel ma vsetky kluby v jednej sutazi a preto maju vsetky rovnake startovne 
    prislo mi to ako najjednoduchsie riesenie - takto si nemusim pamatat o kluboch 
        v akych su sutaziach a ani pisat getter na startovne sutaze, v ktorej sa nachadza klub
    */
    @Override
    public void resolveNewSeason(int startovne) {
        owner.substractToKapital(owner.getClubs().size()*startovne); 
    }
    
    /*
    z hladiska "realnosti" je tento pristup podla mna nezmyselny
    majitel klubu dostane peniaze z reklamy aj ked jeho klub prehral
    podla mna by bolo lepsie (a jednoznacne zaujimavejsie) odmenovat majitelov podla vykonu ich klubov
        t.j. napriklad za vyhru (+3 body vo futbale) by majitel dostal 3x1000, za remizu (+1 bod vo futbale) len 1000 a za prehru nic 
    */
    @Override
    public void resolveNewPoints(Competition competition) {
        for(SportClub club : owner.getClubs()) {
            //sutazi som pridal boolean hasClub aby som zistil, ci je klub v sutazi
            if(competition.hasClub(club)) owner.addToKapital(1000*club.getPoints());
        }
    }
    @Override
    public void buyClub(SportClub club, int value) throws NotEnoughMoneyException{
        if(owner.getKapital() >= value){
            owner.addClub(club);
            owner.substractToKapital(value);
        }
        throw new NotEnoughMoneyException();
    }
}
