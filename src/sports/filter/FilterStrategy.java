package sports.filter;

import sports.base.SportItem;

public interface FilterStrategy {
    boolean test(SportItem si);
}
