package br.czar.odonto.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

public class ReportGenerator {

	private final String fileName;
	private final Map<String, Object> params;
	private final Connection connection;

	public ReportGenerator(String fileName, Map<String, Object> params, Connection connection) {
		this.connection = connection;
		this.fileName = fileName;
		this.params = params;
	}

	public void outputStreamToPDF(OutputStream outputStream) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.fileName, this.params, this.connection);

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}

	}
}
