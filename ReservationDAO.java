package UTAParking.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLConnection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
				  ParkingPermitType = rs.getString("parkingpermittype");
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
	
	public static List<Reservation> getParkingArea(String permitType) throws SQLException
	{
		Statement stmt = null;
		Connection conn= null;
		List<Reservation> listPermitType = new ArrayList<Reservation>();
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			  String getPermitTypeQuery = "SELECT parkingarea_name from PARKING WHERE parkingtype= '"+permitType+"'";
			  ResultSet rs = stmt.executeQuery(getPermitTypeQuery);
			  while(rs.next())
			  {
				  Reservation reserveObj = new Reservation();
				  reserveObj.setParkingAreaName(rs.getString("parkingarea_name"));
				  listPermitType.add(reserveObj);			  }
			 
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
			return listPermitType;
		
	}
	
	public static Reservation getReservation(int id) {
		Statement stmt = null;   
		Connection conn = null;  
		Reservation reservation = new Reservation();
		try {   
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String reservationDetails = " SELECT * from parking WHERE id = '"+id+"'";
			ResultSet reservationList = stmt.executeQuery(reservationDetails);
			while(reservationList.next()) {
				int id1 = reservationList.getInt("id");
				String parkingType = reservationList.getString("parkingtype");
				String parkingAreaName  = reservationList.getString("parkingarea_name");
				int capacity  = reservationList.getInt("capacity");
				int floor = reservationList.getInt("floor");
				float cart  = reservationList.getFloat("cart");
				float camera  = reservationList.getFloat("camera");
			    float history = reservationList.getFloat("history");
			    
				
				//set User
			    reservation.setId(id1);
			    reservation.setParkingType(parkingType);
			    reservation.setParkingAreaName(parkingAreaName);
			    reservation.setCapacity(capacity);
			    reservation.setFloor(floor);
			    reservation.setCart(cart);
			    reservation.setCamera(camera);
			    reservation.setHistory(history);
				
				
			
				
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
		return reservation;
	}
	
	public static String addParkingArea(String parkingAreaName)
	{
		Statement stmt = null;
		Connection conn= null;
		String ParkingArea="";
		String parkingAreaName1;
		
		Scanner in = new Scanner(System.in);
		parkingAreaName1 = in.nextLine();
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			  String addParkingAreaQuery = "INSERT into parking (parkingarea_name) VALUE = "+parkingAreaName1;
			  ResultSet rs = stmt.executeQuery(addParkingAreaQuery);
			  while(rs.next())
			  {
				  ParkingArea = rs.getString("parkingAreaName");
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
		return  ParkingArea;
	}
	
	
	/*public static List<Reservation> getPermitType(int id, String cart)throws SQLException
	{
		Statement stmt = null;
		Connection conn= null;
		List<Reservation> reservationDetails = new ArrayList<Reservation>();
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			  String reservationDetailsQuery = "SELECT * from parking WHERE id= '"+id+"'";
			  ResultSet rs = stmt.executeQuery(reservationDetailsQuery);
			  while(rs.next())
			  {
				  Reservation reserveObj = new Reservation();
				  reserveObj.setId(rs.getInt(id));
				  reserveObj.setParkingType(rs.getString("parkingtype"));
				  reserveObj.setParkingAreaName(rs.getString("parkingarea_name"));
				
				reserveObj.setCart(rs.getFloat(cart));
				  reserveObj.set
				  ParkingPermitType = rs.getString("parkingpermittype");
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
	}*/
}
