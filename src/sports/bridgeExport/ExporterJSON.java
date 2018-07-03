package sports.bridgeExport;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import sports.base.SportClub;
import sports.base.SportItem;
import sports.base.SportOrganization;
import sports.visitor.JSONExportVisitor;

public class ExporterJSON implements Exporter{

	@Override
	public void export(SportItem item) {
		Writer writer = null;
		try {
		   writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(item.getName() + ".json"), "utf-8"));
		   
		   JSONExportVisitor jsonVisitor = new JSONExportVisitor(writer);
		   item.acceptVisitor(jsonVisitor);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
