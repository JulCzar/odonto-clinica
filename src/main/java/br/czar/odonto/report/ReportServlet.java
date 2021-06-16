package br.czar.odonto.report;

import br.czar.odonto.aplication.JDBCUtil;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReportServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = -457944609743398368L;

	public abstract String getJasperFile();
	public abstract HashMap<String, Class<?>> getParameters();

	public abstract Connection getConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String name = request.getServletContext().getRealPath("/reports/" + getJasperFile());

			Connection connection = getConnection();

			Map<String, Object> params = new HashMap<>();
			HashMap<String, Class<?>> paramList = getParameters();
			if (paramList != null)
				for (String key : paramList.keySet()) {

					if (request.getParameter(key) == null) {
						params.put(key, null);
						continue;
					}

					String param = request.getParameter(key);
					if (param != null) {
						if ( param.trim().equals("") || param.trim().equals("null")) {
							params.put(key, null);
							continue;
						}
					}

					if (paramList.get(key).getName().equals("java.lang.Integer"))
						params.put(key, Integer.valueOf(request.getParameter(key)));
					else if (paramList.get(key).toString().contains("java.lang.String")) {
						params.put(key, new String(request.getParameter(key)));
					} else if (paramList.get(key).toString().contains("java.lang.Boolean"))
						params.put(key, Boolean.valueOf(request.getParameter(key)));
					else if (paramList.get(key).toString().contains("java.lang.Float"))
						params.put(key, Float.valueOf(request.getParameter(key)));
					else if (paramList.get(key).toString().contains("java.lang.Double"))
						params.put(key, Double.valueOf(request.getParameter(key)));
				}

			ReportGenerator repGen = new ReportGenerator(name, params, connection);
			repGen.outputStreamToPDF(response.getOutputStream());

			JDBCUtil.closeConnection(connection);
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			System.out.println("Erro no Report Servlet");
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
