package sports.io;

import java.io.Serializable;

public class PointsFirstOutputStrategy implements OutputStrategy, Serializable {

    @Override
    public String formatOutput(String name, double rating) {
        return "(" + rating + ") " + name;
    }
    
}
