package camera;

import java.sql.SQLException;
import java.util.Collection;

public interface VisualizzatoreCameraModel {
	public CameraBean getCamera(int numerocamera) throws SQLException;

	public Collection<CameraBean> getCamere() throws SQLException;

	public Collection<CameraBean> filtraCamere(double min, double max, String tipologia, String order)
			throws SQLException;
}
