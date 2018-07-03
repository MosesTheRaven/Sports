package sports.competition;

import Observer.Observer;
import Subject.MyNotification;
import Subject.NewPointsNotification;
import Subject.NewSeasonNotification;
import Subject.Owner;
import Subject.Subject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sports.base.*;
import sports.base.factory.SportClubFactory;
import sports.command.Command;

/*
Competition bude nas emmiter eventov
bude mat observerov - kluby v sutazi (nema zmysel ich pridavat este raz do nejakeho ineho listu)
    a este nejakych owners
pridal som takisto startovne pre klub
pridal som hasClub

state sa meni, ked sa odcita/pricita od kapitalu

*/
public class Competition extends Subject implements Serializable {
    private List <SportClub> clubs;
    private List <Command> commands;
    

    public int startovne = 2000;
    
    private List<Observer> owners;
    
    public boolean hasClub(SportClub club){
        return this.clubs.contains(club);
    }
    
    private void addClubsToList(SportOrganization so, List<SportClub> list, SportClubFactory factory){
        for (SportItem si: so.getSubOrganizations()) {
            if (si instanceof SportClub){
                SportClub sc=factory.createSportClub((SportClub)si);
                list.add(sc);
            } else if (si instanceof SportOrganization) {
                addClubsToList((SportOrganization)si, list, factory);
            }
        }
    }
    
    public Competition(SportItem si, SportClubFactory factory){
    	clubs=new ArrayList<>();
        commands = new ArrayList<>();
        
        assert(si instanceof SportOrganization);
        addClubsToList((SportOrganization)si, clubs, factory);
        owners = new ArrayList<Observer>();
    }
    
    public Competition(){
    }
    
    public void printClubs(){
    	System.out.println("Vypisujem Sutaz:");
    	int i = 0;
        for (SportClub sc: clubs){
        	System.out.print("[" + i + "] - ");
            System.out.println(sc.toStringWithPoints());
            i++;
        }
    }
    
    public SportClub getClub(int index){
    	return clubs.get(index);
    }
    
    public void addCommand(Command cmd) throws ClubNotInCompetitionException{
    	List<SportClub> cmdsClubs = cmd.getClubs();
    	
    	for(SportClub cmdsClub : cmdsClubs){
    		if(!clubs.contains(cmdsClub)){
    			throw new ClubNotInCompetitionException(cmdsClub);
    		}
    	}
    	
    	commands.add(cmd);
    }
    
    public void removeCommand(int i) {
    	commands.remove(i);
    }
    
    public void printActualCommands(){
    	System.out.println("Aktualne nespracovane prikazy:");
    	for(int i=0;i<commands.size();i++){
    		System.out.print("[" + i + "] - ");
    		System.out.println(commands.get(i));
    	}
    }
    public void newSeason(){
        notifyObservers(new NewSeasonNotification(this.startovne));
    }
    public void executeCommands(){
    	for(Command cmd : commands){
    		cmd.execute();
    	}
    	commands.clear();
        notifyObservers(new NewPointsNotification(this));
    }
    
    public Memento createSnapshot(){
        return new Memento(clubs);
    }
    
    public void restoreSnapshot(Memento m){
        m.restore(clubs);
    }

    @Override
    public void registerObserver(Observer obs) throws UnauthorizedOwnerException{
        if(obs instanceof Owner) {
            boolean ok = false;
            for(SportClub club : this.clubs){
               if(((Owner) obs).doYouOwn(club)) ok = true;
            }
            if(ok) this.owners.add(obs);
            else throw new UnauthorizedOwnerException(((Owner) obs));
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        this.owners.remove(obs);
    }

    /*
    posielame notifikacie klubom v sutazi
    a ak su nejaki majitelia zaregistrovany ako majitelia klubov v sutazi, tak posleme notifikaciu aj im
    */
    @Override
    public void notifyObservers(MyNotification notification) {

        for (SportClub club : clubs){
            club.update(notification);
        } 
        if(!owners.isEmpty()) for(Observer owner : owners) {
            owner.update(notification);
        }
    }
}
