package sports.base.factory;

import sports.base.*;

public class RandomNumberSportClubFactory implements SportClubFactory{

    @Override
    public SportClub createSportClub(SportClub sc) {
        int number=7;
        return new SportClubWithRandomNumber(sc.getName(), sc.getRating(), number);
    }
}
