package sports.bridgeExport;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import sports.base.SportItem;
import sports.visitor.JSONExportVisitor;
import sports.visitor.XMLExportVisitor;

public class ExporterXML implements Exporter{
	
	@Override
	public void export(SportItem item) {
		Writer writer = null;
		try {
		   writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(item.getName() + ".xml"), "utf-8"));
		   
		   XMLExportVisitor jsonVisitor = new XMLExportVisitor(writer);
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
