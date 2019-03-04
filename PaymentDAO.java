package arlington_parking_app.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import arlington_parking_app.model.Payment;
import arlington_parking_app.util.SQLConnection;

public class PaymentDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static int insertPayment(Payment payment) {
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection();  
		String query = "INSERT INTO PAYMENT (user_id,name_on_card,card_number,card_expires,card_security_code) ";					
		query += " VALUES ("  
				+ payment.getUserId() + ",'"
				+ payment.getNameOnCard() + "','"		
				+ payment.getCardNumber() + "','"
				+ payment.getCardExpires() + "','" 
				+ payment.getCardSecurityCode() +"')" ;
		
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
}