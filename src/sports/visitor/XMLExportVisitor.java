package sports.visitor;

import java.io.IOException;
import java.io.Writer;

import sports.base.SportClub;
import sports.base.SportItem;
import sports.base.SportOrganization;

public class XMLExportVisitor implements SportItemVisitor {
	
	private Writer exportWriter;
	
	public XMLExportVisitor(Writer exportWriter) {
		this.exportWriter = exportWriter;
	}
	
	@Override
	public void visit(SportClub club) {
		StringBuilder xmlClub = new StringBuilder();
		xmlClub.append("<SportClub>\n");
		xmlClub.append("<name>");
		xmlClub.append(club.getName());
		xmlClub.append("</name>\n");
		xmlClub.append("<rating>");
		xmlClub.append(club.getRating());
		xmlClub.append("</rating>\n");
		xmlClub.append("<points>");
		xmlClub.append(club.getPoints());
		xmlClub.append("</points>\n");
		xmlClub.append("<pokrik>");
		xmlClub.append(club.getPokrik());
		xmlClub.append("</pokrik>\n");
		xmlClub.append("</SportClub>\n");
		try {
			exportWriter.write(xmlClub.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(SportOrganization organization) {
		StringBuilder xmlOrganization = new StringBuilder();
		xmlOrganization.append("<SportOrganization>\n");
		xmlOrganization.append("<name>");
		xmlOrganization.append(organization.getName());
		xmlOrganization.append("</name>\n");
		xmlOrganization.append("<rating>");
		xmlOrganization.append(organization.getRating());
		xmlOrganization.append("</rating>\n");
		xmlOrganization.append("<suborganizations>\n");
		
		try {
			exportWriter.write(xmlOrganization.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(SportItem subOrg : organization.getSubOrganizations()){
			subOrg.acceptVisitor(this);
		}
		
		try {
			exportWriter.write("</suborganizations>\n");
			exportWriter.write("</SportOrganization>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
