package com.railway.DOA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.railway.model.RailwayReservations;
import com.railway.util.DBConnectivity;

//QNo.2 (Related) Taking input from the user
//For making a class to be of exception class, we need to use extend and make the constructor!
class NotSeniorCitizenException extends RuntimeException { // or extends Exception
	// this is a parameterized constructor.
	NotSeniorCitizenException(String msg) { // Every constructor has by default method called super(), even if you don't
											// mention it here.
											// If we had nothing written here, this method NotSeniorCitizenException
											// will call the super class which is RuntimeException
											// which also has the super class called Exception.
											// But we are calling the super class with the parameter here, just for my
											// own understanding, and this is not necessary.
		super(msg);
	}
}

public class TicketingDOAandOperations {

	static Logger log = Logger.getLogger(TicketingDOAandOperations.class);

	public static void createTable(Properties property) {

		log.info("TicketingDOAandOperations -> createTable invoked");

		Connection conn = null;
		Statement pd = null;
		// Establishing connection
		try {
			conn = DBConnectivity.getConnection(property);
			// SQL query to create a table
			String SQL = "CREATE TABLE IF NOT EXISTS railwayreservation (" + "passenger_name VARCHAR(200),"
					+ "passenger_age INT," + "choosen_seat VARCHAR(200)," + "reservation_type VARCHAR(200),"
					+ "is_senior_citizen BIT(1)," + "amout_paid DOUBLE)";

			// creating a statement
			pd = conn.createStatement();
			pd.executeUpdate(SQL);

			log.info("Table creation in DB for RailwayReservationService finshed!!");

		} catch (SQLException e) {
			log.error(e.getStackTrace());
		}
		// closing the resources that have been opened!
		try {
			pd.close();
		} catch (SQLException e) {
			log.error(e.getStackTrace());
		}
		try {
			conn.close();
		} catch (SQLException e) {
			log.error(e.getStackTrace());
		}

	}

	public static RailwayReservations takingUserInput() {
		
		log.info("TicketingDOAandOperations -> takingUserInput invoked");
		
		Scanner sc = new Scanner (System.in);
		RailwayReservations railwayReservations = new RailwayReservations(); //constructor creating!!
		
		System.out.println("Enter the name of a passenger?");
		String nameInput= sc.next();
		railwayReservations.setPassengerName(nameInput);
	
		log.debug("User details debugging....");
		log.info("User Input Name: " + nameInput);
		
		//QNo.2 (Related)Taking the input for type of reservation (AC or NonAC)
		System.out.println("Enter the type of reservation?");
		String reservationTypeInput = sc.next();
		railwayReservations.setTypeOfReservation(reservationTypeInput);
		log.info("User Input Reservation Type: " + reservationTypeInput);

		//QNo.2 (Related)Calculating amount
		railwayReservations.setAmountPaid(railwayReservations.getTypeOfReservation().equalsIgnoreCase("AC") ? 100.0 : 60.0);
	
		//QNo.2 (Related) Validate and take input for age, isSeniorCitizen, choosenSeat
	
		int counter = 1;
			while (counter <= 3) { //This is for allowing user to input correct age 3 times only
				try {
					System.out.println("Enter the age of a passenger?");
					String ageInput = sc.next();
					railwayReservations.setPassengerAge(Integer.parseInt(ageInput));
						log.info("User Input Age:" + ageInput);
        				
									// Check if age is greater than 60
							if(railwayReservations.getPassengerAge() > 60) {
									log.info("User is a senior citizen!");
									railwayReservations.setIsSeniorCitizen(true);
									// Assign lower berth/seat for senior citizens
									railwayReservations.setChoosenSeat("Lower Berth");
            
							} else {
									try {
										throw new NotSeniorCitizenException("User is not a senior citizen."); 
										//I have created the new object (Exception object), and I can do that
										//because I have already defined one class above. It will call the constructor 
										//(in this case, constructor with parameter). The control goes to the method of 
										//the above mentioned class and then goes to JVM and JVM asks main method if the program has handled this exception!
										//And to answer JVM, I created an exception handling part below in the catch block.
										} catch (Exception e) {
											log.info(e.getMessage());
										} 		
            	
									railwayReservations.setIsSeniorCitizen(false);
									System.out.println("Enter the Chosen seat?");
									String seatInput = sc.next();
									railwayReservations.setChoosenSeat(seatInput);
									log.info("User Input Seat: " + seatInput);
							}
							// Break the loop if age is successfully parsed
							break; 
        		
				  } catch (NumberFormatException e) {
					  System.out.println("Invalid age format. Please enter a valid integer.");
					  log.error("Invalid age format. Please enter a valid integer.");
				  }
					
				counter++;
			}
		
			sc.close();
		return railwayReservations;
	
	}
	
	
	public static void insertingIntoDB(RailwayReservations railwayReservations, Properties property) {
	  
	  log.info("TicketingDOAandOperations -> insertingIntoDB invoked");
	  
	  Connection conn1= null; PreparedStatement pd1 = null; //Establishing connection
	  	try { 
	  		conn1 = DBConnectivity.getConnection(property); 
	  		String SQL1 = "insert into railwayreservation (passenger_name, passenger_age, choosen_seat, reservation_type, is_senior_citizen, amout_paid) VALUES "
	  					+ "(?,?,?,?,?,?)";
	  
	  		//creating a statement 
	  		pd1 = conn1.prepareStatement(SQL1); 
	  		pd1.setString(1,railwayReservations.getPassengerName());
	  		pd1.setInt(2,railwayReservations.getPassengerAge()); 
	  		pd1.setString(3,railwayReservations.getChoosenSeat());
	  		pd1.setString(4,railwayReservations.getTypeOfReservation());
	  		pd1.setBoolean(5,railwayReservations.getIsSeniorCitizen());
	  		pd1.setDouble(6,railwayReservations.getAmountPaid());
	  		pd1.executeUpdate();
	  
	  		log.info("The user input details inseerted in the Database.");
	  
	  } catch (SQLException e){
		  log.error(e.getStackTrace());
		  } 
	  	//closing the resources that have been opened!
	  	
	  	try {
	  	pd1.close(); 
	  	} catch (SQLException e1) {
	  	log.error(e1.getStackTrace());
	  	}
	  	
	  	try {
	  	conn1.close();
	  	} catch (SQLException e2) {
	  	log.error(e2.getStackTrace());
	  	}
	  
	 }

	public static void updatingDB(RailwayReservations railwayReservations,Properties property) {
	  
		log.info("TicketingDOAandOperations -> updatingDB invoked"); 
		Connection conn2= null;
		PreparedStatement pd2 = null; 
		
		//Establishing connection
		try {
			conn2 = DBConnectivity.getConnection(property); 
			String SQL2 = "update railwayreservation SET is_senior_citizen=? amout_paid=? where passenger_name=? "
							+ "(?,?,?,?,?,?)";
	  
			//creating a statement
			pd2 = conn2.prepareStatement(SQL2);
			pd2.setBoolean(1,railwayReservations.getIsSeniorCitizen()? true: false);
			pd2.setDouble(2,railwayReservations.getAmountPaid());
			pd2.setString(3,railwayReservations.getPassengerName());
	  
			log.debug("Updates continuing...."); 
			log.info("The updates in the Database completed as per the question no. 3 and 4!" );
	  
			}catch(SQLException e){
				log.error(e.getStackTrace());
			}

		// closing the resources that have been opened!
		try {
		pd2.close();
		} catch (SQLException e2){
		log.error(e2.getStackTrace());
		}
		
		try {
		conn2.close();
		}catch(SQLException e2){
		log.error(e2.getStackTrace());
		}

}


}