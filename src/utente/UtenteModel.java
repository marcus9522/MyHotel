package utente;

import java.sql.SQLException;
import java.util.Collection;

public interface UtenteModel {
	public void insertUtente(UtenteBean utente) throws SQLException;

	public void deleteUtente(String email) throws SQLException;

	public void modifyUtente(UtenteBean utente) throws SQLException;

	public UtenteBean getUtente(String email) throws SQLException;

	public boolean checkEmail(String email) throws SQLException;

	public Collection<UtenteBean> getUtenti() throws SQLException;

	public String login(String email, String password) throws SQLException;
}
