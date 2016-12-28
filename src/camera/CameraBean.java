package camera;

public class CameraBean {

	private int numeroCamera;
	private String tipologia;
	private String immagine;
	private double prezzo;
	private String descrizione;
	
	
	public CameraBean(){
		
	}
	
	public CameraBean(int nc,String tipo,String image,double price,String desc){
		numeroCamera = nc;
		tipologia = tipo;
		immagine = image;
		prezzo = price;
		descrizione = desc;
	}

	public int getNumeroCamera() {
		return numeroCamera;
	}

	public void setNumeroCamera(int numeroCamera) {
		this.numeroCamera = numeroCamera;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
