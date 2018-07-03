package sports.base;

import Observer.Observer;
import Subject.MyNotification;
import Subject.NewPointsNotification;
import Subject.NewSeasonNotification;
import java.io.Serializable;
import java.util.Iterator;
import sports.pokrik.Pokrik;
import sports.visitor.SportItemVisitor;

public class SportClub extends SportItem implements Pokrik, Serializable, Observer {
    private int points = 0;
    private String pokrik;
    
    @Override
    public void update(MyNotification notification) {
        if(notification instanceof NewSeasonNotification) this.points =0;
        if(notification instanceof NewPointsNotification) System.out.println("Klub " + this.getName() + " ma aktualne " + this.getPoints() + " bodov");
        //tu by sme pridali dalsie riesenie notifikacii
    }
    
    class SportClubIterator implements Iterator<SportItem>{

        private boolean uzVypisane=false;
        
        @Override
        public boolean hasNext() {
            return !uzVypisane;
        }

        @Override
        public SportItem next() {
            uzVypisane=true;
            return SportClub.this;
        }
        
    }
    
    public String toStringWithPoints() {
    	return name + " - " + points + " bodov";
    }
    
    @Override
    public Iterator<SportItem> iterator() {
        return new SportClubIterator();
    }

    public SportClub(){        
    }
    
    public SportClub(String name, double points){
        super(name, points);
        pokrik="";
    }
   
    public SportClub(String name, double points, String pokrik){
        super(name, points);
        this.pokrik=pokrik;
    }

    @Override
    public void pokrik(){
       System.out.println(pokrik);
    }
    
    public void acceptVisitor(SportItemVisitor visitor) {
    	visitor.visit(this);
    }

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public String getPokrik() {
		return this.pokrik;
	}
	
	public void setPokrik(String pokrik) {
		this.pokrik = pokrik;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void removePoints(int points) {
		this.points -= points;
	}

}
