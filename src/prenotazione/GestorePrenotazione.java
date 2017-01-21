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
	/**
	 * Metodo per l'inserimento di una nuova prenotazione nel database e della creazione dell'associazione con la relativa camera
	 * @param prenotazione - La prenotazione da inserire nel database
	 */
	public synchronized void insertPrenotazione(PrenotazioneBean prenotazione) throws SQLException {
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
	/**
	 * Metodo che elimina una prenotazione all'interno del database
	 * @param idPrenotazione - La chiave della prenotazione da eliminare
	 */
	public synchronized void deletePrenotazione(int idPrenotazione) throws SQLException {
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
	/**
	 * Metodo che restituisce i dati di una prenotazione tramite il suo ID
	 * @param idPrenotazione - La chiave della prenotazione
	 * @return Dati della prenotazione
	 */
	public synchronized PrenotazioneBean getPrenotazione(int idPrenotazione) throws SQLException {
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
	/**
	 * Metodo che restituisce tutte le prenotazioni presenti all'interno del database
	 * @return Tutte le prenotazioni effettuate dai clienti presenti nel database, con relativi dati
	 */
	public synchronized Collection<PrenotazioneBean> getPrenotazioni() throws SQLException {
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

	/**
	 * Metodo che restituisce le prenotazione di un determinato utente identificato tramite la sua email
	 * @param email - L'email dell'utente
	 * @return Le prenotazioni effettuate dall'utente
	 */
	public synchronized Collection<PrenotazioneBean> getPrenotazioniUtente(String email) throws SQLException {
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
	/**
	 *  Metodo per controllora se una camera è disponibile in un certo periodo
	 *  @param numerocamera - Il numero della camera da controllare
	 *  @param datainizio - La data di inizio del periodo da controllare
	 *  @param datafine - La data di fine del periodo da controllare
	 *  @return True se la camera è disponibile
	 *	@return False se la camera non è disponibile
	 */
	public boolean checkDisponibita(int numerocamera, Date datainizio, Date datafine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME
				+ " WHERE NUMEROCAMERA = ? AND ((DATAINIZIO BETWEEN DATE ? AND DATE ? OR DATAFINE BETWEEN DATE ? AND DATE ?) OR (DATE ? BETWEEN DATAINIZIO AND DATAFINE OR DATE ? BETWEEN DATAINIZIO AND DATAFINE))";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numerocamera);
			preparedStatement.setDate(2, datainizio);
			preparedStatement.setDate(3, datafine);
			preparedStatement.setDate(4, datainizio);
			preparedStatement.setDate(5, datafine);
			preparedStatement.setDate(6, datainizio);
			preparedStatement.setDate(7, datafine);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return false;
			else
				return true;

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
	/**
	 * Metodo per il filtraggio delle prenotazioni
	 * @param periodo - Il periodo di cui si vogliono recuperare le prenotazioni
	 * @param order -  Il tipo di ordinamento scelto
	 * @param totalemin - Il prezzo minimo 
	 * @param totalemax - Il prezzo massimo
	 * @return Le prenotazioni in base ai dati inseriti nel filtraggio
	 */
	public Collection<PrenotazioneBean> filtraprenotazioni(String periodo, String order, double totalemin,
			double totalemax) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean first = true;
		Date today = new java.sql.Date(System.currentTimeMillis());
		Collection<PrenotazioneBean> prenotazioni = new LinkedList<PrenotazioneBean>();
		String selectSQL = "SELECT * FROM " + GestorePrenotazione.TABLE_NAME;
		if (totalemin != 0) {
			if (first == true) {
				selectSQL += " WHERE ";
				first = false;
			} else
				selectSQL += " AND ";
			selectSQL += " TOTALE>= " + totalemin;
		}
		if (totalemax != 0) {
			if (first == true) {
				selectSQL += " WHERE ";
				first = false;
			} else
				selectSQL += " AND ";
			selectSQL += " TOTALE<= " + totalemax;
		}
		if (periodo.equals("TUTTI") == false) {
			if (periodo.equals("TERMINATE")) {
				if (first == true) {
					selectSQL += " WHERE ";
					first = false;
				} else
					selectSQL += " AND ";
				selectSQL += " DATAFINE< '" + today +"'";
			}
			if (periodo.equals("INCORSO")) {
				if (first == true) {
					selectSQL += " WHERE ";
					first = false;
				} else
					selectSQL += " AND ";
				selectSQL += " DATAINIZIO<= '" + today + "'";
				selectSQL += " AND ";
				selectSQL += " DATAFINE>= '" + today + "'";
			}
			if (periodo.equals("FUTURE")) {
				if (first == true) {
					selectSQL += " WHERE ";
					first = false;
				} else
					selectSQL += " AND ";
				selectSQL += " DATAINIZIO> '" + today + "'";
			}
		}
		selectSQL += " ORDER BY " + order;
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
}
