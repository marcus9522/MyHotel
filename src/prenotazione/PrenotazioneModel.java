package prenotazione;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public interface PrenotazioneModel {
	public void insertPrenotazione(PrenotazioneBean prenotazione) throws SQLException;

	public void deletePrenotazione(int idPrenotazione) throws SQLException;

	public PrenotazioneBean getPrenotazione(int idPrenotazione) throws SQLException;

	public Collection<PrenotazioneBean> getPrenotazioni() throws SQLException;

	public Collection<PrenotazioneBean> getPrenotazioniUtente(String email) throws SQLException;

	public boolean checkDisponibita(int numerocamera, Date datainizio, Date datafine) throws SQLException;

	public Collection<PrenotazioneBean> filtraprenotazioni(String periodo, String order, double totalemin,
			double totalemax) throws SQLException;

}
