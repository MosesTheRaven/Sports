package sports.visitor;

import sports.base.SportClub;
import sports.base.SportOrganization;

public class SportExpertVisitor implements SportItemVisitor{
	private double notSoBad;
	private double good;
	private double excelent;
	
	public SportExpertVisitor(double notSoBad0, double good0, double excelent0){
		this.notSoBad = notSoBad0;
		this.good = good0;
		this.excelent = excelent0;
	}
	
	@Override
	public void visit(SportClub club) {
		double rating = club.getRating();
		if(rating < this.notSoBad){
			System.out.println("Klub " + club.getName() + " je na tom ozaj, ale ozaj zle.");
		}
		else if(rating < this.good){
			System.out.println("Klub " + club.getName() + " nie je na tom až tak zle, ale ktoviečo to nie je.");
		}
		else if(rating < this.excelent){
			System.out.println("Klub " + club.getName() + " je dosť dobrý klub.");
		}
		else{
			System.out.println("Klub " + club.getName() + " je naozaj excelentný klub.");
		}
	}

	@Override
	public void visit(SportOrganization organization) {
		double rating = organization.getRating();
		if(rating < this.notSoBad){
			System.out.println("Organizácia " + organization.getName() + " je na tom ozaj, ale ozaj zle.");
		}
		else if(rating < this.good){
			System.out.println("Organizácia " + organization.getName() + " nie je na tom až tak zle, ale ktoviečo to nie je.");
		}
		else if(rating < this.excelent){
			System.out.println("Organizácia " + organization.getName() + " je dosť dobrá organizácia.");
		}
		else{
			System.out.println("Organizácia " + organization.getName() + " je naozaj excelentná športová organizácia.");
		}
	}

}
