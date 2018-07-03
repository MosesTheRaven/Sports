package sports.visitor;

import sports.base.SportClub;
import sports.base.SportItem;
import sports.base.SportOrganization;

public class HaterVisitor implements SportItemVisitor{

	@Override
	public void visit(SportClub club) {
		System.out.println("Som bou kuknúť klub " + club.getName() + " a zas to stálo víte za čo? Šecko zle...");
	}

	@Override
	public void visit(SportOrganization organization) {
		System.out.println("Tá organizácia " + organization.getName() + " to je čistá tragédia. Čo za neschopákov to tam vedie?");
		for(SportItem it : organization.getSubOrganizations()){
			it.acceptVisitor(this);
		}
	}

}
