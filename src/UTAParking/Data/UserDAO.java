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
