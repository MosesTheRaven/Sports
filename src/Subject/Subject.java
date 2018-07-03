
package Subject;

import Observer.Observer;
import java.util.List;
import sports.competition.UnauthorizedOwnerException;

public abstract class Subject {
    
    
    public abstract void registerObserver(Observer obs) throws UnauthorizedOwnerException;
    public abstract void removeObserver(Observer obs);
    public abstract void notifyObservers(MyNotification notification);
}
