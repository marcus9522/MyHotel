package prenotazione;

import java.sql.SQLException;
import java.util.Collection;

public interface PrenotazioneModel {
	public void insertPrenotazione(PrenotazioneBean prenotazione,int numeroCamera) throws SQLException;

	public void deletePrenotazione(int idPrenotazione) throws SQLException;

	public PrenotazioneBean getPrenotazione(int idPrenotazione) throws SQLException;

	public Collection<PrenotazioneBean> getPrenotazioni() throws SQLException;
	
	public Collection<PrenotazioneBean> getPrenotazioniUtente(String email) throws SQLException;

}
