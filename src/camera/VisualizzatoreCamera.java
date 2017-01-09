package camera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import connessione.DriverManagerConnectionPool;

public class VisualizzatoreCamera implements VisualizzatoreCameraModel {
	private static final String TABLE_NAME = "camera";

	@Override
	public synchronized CameraBean getCamera(int numerocamera) throws SQLException {
		// Metodo che restituisce le informazioni di una camera identificata
		// tramite il suo ID
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CameraBean bean = new CameraBean();
		String selectSQL = "SELECT * FROM " + VisualizzatoreCamera.TABLE_NAME + " WHERE NUMEROCAMERA = ? ";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numerocamera);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setNumeroCamera(rs.getInt("NUMEROCAMERA"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
				bean.setImmagine(rs.getString("IMMAGINE"));
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
	public synchronized Collection<CameraBean> getCamere() throws SQLException {
		// Metodo che restituisce le informazioni su tutte le camere presente
		// all'interno del database
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<CameraBean> camere = new LinkedList<CameraBean>();
		String selectSQL = "SELECT * FROM " + VisualizzatoreCamera.TABLE_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CameraBean bean = new CameraBean();
				bean.setNumeroCamera(rs.getInt("NUMEROCAMERA"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
				bean.setImmagine(rs.getString("IMMAGINE"));
				camere.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return camere;
	}

	@Override
	public synchronized Collection<CameraBean> filtraCamere(double min, double max, String tipologia, String order)
			throws SQLException {
		// Metodo che restituisce le camere che soddisfano i parametri inseriti
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean first = true;
		Collection<CameraBean> camere = new LinkedList<CameraBean>();

		String selectSQL = "SELECT * FROM " + VisualizzatoreCamera.TABLE_NAME;
		if (min != 0) {
			if (first == true) {
				selectSQL += " WHERE ";
				first = false;
			} else
				selectSQL += " AND ";
			selectSQL += " PREZZO>= " + min;
		}
		if (max != 0) {
			if (first == true) {
				selectSQL += " WHERE ";
				first = false;
			} else
				selectSQL += " AND ";
			selectSQL += " PREZZO<= " + max;
		}
		if (tipologia.equals("Tutte") == false) {
			if (first == true) {
				selectSQL += " WHERE ";
				first = false;
			} else
				selectSQL += " AND ";
			selectSQL += "TIPOLOGIA='"+ tipologia +"'";
		}
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CameraBean bean = new CameraBean();
				bean.setNumeroCamera(rs.getInt("NUMEROCAMERA"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
				bean.setImmagine(rs.getString("IMMAGINE"));
				camere.add(bean);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return camere;
	}
}
