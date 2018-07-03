package sports.io;

import java.io.Serializable;

public class DefaultOutputStrategy implements OutputStrategy, Serializable {

    @Override
    public String formatOutput(String name, double rating) {
        return name + " (" + rating + ")";
    }


    
}
