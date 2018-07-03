package sports.visitor;

import sports.base.SportClub;
import sports.base.SportOrganization;

public interface SportItemVisitor {
	public void visit(SportClub club);
	public void visit(SportOrganization organization);
}
