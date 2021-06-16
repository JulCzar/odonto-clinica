package br.czar.odonto.report;

import br.czar.odonto.aplication.JDBCUtil;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.util.HashMap;

@WebServlet("/paciente/relatorio")
public class PatientReport extends ReportServlet {
	@Override
	public String getJasperFile() {
		return "patients.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParameters() {
		HashMap<String, Class<?>> param = new HashMap<>();
		param.put("NOME", String.class);
		return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}
}
