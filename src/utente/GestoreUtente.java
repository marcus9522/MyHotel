package utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import connessione.DriverManagerConnectionPool;

public class GestoreUtente implements UtenteModel {
	private static final String TABLE_NAME = "utente";

	@Override
	public void insertUtente(UtenteBean utente) throws SQLException {
		// Metodo per l'inserimento di un nuovo utente all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + GestoreUtente.TABLE_NAME
				+ " (EMAIL, PASSWORD, RUOLO, NOME, COGNOME, DATA_NASCITA,) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getRuolo());
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());
			preparedStatement.setDate(6, utente.getDataNascita());
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
	public void deleteUtente(String email) throws SQLException {
		// Metodo per la cancellazione di un utente presente all'interno del
		// database tramite la sua chiave primaria
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + GestoreUtente.TABLE_NAME + " WHERE EMAIL = ?";

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

	@Override
	public void modifyUtente(UtenteBean utente) throws SQLException {
		// Metodo per la modifica di un utente presente all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = " UPDATE " + GestoreUtente.TABLE_NAME
				+ " SET EMAIL = ?, PASSWORD = ?, RUOLO = ?, NOME = ?, COGNOME = ?, DATA_NASCITA = ?"
				+ " WHERE EMAIL = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getRuolo());
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());
			preparedStatement.setDate(6, utente.getDataNascita());
			preparedStatement.setString(7, utente.getEmail());
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
	public UtenteBean getUtente(String email) throws SQLException {
		// Metodo per visualizzare i dati di un utente individuato tramite la
		// sua chiave primaria
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		String selectSQL = "SELECT * FROM " + GestoreUtente.TABLE_NAME + " WHERE EMAIL = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setRuolo(rs.getString("RUOLO"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setDataNascita(rs.getDate("DATA_NASCITA"));
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
	public boolean checkEmail(String email) throws SQLException {
		// Metodo per verificare se un email è gia presente all'interno della
		// tabella utenti
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<String> Email = new ArrayList<String>();
		String selectSQL = "SELECT " + GestoreUtente.TABLE_NAME + ".EMAIL FROM " + GestoreUtente.TABLE_NAME;
		boolean found = false;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Email.add(rs.getString("EMAIL"));
			}
			if (Email != null && Email.size() != 0) {
				Iterator<String> it = Email.iterator();
				while (it.hasNext()) {
					if (it.next().equals(email)) {
						found = true;
						break;
					}
				}
			}
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return found;
	}

	@Override
	public Collection<UtenteBean> getUtenti() throws SQLException {
		// Metodo che restituisce le informazioni di tutti gli utenti presenti
		// all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
		String selectSQL = "SELECT * FROM " + GestoreUtente.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setRuolo(rs.getString("RUOLO"));
				bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setDataNascita(rs.getDate("DATA_NASCITA"));
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utenti;
	}

	@Override
	public String login(String email, String password) throws SQLException {
		// Metodo che controlla se email e password inserite hanno un riscontro
		// nel database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<UtenteBean> user = new LinkedList<UtenteBean>();
		String selectSQL = "SELECT * FROM " + GestoreUtente.TABLE_NAME;
		String found = "false";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setRuolo(rs.getString("RUOLO"));
				user.add(bean);
			}
			if (user != null && user.size() != 0) {
				Iterator<UtenteBean> it = user.iterator();
				while (it.hasNext()) {
					UtenteBean bean = (UtenteBean) it.next();
					if ((bean.getEmail().equals(email)) && (bean.getPassword().equals(password))) {
						found = "true";
						if (bean.getRuolo().equals("amministratore"))
							found = "admin";
						break;
					}
				}
			}
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return found;
	}
}
