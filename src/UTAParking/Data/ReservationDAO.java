package UTAParking.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLConnection;
import java.sql.Statement;
import java.util.ArrayList;
import UTAParking.model.*;
import UTAParking.util.SQLConnection;

public class ReservationDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	/*
	 * This function returns the permit type of a given user
	 */
	public static String getPermitType(String username)
	{
		Statement stmt = null;
		Connection conn= null;
		String ParkingPermitType="";
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			  String getPermitTypeQuery = "SELECT parkingpermittype from USER WHERE USERNAME= '"+username+"'";
			  ResultSet rs = stmt.executeQuery(getPermitTypeQuery);
			  while(rs.next())
			  {
				  ParkingPermitType = rs.getString("username");
			  }
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
		return  ParkingPermitType;
	}
}
