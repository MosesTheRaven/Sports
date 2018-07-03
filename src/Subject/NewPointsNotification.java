
package Subject;

import sports.competition.Competition;

public class NewPointsNotification implements MyNotification{

    Competition competition;

    public NewPointsNotification(Competition competition) {
        this.competition = competition;
    }
    
    @Override
    public String popis() {
        return "Notifikacia, ktora upozornuje na novy stav bodov v sutazi.";
    }
    
}
