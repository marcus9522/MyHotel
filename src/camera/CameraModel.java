package camera;

import java.sql.SQLException;

public interface CameraModel {
	public void insertCamera(CameraBean camera) throws SQLException;

	public void deleteCamera(int numerocamera) throws SQLException;

	public void modifyCamera(CameraBean camera) throws SQLException;
}
