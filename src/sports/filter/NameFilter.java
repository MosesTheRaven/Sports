package sports.filter;

import sports.base.SportItem;

public class NameFilter implements FilterStrategy {
    String substring;
    
    public NameFilter(String substring){
        this.substring=substring;
    }  
    
    @Override
    public boolean test(SportItem si) {
        return si.getName().contains(substring);
    }
    
}
