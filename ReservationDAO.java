package arlington_parking_app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import arlington_parking_app.model.Reservation;
import arlington_parking_app.model.ReservationDetails;
import arlington_parking_app.util.SQLConnection;

public class ReservationDAO { 
	 
	static SQLConnection DBMgr = SQLConnection.getInstance(); 
	
	//insert new reservation and return auto-gen PK
	public static int insertReservation(Reservation reservation) {
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection();  
		String query = "INSERT INTO RESERVATION (car_id,payment_id,start_time,end_time,hasgps,hasonstar,hassirius,iscanceled,isdeleted,total_price) ";					
		query += " VALUES ("  
				+ reservation.getCarId() + ","
				+ reservation.getPaymentId() + ",'"		
				+ reservation.getStartTimeAsString() + "','"
				+ reservation.getEndTimeAsString() + "'," 
				+ reservation.getHasGps() + ","
				+ reservation.getHasOnstar() + ","
				+ reservation.getHasSirius() + ","
				+ reservation.getIsCanceled() + ","
				+ reservation.getIsDeleted() + ","
				+ reservation.getTotalPrice() + ")" ;
		PreparedStatement pstmt;
		int key = 0;
		try {
			conn = SQLConnection.getDBConnection();  
			conn.setAutoCommit(false);  
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);			  
			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();		 
			keys.next();
			key = keys.getInt(1);
			keys.close();
			pstmt.close();
			conn.commit();	
			conn.close();
		} 
		catch (Exception e) { 
				e.printStackTrace(); 
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
         }
		return key;
}
	
	//manager view all RRs
	public static ArrayList<ReservationDetails> getAllReservations() {
		String query = " SELECT reservation.id as id,username,car.name as name,capacity,start_time,end_time,hassirius,hasgps,hasonstar,total_price"
				+ " FROM reservation,user,car,payment"
				+ " WHERE payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id" 
				+ " AND iscanceled!=1 AND isdeleted !=1 ORDER BY start_time DESC";
		return getReservations(query);
	}
	
	//manager filter RRs
    public static ArrayList<ReservationDetails> getAllReservationsByDate(String searchStart, String searchEnd) {
    	String query = " SELECT reservation.id as id,username,car.name as name,capacity,start_time,end_time,hassirius,hasgps,hasonstar,total_price"
				+ " FROM reservation,user,car,payment"
				+ " WHERE payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id" 
				+ " AND iscanceled!=1 AND isdeleted !=1 AND start_time <= '"+searchEnd+"' and '"+searchStart+"' <= end_time"
				+ " ORDER BY start_time DESC";
    	
		return getReservations(query);
	}
	
	//customer view my RRs
	public static ArrayList<ReservationDetails> getAllMyReservations(int userID) {
		String query = " SELECT reservation.id as id,username,car.name as name,capacity,start_time,end_time,hassirius,hasgps,hasonstar,total_price"
				+ " FROM reservation,user,car,payment"
				+ " WHERE payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id" 
				+ " AND iscanceled!=1 AND isdeleted !=1 AND user.id="+userID+" ORDER BY start_time DESC";
		return getReservations(query);
	}
	
	//customer filter my RRs
    public static ArrayList<ReservationDetails> getAllMyReservationsByDate(int userID, String searchStart, String searchEnd) {
    	
    	String query = " SELECT reservation.id as id,username,car.name as name,capacity,start_time,end_time,hassirius,hasgps,hasonstar,total_price"
				+ " FROM reservation,user,car,payment"
				+ " WHERE payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id" 
				+ " AND iscanceled!=1 AND isdeleted !=1  AND user.id="+userID+" AND start_time <= '"+searchEnd+"' and '"+searchStart+"' <= end_time"
				+ " ORDER BY start_time DESC";
    	
		return getReservations(query);
	}
	
	//retrieve list of RRs from DB
	private static ArrayList<ReservationDetails> getReservations(String query) {
		
		ArrayList<ReservationDetails> resultList = new ArrayList<ReservationDetails>();	
		Statement stmt = null;   
		Connection conn = null;  	
		try {   
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			ResultSet reservationList = stmt.executeQuery(query);
			while(reservationList.next()) {
				ReservationDetails reservationDetails = new ReservationDetails();
				int id = reservationList.getInt("id");
				String username = reservationList.getString("username");
				String carName  = reservationList.getString("name");
				int capacity  = reservationList.getInt("capacity");
				String startTimeAsString = reservationList.getTimestamp("start_time").toLocalDateTime().toString();
				String endTimeAsString = reservationList.getTimestamp("end_time").toLocalDateTime().toString();
				int hasGps  = reservationList.getInt("hasgps");
				int hasSirius  = reservationList.getInt("hassirius");
				int hasOnstar  = reservationList.getInt("hasonstar");
				double totalPrice = reservationList.getDouble("total_price");
				
				reservationDetails.setReservationDetails(id,username, carName, capacity, 
						startTimeAsString, endTimeAsString, hasGps, hasSirius, hasOnstar,totalPrice);					
				
				resultList.add(reservationDetails);
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

		return resultList;
	}
	
	
	//retrieve a specific RR from DB
	public static ReservationDetails getReservationById(int resId) {
			
			ReservationDetails reservationDetails = new ReservationDetails();
			Statement stmt = null;   
			Connection conn = null;  	
			String query = " SELECT reservation.id as id,username,car.name as name,capacity,start_time,end_time,hassirius,hasgps,hasonstar,total_price"
					+ " FROM reservation,user,car,payment"
					+ " WHERE reservation.id="+resId+" AND payment.id=reservation.payment_id AND payment.user_id=user.id AND car.id=reservation.car_id" 
					+ " AND iscanceled!=1 AND isdeleted !=1 ORDER BY start_time DESC";
			try {   
				conn = SQLConnection.getDBConnection();  
				stmt = conn.createStatement();
				ResultSet reservationList = stmt.executeQuery(query);
				while(reservationList.next()) {
					int id = reservationList.getInt("id");
					String username = reservationList.getString("username");
					String carName  = reservationList.getString("name");
					int capacity  = reservationList.getInt("capacity");
					String startTimeAsString = reservationList.getTimestamp("start_time").toLocalDateTime().toString();
					String endTimeAsString = reservationList.getTimestamp("end_time").toLocalDateTime().toString();
					int hasGps  = reservationList.getInt("hasgps");
					int hasSirius  = reservationList.getInt("hassirius");
					int hasOnstar  = reservationList.getInt("hasonstar");
					double totalPrice = reservationList.getDouble("total_price");
					
					reservationDetails.setReservationDetails(id,username, carName, capacity, 
							startTimeAsString, endTimeAsString, hasGps, hasSirius, hasOnstar,totalPrice);						
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

			return reservationDetails;
	}
		
	
	//customer cancel RR
	public static void cancelReservation(int resId) {
		String query = "UPDATE reservation SET iscanceled=1 WHERE id="+resId;
		removeReservation(query);
	}
	//manager delete RR
	public static void deleteReservation(int resId) {
		String query = "UPDATE reservation SET isdeleted=1 WHERE id="+resId;
		removeReservation(query);
	}
	
	//update RR as canceled or deleted in DB
	private static void removeReservation(String query) {
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection();  
		try {   
			conn = SQLConnection.getDBConnection();  
			conn.setAutoCommit(false);   
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();					 
		} catch (SQLException sqle) { 
			sqle.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}					
	}	

}
