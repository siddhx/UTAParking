package UTAParking.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLConnection;
import java.sql.Statement;
import java.util.ArrayList;
import UTAParking.Model.*;
import UTAParking.Util.*;

public class UserDAO 
{
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void registerUser(User user) {
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection();  
		String registerUser = "INSERT INTO USER (username,password,last_name,first_name,role,UTA_Id,phone,email,street_address,city,state,zipcode,Parking_Permit_Type) ";					
		registerUser += " VALUES ('"  
									+ user.getUsername() + "','"
									+ user.getPassword() + "','"
									+ user.getLast_name() + "','"
									+ user.getFirst_name() + "','"
									+ user.getRole() + "','"
									+ user.getUTA_Id() + "','"
									+ user.getPhone() + "','"
									+ user.getEmail() + "','"
									+ user.getStreet_address() + "','"
									+ user.getCity() + "','"
									+ user.getState() + "','"
									+ user.getZipcode() + "','"
									+ user.getParking_Permit_Type() + ")" ;
		try {   
			conn = SQLConnection.getDBConnection();  
			conn.setAutoCommit(false);   
			stmt = conn.createStatement();
			stmt.executeUpdate(registerUser);
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
	
	public static ArrayList<User> listUsers(String lastName) {  
		Statement stmt = null;   
		Connection conn = null;  
		try {   
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String searchUser = " SELECT * from USER WHERE LAST_NAME = '"+lastName+"' ORDER BY UTA_Id";

			ResultSet userList = stmt.executeQuery(searchUser);
			ArrayList<User> userListInDB = new ArrayList<User>();

			while (userList.next()) {
				User user = new User(); 
				String utaId = userList.getString("UTA_Id");
				String username  = userList.getString("username");
				String firstname  = userList.getString("firstname");
				String lastname = userList.getString("firstname");
				String role  = userList.getString("role");
				user.setUsername(username);
				user.setFirst_name(firstname);  
				user.setLast_name(lastname);
				user.setUTA_Id(utaId);
				user.setRole(role);
				userListInDB.add(user);	 
			} 
			return userListInDB;

		} 
		catch (SQLException e) {
			e.printStackTrace();}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
		return null;
	}
	
	//if username exists in DB
	public static boolean isUnique(String username) {  
		Statement stmt = null;   
		Connection conn = null;  
		try {   
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String searchUsername = " SELECT * from USER WHERE USERNAME = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUsername);
			ArrayList<User> userListInDB = new ArrayList<User>();
			while (userList.next()) {
				User user = new User(); 
				userListInDB.add(user);	 
			} 
			return (userListInDB.isEmpty());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
		return false;
	}	
}
