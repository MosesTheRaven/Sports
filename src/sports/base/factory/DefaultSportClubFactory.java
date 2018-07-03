package sports.base.factory;

import sports.base.SportClub;

public class DefaultSportClubFactory implements SportClubFactory{

    @Override
    public SportClub createSportClub(SportClub sc) {
        return sc;
    }
    
}
