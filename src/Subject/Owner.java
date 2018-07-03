package Subject;

import Observer.Observer;
import State.InsolventState;
import State.NormalState;
import State.State;
import java.util.ArrayList;
import java.util.List;
import sports.base.SportClub;
import sports.competition.Competition;

public class Owner implements Observer{

    private String meno;
    private int kapital;
    private List<SportClub> kluby;
    public State state;

    public Owner(String meno, int kapital) {
        this.meno = meno;
        this.kapital = kapital;
        this.kluby = new ArrayList<>();
        this.state = new NormalState(this);
    }
    
    /*
    pridane kvoli spracovaniu stavov
    */
    public List<SportClub> getClubs(){
        return this.kluby;
    }
    public void setKapital(int kapital){
        this.kapital = kapital;
    }
    public void addToKapital(int add){
        this.kapital += add;
        if(kapital > 0 && state instanceof InsolventState) state = new NormalState(this);
    }
    public void substractToKapital(int sub){
        this.kapital -= sub;
        if(kapital < 0 && state instanceof NormalState) state = new InsolventState(this);
    }
    public int getKapital(){
        return this.kapital;
    }
    public void addClub(SportClub club){
        this.kluby.add(club);
    }
    
    
    public void sellClub(SportClub club, int value){
        this.kluby.remove(club);
        this.addToKapital(value);
        
    }
    
    public boolean doYouOwn(SportClub club){
        return this.kluby.contains(club);
    }
    
    public String getName(){
        return this.meno;
    }
    
    @Override
    public String toString(){
        String retVal = "Majitel " + this.meno + " ma kapital " + this.kapital + "$ a vlastni kluby:";
        if(!this.kluby.isEmpty()){
            for(SportClub club : this.kluby){
                retVal += "\n\t" + club.getName();
            }
        }
        else retVal += " ziadne";
        return retVal;
    }
    
    
    @Override
    public void update(MyNotification notification) {
        if(notification instanceof NewSeasonNotification) {
            this.state.resolveNewSeason(((NewSeasonNotification) notification).startovne);
        }
        
        if(notification instanceof NewPointsNotification) {
        
            this.state.resolveNewPoints(((NewPointsNotification) notification).competition);
            
        }
        //tu by sme pridali dalsie riesenie notifikacii
    }

    
    
}
