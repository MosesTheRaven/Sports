package sports.competition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sports.base.SportClub;

public class Memento {
    List<Integer> points=new ArrayList<>();
    LocalDateTime time;
    
    Memento(List<SportClub> clubs){
        for (SportClub sc:clubs){
            points.add(sc.getPoints());
        }
        time=LocalDateTime.now();
    }

    void restore(List<SportClub> clubs) {
        Iterator<Integer> it_points=points.iterator();
        Iterator<SportClub> it_clubs=clubs.iterator();
        
        while (it_clubs.hasNext() && it_points.hasNext()){
            it_clubs.next().setPoints(it_points.next());
        }
        assert(!it_clubs.hasNext());
        assert(!it_points.hasNext());       
    }
}
