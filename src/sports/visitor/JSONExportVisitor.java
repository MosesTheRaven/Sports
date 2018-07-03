package sports.visitor;

import java.io.IOException;
import java.io.Writer;

import sports.base.SportClub;
import sports.base.SportItem;
import sports.base.SportOrganization;

public class JSONExportVisitor implements SportItemVisitor{
	
	private Writer exportWriter;
	
	public JSONExportVisitor(Writer exportWriter) {
		this.exportWriter = exportWriter;
	}

	@Override
	public void visit(SportClub club) {
		StringBuilder jsonClub = new StringBuilder();
		jsonClub.append("{\n");
		jsonClub.append("\"type\" : \"SportClub\",\n");
		jsonClub.append("\"name\" : \"");
		jsonClub.append(club.getName());
		jsonClub.append("\",\n");
		jsonClub.append("\"rating\" : \"");
		jsonClub.append(club.getRating());
		jsonClub.append("\",\n");
		jsonClub.append("\"points\" : \"");
		jsonClub.append(club.getPoints());
		jsonClub.append("\",\n");
		jsonClub.append("\"pokrik\" : \"");
		jsonClub.append(club.getPokrik());
		jsonClub.append("\"\n");
		jsonClub.append("},\n");
		try {
			exportWriter.write(jsonClub.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(SportOrganization organization) {
		StringBuilder jsonOrganization = new StringBuilder();
		jsonOrganization.append("{\n");
		jsonOrganization.append("\"type\" : \"SportOrganization\",\n");
		jsonOrganization.append("\"name\" : \"");
		jsonOrganization.append(organization.getName());
		jsonOrganization.append("\",\n");
		jsonOrganization.append("\"rating\" : \"");
		jsonOrganization.append(organization.getRating());
		jsonOrganization.append("\",\n");
		jsonOrganization.append("\"suborganizations\" : [\n");
		
		try {
			exportWriter.write(jsonOrganization.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(SportItem subOrg : organization.getSubOrganizations()){
			subOrg.acceptVisitor(this);
		}
		
		try {
			exportWriter.write("]\n},\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
