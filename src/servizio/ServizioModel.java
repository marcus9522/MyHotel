package servizio;

import java.sql.SQLException;
import java.util.Collection;

public interface ServizioModel {
	public Collection<ServizioBean> getServizi() throws SQLException;

	public Collection<ServizioBean> getServiziCamera(int numeroCamera) throws SQLException;

	public void insertServizioCamera(String nomeservizio, int numerocamera) throws SQLException;
}
