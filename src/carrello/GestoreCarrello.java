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
	public synchronized void insertCamera(String email, int numerocamera, Date datainizio, Date datafine) throws SQLException {
		//Metodo che inserice una nuova camera all'interno del carrello con le date di inizio e fine prenotazione
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO" + GestoreCarrello.TABLE_NAME + " (EMAIL, NUMEROCAMERA, DATAINIZIO, DATAFINE) VALUES (?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, numerocamera);
            preparedStatement.setDate(3, datainizio);
            preparedStatement.setDate(4, datafine);
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
	public synchronized void deleteCamera(String email, int numerocamera) throws SQLException {
		//Metodo che cancella una singola camera dal carrello
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
	public synchronized ArrayList<Integer> getIdCamereCarrello(String email) throws SQLException {
		//Metodo che restituisce tutti gli id delle camere presenti all'interno del carrello
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Integer> idcamere = new ArrayList<Integer>();
		String selectSQL = "SELECT * FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
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
	public synchronized Collection<CarrelloBean> getCarrelloUtente(String email) throws SQLException {
		// Metodo che restituisce datainizio e data fine delle camere presenti all'interno del carrello
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<CarrelloBean> carrello = new LinkedList<CarrelloBean>();
		String selectSQL = "SELECT * FROM " + GestoreCarrello.TABLE_NAME + " WHERE EMAIL = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CarrelloBean bean = new CarrelloBean();
				bean.setEmail(rs.getString("EMAIL"));
				bean.setNumerocamera(rs.getInt("NUMEROCAMERA"));
				bean.setDatainizio(rs.getDate("DATAINIZIO"));
				bean.setDatafine(rs.getDate("DATAFINE"));
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
	public synchronized void emptyCarrello(String email) throws SQLException {
		//Metodo che elimina ogni camera presente all'interno del carrello dell'utente
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

