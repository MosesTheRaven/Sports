package sports.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import sports.base.SportClub;
import sports.base.SportItem;
import sports.base.SportOrganization;

public class TrueSportEnthusiastVisitor implements SportItemVisitor {
	private static Random rand = new Random(System.currentTimeMillis());
	
	private static List<String> clubsVirtues = new ArrayList<String>(
			Arrays.asList(
					"Videl som naozaj výborných hráčov. Do zápasu šli celým srdcom. Tak to má byť!", 
					"Fanúškovia tohto klubu boli jednoducho úžasný. Takú atmosféru nevidno hocikde.",
					"Obzvlášť pekné a milé roztlieskavačky ma potešili :)",
					"Teta v bufete, ktorá predávala hotdogy a langoše je asi najmilšia osoba, čo som stretol!"
					));
	
	private static List<String> organizatonsVirtues = new ArrayList<String>(
			Arrays.asList(
					"Vedenie tejto organizácie je veľmi profesionálne.", 
					"V tejto organizácii pôsobia naozaj kvalitné kluby.",
					"Výchova mládeže v organizácii je na vysokej úrovni.",
					"Sekretárka podpredsedu mi spravila pri návšteve ozaj výbornú kávičku."
					));

	@Override
	public void visit(SportClub club) {
		String msg = clubsVirtues.get(rand.nextInt(clubsVirtues.size()));
		
		System.out.println("Bol som na návšteve v klube " + club.getName() + ". " + msg);
	}

	@Override
	public void visit(SportOrganization organization) {
		String msg = organizatonsVirtues.get(rand.nextInt(organizatonsVirtues.size()));
		
		System.out.println("Bol som na návšteve v organizácii " + organization.getName() + ". " + msg);
		
		for(SportItem it : organization.getSubOrganizations()){
			it.acceptVisitor(this);
		}
	}

}
