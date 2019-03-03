package UTAParking.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLConnection;
import java.sql.Statement;
import java.util.ArrayList;

import UTAParking.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import UTAParking.model.User;
import UTAParking.util.*;

public class UserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	
	public static void registerUser(User user) {
		Statement stmt = null;   
		Connection conn = SQLConnection.getDBConnection();  
		String registerUser = "INSERT INTO USER (utaid,username,first_name,last_name,password,email,phone,role,state,address,zipcode,parkingpermitype,city) ";					
		registerUser += " VALUES ('"  
				+ user.getUtaId() + "','"
				+ user.getUsername() + "','"		
				+ user.getFirstName() + "','"
				+ user.getLastName() + "','" 
				+ user.getPassword()  + "','"
				+ user.getEmail() + "','"
				+ user.getPhone() + "','"		
				+ user.getRole() + "','"
				+ user.getState() + "','"
				+user.getAddress() + "','"
				+user.getZipcode() + "','"
				+user.getParkingpermitype() + "','"
				+ user.getCity() + "')" ;
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
	
	
	//get a user based on username (needed for login)
	public static User getUser(String username) {
		Statement stmt = null;   
		Connection conn = null;  
		User user = new User();
		try {   
			conn = SQLConnection.getDBConnection();  
			stmt = conn.createStatement();
			String searchUsername = " SELECT * from USER WHERE USERNAME = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUsername);
			while(userList.next()) {
				int id = userList.getInt("id");
				String utaId = userList.getString("utaid");
				String firstName  = userList.getString("first_name");
				String lastName  = userList.getString("last_name");
				String password = userList.getString("password");
				String email  = userList.getString("email");
				String role  = userList.getString("role");
			    String address = userList.getString("address");
			    String parkingpermitype = userList.getString("parkingpermitype");
			    String state = userList.getString("state");
			    String zipcode = userList.getString("zipcode");
			    String phone = userList.getString("phone");
			    String city = userList.getString ("city");
				
				//set User
				user.setId(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setUtaId(utaId);
				user.setAddress(address);
				user.setRole(role);
				user.setParkingpermitype(parkingpermitype);
				user.setState(state);
				user.setZipcode(zipcode);
				user.setPhone(phone);
				user.setCity(city);
				
			
				
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
		return user;
	}
	
	//get a user based on username (for Admin)
		public static User getUserForAdmin(String username) {
			Statement stmt = null;   
			Connection conn = null;  
			User user = new User();
			try {   
				conn = SQLConnection.getDBConnection();  
				stmt = conn.createStatement();
				String searchUsername = " SELECT * from USER WHERE USERNAME = '"+username+"'";
				ResultSet userList = stmt.executeQuery(searchUsername);
				while(userList.next()) {
					int id = userList.getInt("id");
					String utaId = userList.getString("utaid");
					String firstName  = userList.getString("first_name");
					String lastName  = userList.getString("last_name");
					String password = userList.getString("password");
					String email  = userList.getString("email");
					String role  = userList.getString("role");
				    String address = userList.getString("address");
				    String parkingpermitype = userList.getString("parkingpermitype");
				    String state = userList.getString("state");
				    String zipcode = userList.getString("zipcode");
				    String phone = userList.getString("phone");
				    String city = userList.getString ("city");
				    String noShow = userList.getString("noshow_status");
				    String violationStatus = userList.getString("violation_status");
				    String status = userList.getString("status");
					
					//set User
					user.setId(id);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					user.setUtaId(utaId);
					user.setAddress(address);
					user.setRole(role);
					user.setParkingpermitype(parkingpermitype);
					user.setState(state);
					user.setZipcode(zipcode);
					user.setPhone(phone);
					user.setCity(city);
					user.setViolationStatus(violationStatus);
					user.setNoShowStatus(noShow);
					user.setStatus(status);
					
					
					
				
					
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
			return user;
		}
	//check if a username already exists in DB or not
	public static boolean uniqueUsername(String username) {  
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
	/*
	 * This function returns the parking permit type of a given user
	 */
	public static String getParkingPermitType(String username)
	{
		Statement stmt = null;   
		String ParkingPermitType="";
		Connection conn = null;  
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			 //System.out.println("inside userDAO");
			  String getPermitTypeQuery = "SELECT parkingpermitype from USER WHERE USERNAME= '"+username+"'";
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
		return ParkingPermitType;
	}

	public static String getStatus(String username)
	{
		Statement stmt = null;   
		String Status="";
		Connection conn = null;  
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			 //System.out.println("inside userDAO");
			  String getStatusQuery = "SELECT status from USER WHERE USERNAME= '"+username+"'";
			  System.out.println("inside DAO and username is "+username);
			  ResultSet rs = stmt.executeQuery(getStatusQuery);
			  while(rs.next())
			  {
				  Status = rs.getString("status");
				  System.out.println("Status in DB is"+ Status);
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
		return Status;
	}

	public static String changeStatus(String username, String currentStatus)
	{
		Statement stmt = null;   
		String Status="";
		Connection conn = null; 
		 String changeStatusQuery="";
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			 //System.out.println("inside userDAO");
			 if(currentStatus.equals("1"))
			 {
				 System.out.println("inside status=1");
				 changeStatusQuery = "UPDATE USER SET status=0 WHERE USERNAME= '"+username+"'"; 
			 }
			 else
				 if(currentStatus.equals("0"))
				 {
					 changeStatusQuery = "UPDATE USER SET status=1 WHERE USERNAME= '"+username+"'";
				 }
			  
			  System.out.println("inside DAO changeStatus and username is "+username);  //debug
			 stmt.executeUpdate(changeStatusQuery);
		
			 String check="SELECT * FROM USER WHERE USERNAME= 'Niya'";
			Status="0";
			  ResultSet rs = stmt.executeQuery(check);
			  while(rs.next())
			  {
				  
				  System.out.println("am here--"+rs.getString("status"));
			  }
			 
			  conn.commit();
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
			try {
				
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return Status;
	}

	public static String getViolation(String username)
	{
		Statement stmt = null;   
		String noshow="";
		String violation="";
		String result="";
		Connection conn = null;  
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			 //System.out.println("inside userDAO");
			 System.out.println("in here");
			  String getPermitTypeQuery = "SELECT noshow_status,violation_status from USER WHERE USERNAME= '"+username+"'";
			  ResultSet rs = stmt.executeQuery(getPermitTypeQuery);
			  while(rs.next())
			  {
				  noshow = rs.getString("noshow_status");
				  violation = rs.getString("violation_status");
			  }
			  
			  if(noshow==null && violation==null)
			  {
				  System.out.println("in if");
				  result="No violation or no show for username '"+username+"'";
			  }
			  else
				  result="1";
			 
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
		return result;
	}
	
	public static String getRole(String username)
	{
		Statement stmt = null;   
			String roleinDB="";
			Connection conn = null;  
			try
			{
				 conn = SQLConnection.getDBConnection();
				 stmt =conn.createStatement();
				 //System.out.println("inside userDAO");
				  String getPermitTypeQuery = "SELECT role from USER WHERE USERNAME= '"+username+"'";
				  ResultSet rs = stmt.executeQuery(getPermitTypeQuery);
				  while(rs.next())
				  {
					  roleinDB = rs.getString("role");
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
				System.out.println("role"+roleinDB);
			return roleinDB;
		
	}
	
	public static String changeRole(String username,String role)
	{
		Statement stmt = null;   
		
		Connection conn = null; 
		 String Query="";
		 String result="";
		try
		{
			 conn = SQLConnection.getDBConnection();
			 stmt =conn.createStatement();
			 //System.out.println("inside userDAO");
			 System.out.println("inside status=1");
				 Query = "UPDATE USER SET role='"+role+"' WHERE USERNAME= '"+username+"'"; 
			 
			  System.out.println("inside DAO changrRole and username is "+username);  //debug
			 stmt.executeUpdate(Query);
			 result="Role updated successfully";
			  conn.commit();
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
			try {
				
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
		
	}
		
}