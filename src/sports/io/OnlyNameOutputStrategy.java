package sports.io;

import java.io.Serializable;

public class OnlyNameOutputStrategy implements OutputStrategy, Serializable {

    @Override
    public String formatOutput(String name, double rating) {
        return name.toUpperCase();
    }
    
}
