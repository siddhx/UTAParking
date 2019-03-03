package UTAParking.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;

import UTAParking.model.*;
import UTAParking.util.SQLConnection;
import java.sql.Statement;

public class ParkingDAO {

	public List<Parking> listParking(String permitType) throws SQLException
	{
		String abd;
		List<Parking> parkingList = new ArrayList<Parking>();
		try
		{
			Connection conn = SQLConnection.getDBConnection();
			Statement stmt = conn.createStatement();
			System.out.println("Inside parkingDAO");
			String searchParkingQuery = "SELECT parkingtype,parkingarea_name,capacity,floor FROM PARKING WHERE parkingtype='"+permitType+"'";
			ResultSet result = stmt.executeQuery(searchParkingQuery);
			
			while(result.next())
			{
				Parking parkingObj = new Parking();
				parkingObj.setParkingType(result.getString("parkingtype"));
				parkingObj.setParkingAreaName(result.getString("parkingarea_name"));
				parkingObj.setCapacity(Integer.toString(result.getInt("capacity")));
				parkingObj.setFloor(Integer.toString(result.getInt("floor")));
				parkingList.add(parkingObj);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return parkingList;
	}
}
