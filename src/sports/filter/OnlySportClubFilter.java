package sports.filter;

import sports.base.SportClub;
import sports.base.SportItem;

public class OnlySportClubFilter implements FilterStrategy{

    @Override
    public boolean test(SportItem si) {
        return (si instanceof SportClub);
    }
    
}
