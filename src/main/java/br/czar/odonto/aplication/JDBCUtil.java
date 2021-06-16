package br.czar.odonto.aplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	private JDBCUtil() {}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/odontodb", "topicos2", "123456");

			conn.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao registrar o driver do postgres.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar no banco de dados.");
			e.printStackTrace();
		}
		return conn;
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void closeStatement(PreparedStatement stat) {
		if (stat != null) {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
