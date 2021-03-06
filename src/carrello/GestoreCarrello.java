package carrello;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import connessione.DriverManagerConnectionPool;

public class GestoreCarrello implements CarrelloModel {
	private static final String TABLE_NAME = "hacarrello";
	@Override

	/**
	 * 		Metodo che inserice una nuova camera all'interno del carrello con le date di inizio e fine prenotazione
	 * @param email - la mail dell'utente che ha prenotato la camera
	 * @param numercamera - il numero della camera scelta
	 * @param datainizio - Data di inizio della prenotazione
	 * @param datafine - Data di fine della prenotazione
	 * @param totale - Totale della prenotazione
	 */
	
	public synchronized void insertCamera(String email, int numerocamera, Date datainizio, Date datafine, double totale) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String dateSQL = "SELECT DATEDIFF(?,?) AS DIFF";
        String insertSQL = "INSERT INTO " + GestoreCarrello.TABLE_NAME + " (EMAIL, NUMEROCAMERA, DATAINIZIO, DATAFINE, TOTALE) VALUES (?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement2 = connection.prepareStatement(dateSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, numerocamera);
            preparedStatement.setDate(3, datainizio);
            preparedStatement.setDate(4, datafine);
            preparedStatement2.setDate(1, datafine);
            preparedStatement2.setDate(2, datainizio);
            ResultSet rs = preparedStatement2.executeQuery();
            while (rs.next()) {
                preparedStatement.setDouble(5, totale * (rs.getDouble("DIFF")+1));
			}
			preparedStatement.executeUpdate();

			//connection.commit();
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
	 * Metodo che cancella una singola camera dal carrello
	 * @param email - l'email dell'utente che deve cancellare la camera dal carrello
	 * @param numerocamera - il numero della camera da cancellare
	 */
	public synchronized void deleteCamera(String email, int numerocamera) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ? AND NUMEROCAMERA = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, numerocamera);
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
	 * Metodo che restituisce tutti gli id delle camere presenti all'interno del carrello
	 * @param email - L'email dell'utente 
	 * @return id camera
	 */
	public synchronized ArrayList<Integer> getIdCamereCarrello(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Integer> idcamere = new ArrayList<Integer>();
		String selectSQL = "SELECT * FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				idcamere.add(rs.getInt("NUMEROCAMERA"));
			}
  
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return idcamere;
	}

	@Override
	/**
	 * Metodo che restituisce datainizio e datafine delle camere presenti all'interno del carrello
	 * @param email - L'email dell'utente
	 * @return dataInizio e dataFine
	 */
	public synchronized Collection<CarrelloBean> getCarrelloUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<CarrelloBean> carrello = new LinkedList<CarrelloBean>();
		String selectSQL = "SELECT * FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CarrelloBean bean = new CarrelloBean();
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNumerocamera(rs.getInt("NUMEROCAMERA"));
				bean.setDatainizio(rs.getDate("DATAINIZIO"));
				bean.setDatafine(rs.getDate("DATAFINE"));
				bean.setTotale(rs.getDouble("TOTALE"));
				carrello.add(bean);
			}
  
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return carrello;
	}

	@Override
	/**
	 * Metodo che elimina ogni camera presente all'interno del carrello dell'utente
	 * @param email - L'email dell'utente
	 */
	public synchronized void emptyCarrello(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);
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

