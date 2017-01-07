package prenotazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import connessione.DriverManagerConnectionPool;

public class GestorePrenotazione implements PrenotazioneModel {
	private static final String TABLE_NAME = "prenotazione";

	@Override
	public synchronized void insertPrenotazione(PrenotazioneBean prenotazione) throws SQLException {
		// Metodo per l'inserimento di una nuova prenotazione nel database e
		// della creazione dell'associazione con la relativa camera
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + GestorePrenotazione.TABLE_NAME
				+ " (EMAIL, NUMEROCAMERA, TOTALE, DATAINIZIO, DATAFINE) VALUES (?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prenotazione.getEmail());
			preparedStatement.setInt(2, prenotazione.getNumerocamera());
			preparedStatement.setDouble(3, prenotazione.getTotale());
			preparedStatement.setDate(4, prenotazione.getDatainizio());
			preparedStatement.setDate(5, prenotazione.getDatafine());
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
	public synchronized void deletePrenotazione(int idPrenotazione) throws SQLException {
		// Metodo che elimina una prenotazione all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + GestorePrenotazione.TABLE_NAME + " WHERE IDPRENOTAZIONE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idPrenotazione);
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
	public synchronized PrenotazioneBean getPrenotazione(int idPrenotazione) throws SQLException {
		// Metodo che restituisce i dati di una prenotazione tramite il suo ID
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PrenotazioneBean bean = new PrenotazioneBean();
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME + " WHERE IDPRENOTAZIONE = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idPrenotazione);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setIdprenotazione(idPrenotazione);
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNumerocamera(rs.getInt("NUMEROCAMERA"));
				bean.setTotale(rs.getDouble("TOTALE"));
				bean.setDatainizio(rs.getDate("DATAINIZIO"));
				bean.setDatafine(rs.getDate("DATAFINE"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized Collection<PrenotazioneBean> getPrenotazioni() throws SQLException {
		// Metodo che restituisce tutte le prenotazioni presenti all'interno del
		// database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				bean.setIdprenotazione(rs.getInt("IDPRENOTAZIONE"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNumerocamera(rs.getInt("NUMEROCAMERA"));
				bean.setTotale(rs.getDouble("TOTALE"));
				bean.setDatainizio(rs.getDate("DATAINIZIO"));
				bean.setDatafine(rs.getDate("DATAFINE"));
				prenotazioni.add(bean);
			}
  
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prenotazioni;
	}

	public synchronized Collection<PrenotazioneBean> getPrenotazioniUtente(String email) throws SQLException {
		// Metodo che restituisce le prenotazione di un determinato utente
		// identificato tramite la sua email
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME + " WHERE EMAIL = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PrenotazioneBean bean = new PrenotazioneBean();
				bean.setIdprenotazione(rs.getInt("IDPRENOTAZIONE"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNumerocamera(rs.getInt("NUMEROCAMERA"));
				bean.setTotale(rs.getDouble("TOTALE"));
				bean.setDatainizio(rs.getDate("DATAINIZIO"));
				bean.setDatafine(rs.getDate("DATAFINE"));
				prenotazioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prenotazioni;
	}

	@Override
	public boolean checkDisponibita(int numerocamera, Date datainizio, Date datafine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME + " WHERE (DATAINIZIO BETWEEN DATE ? AND DATE ? OR DATAFINE BETWEEN DATE ? AND DATE ?) OR (DATE ? BETWEEN DATAINIZIO AND DATAFINE OR DATE ? BETWEEN DATAINIZIO AND DATAFINE) AND NUMEROCAMERA = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, datainizio);
			preparedStatement.setDate(2, datafine);
			preparedStatement.setDate(3, datainizio);
			preparedStatement.setDate(4, datafine);
			preparedStatement.setDate(5, datainizio);
			preparedStatement.setDate(6, datafine);
			preparedStatement.setInt(7, numerocamera);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.first()) return false;
			else return true;

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
