package carrello;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public interface CarrelloModel {
	public void insertCamera(String email, int numerocamera, Date datainizio, Date datafine, double prezzo) throws SQLException;

	public void deleteCamera(String email, int numerocamera) throws SQLException;

	public ArrayList<Integer> getIdCamereCarrello(String email) throws SQLException;

	public Collection<CarrelloBean> getCarrelloUtente(String email) throws SQLException;
	
	public void emptyCarrello(String email) throws SQLException;
	

}
