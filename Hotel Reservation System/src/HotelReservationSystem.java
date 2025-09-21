import java.sql.DriverManager;
import java.sql. SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;

public class HotelReservationSystem {
	
	private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
	private static final String username = "root";
	private static final String password="Ratankumar@27";

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
    
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Scanner sc = new Scanner(System.in);
			
			System.out.println("-----Welcome to Hotel Reservation-----");
			
			while(true) {
				System.out.println();
				System.out.println("1. Reserve a Room");
				System.out.println("2. View Reservations");
				System.out.println("3. Get Room Number");
				System.out.println("4. Update Reservations");
				System.out.println("5. Delete  Reservations");
				System.out.println("0. Exit");
				System.out.println("Choose an option:");
				int option = sc.nextInt();
				
				switch(option) {
					case 1 : reserveRoom(connection, sc);
						break;
						
					case 2 : viewReservations(connection, sc);
						break;
					
					case 3 : getRoomNum(connection, sc);
						break;
						
					case 4 : updateReservation(connection, sc);
						break;
						
					case 5 : deleteReservation(connection, sc);
						break;
						
					case 0 : exit();
					        sc.close();
					        return;
						
					default: System.out.println("Invalid Choice. Try Again.");	
				}
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}

	
	
	public static void reserveRoom(Connection connection, Scanner sc){
		
		System.out.println();
		sc.nextLine();
		System.out.println("Enter Guest Name:");
		String guestName= sc.nextLine();

		System.out.println("Enter Room Number:");
		int roomNum= sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Contact Number:");
		String contactNum= sc.nextLine();
		
		String query = "Insert into reservations (guest_name, room_num, contact_num) values('" + guestName +"', " + roomNum + ", '" + contactNum+"')";
		try {
	    Statement statement = connection.createStatement();	
	    int rowsEffected = statement.executeUpdate(query);
	    if(rowsEffected >0) {
	    	System.out.println("Reservation Successfull!!");
	    }
	    else {
	    	System.out.println("Reservation Failed");
	    }
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static void viewReservations(Connection connection, Scanner sc) throws SQLException {
		String query = "Select * from reservations";
		try 
		   (Statement statement = connection.createStatement();
		   ResultSet resultSet = statement.executeQuery(query);){
		   System.out.println("Current reservations:");
		   System.out.println("+---------+--------------------------+----------+-----------------------+-------------------------------+");
		   System.out.println("|    ID   |          Name            |    Room  |        Contact        |             Date              |");
		   System.out.println("+---------+--------------------------+----------+-----------------------+-------------------------------+");
		   
		   while(resultSet.next()) {
			   int resId = resultSet.getInt("reservation_id");
			   String name = resultSet.getString("guest_name");
			   int room = resultSet.getInt("room_num");
			   String cont = resultSet.getString("contact_num");
			   String date = resultSet.getTimestamp("reservation_date").toString();
			   
			   System.out.printf("| %-7d | %-24s | %-8d | %-21s | %-29s | \n", resId, name, room, cont, date );
			   
		   }
		   System.out.println("+---------+--------------------------+----------+-----------------------+-------------------------------+");
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void getRoomNum(Connection connection, Scanner sc) {
		try {
		sc.nextLine();
		System.out.println("Enter Reservation Id:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Guest Name:");
		String name = sc.nextLine();
		String query = "Select * from reservations where reservation_id ="+id+" and guest_name='"+name+"'";
		try 
			(Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(query);){
			if(resultSet.next()) {
				int roomnum=resultSet.getInt("room_num");
				System.out.println("Room Number for reservation id "+id+" and guest name "+name+" is: " +roomnum);
			}
			else {
				System.out.println("Reservation not found for the given id and guest name");
			}
		}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateReservation(Connection connection, Scanner sc) {
		try {
			sc.nextLine();
			System.out.println("Enter Reservation ID:");
			int resId = sc.nextInt();
			sc.nextLine();
			
			if(!reservationExists(connection, resId)) {
				System.out.println("Reservation not found for the given ID: "+ resId);
				return;
			}
			
			System.out.print("Enter new Guest Name: ");
			String newGuest = sc.nextLine();
			System.out.print("Enter new Room Number: ");
			int roomNum = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter new Contact Number: ");
			String contNum = sc.nextLine();
			
			String query = "Update reservations set guest_name = '"+newGuest+"' , room_num = "+roomNum +", contact_num = '"+contNum+"' "
					+ "where reservation_id = " +resId ;
			
			try(Statement statement = connection.createStatement()){
				int rowsAffected = statement.executeUpdate(query);
				
				if(rowsAffected > 0) {
					System.out.println("Reservation updated Seccessfully!");
			    }
				else {
					System.out.println("Reservation updation failed!!");
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void deleteReservation(Connection connection, Scanner sc) {
		try {
			System.out.print("Enter Reservation Id to delete: ");
			int id = sc.nextInt();
			
			if(!reservationExists(connection, id)) {
				System.out.println("Reservation not found for the given ID: "+ id);
				return;
			}
			
			String query = "Delete from reservations where reservation_id = "+id;
			try(Statement statement = connection.createStatement()){
				int rowAffected = statement.executeUpdate(query);
				if(rowAffected > 0) {
					System.out.println("Reservation Deleted Successfully!");
				}
				else {
					System.out.println("Failed to delete Reservation!");
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static boolean reservationExists(Connection connection, int reserId) {
		
		try {
			String query = "Select * from reservations where reservation_id = "+ reserId;
			try(Statement statement = connection.createStatement();
			    ResultSet resultSet = statement.executeQuery(query)){
			
			    return resultSet.next();
		    }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;	
		}
	}
	
	
	public static void exit() throws InterruptedException {
		
		System.out.print("Exiting System");
		int i=5;
		while(i>=0) {
			System.out.print(".");
			Thread.sleep(450);
			i--;
		}
		
		System.out.println();
		System.out.println();
		System.out.println("THANK YOU FOR USING HOTEL RESERVATION SYSTEM!!");
	}
}









