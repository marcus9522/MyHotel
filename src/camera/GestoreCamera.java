package camera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connessione.DriverManagerConnectionPool;

public class GestoreCamera implements CameraModel {
	private static final String TABLE_NAME = "camera";

	@Override
	public synchronized void insertCamera(CameraBean camera) throws SQLException {
		// Metodo per l'inserimento di una nuova camera all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + GestoreCamera.TABLE_NAME
				+ " (NUMEROCAMERA, PREZZO, TIPOLOGIA, IMMAGINE, DESCRIZIONE) VALUES (?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, camera.getNumeroCamera());
			preparedStatement.setDouble(2, camera.getPrezzo());
			preparedStatement.setString(3, camera.getTipologia());
			preparedStatement.setString(4, camera.getImmagine());
			preparedStatement.setString(5, camera.getDescrizione());
			preparedStatement.executeUpdate();

			// connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized void deleteCamera(int numerocamera) throws SQLException {
		// Metodo per la cancellazione di una camera dal database tramite la sua
		// chiave primaria
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM " + GestoreCamera.TABLE_NAME + " WHERE NUMEROCAMERA = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, numerocamera);
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

	@Override
	public synchronized void modifyCamera(CameraBean camera) throws SQLException {
		// Metodo per la modifica dei dati di una camera gia' esistente
		// all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE " + GestoreCamera.TABLE_NAME
				+ " SET NUMEROCAMERA = ?, PREZZO = ?, TIPOLOGIA = ?, IMMAGINE = ?, DESCRIZIONE = ?"
				+ " WHERE NUMEROCAMERA = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, camera.getNumeroCamera());
			preparedStatement.setDouble(2, camera.getPrezzo());
			preparedStatement.setString(3, camera.getTipologia());
			preparedStatement.setString(4, camera.getImmagine());
			preparedStatement.setString(5, camera.getDescrizione());
			preparedStatement.setInt(6, camera.getNumeroCamera());
			preparedStatement.executeUpdate();

			// connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public synchronized String checknumero(int numerocamera) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + GestoreCamera.TABLE_NAME + " WHERE NUMEROCAMERA=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numerocamera);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return "found";
			else
				return "notfound";
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
