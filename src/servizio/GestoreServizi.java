package servizio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import connessione.DriverManagerConnectionPool;

public class GestoreServizi implements ServizioModel {
	private static final String TABLE_NAME = "servizio";
	private static final String TABLE_NAME2 = "ha";

	@Override
	public synchronized Collection<ServizioBean> getServizi() throws SQLException {
		// Metodo che restituisce tutti i servizi disponibili
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ServizioBean> servizi = new LinkedList<ServizioBean>();
		String selectSQL = "SELECT * FROM " + GestoreServizi.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ServizioBean bean = new ServizioBean();
				bean.setNome(rs.getString("NOMESERVIZIO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				servizi.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return servizi;
	}

	@Override
	public synchronized Collection<ServizioBean> getServiziCamera(int numeroCamera) throws SQLException {
		// Metodo che restituisce tutti i servizi disponibili per una certa
		// camera identificata dal suo numero
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ServizioBean> servizi = new LinkedList<ServizioBean>();
		String selectSQL = "SELECT " + GestoreServizi.TABLE_NAME + ".* FROM " + GestoreServizi.TABLE_NAME + "," + GestoreServizi.TABLE_NAME2 + " WHERE NUMEROCAMERA = ? AND " + GestoreServizi.TABLE_NAME + ".NOMESERVIZIO = " + GestoreServizi.TABLE_NAME2 + ".NOMESERVIZIO";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numeroCamera);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ServizioBean bean = new ServizioBean();
				bean.setNome(rs.getString("NOMESERVIZIO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				servizi.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return servizi;
	}

	@Override
	public synchronized void insertServizioCamera(String nomeservizio, int numerocamera) throws SQLException {
		// Metodo che inserisce un servizio ad una camera, il servizio sarà
		// scelto fra quelli disponibili
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + GestoreServizi.TABLE_NAME2 + " (NUMEROCAMERA, NOMESERVIZIO) VALUES (?, ?) ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, numerocamera);
			preparedStatement.setString(2, nomeservizio);
			preparedStatement.executeUpdate();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
