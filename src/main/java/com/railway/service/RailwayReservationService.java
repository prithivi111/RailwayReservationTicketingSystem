package com.railway.service;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.railway.DOA.TicketingDOAandOperations;
import com.railway.model.RailwayReservations;
import com.railway.propertiesfilewritingandreading.PropertiesWriteAndRead;


public class RailwayReservationService {
	
	static Logger log = Logger.getLogger(RailwayReservationService.class);
	
	public static void main(String[] args) {
		
		log.info("RailwayReservationService -> main invoked");
		
		//QNo.1 (Related) Writing and reading from properties file
		PropertiesWriteAndRead.properitesWrite();		
		Properties property = PropertiesWriteAndRead.propertiesRead();
		
		log.info("Write/Read Operations from the properties file finished");
		
		//QNo.1(Related) Creating Database Table
		TicketingDOAandOperations.createTable(property);
		
		//QNo.2(Related) Taking User Input First
		RailwayReservations railwayReservations = TicketingDOAandOperations.takingUserInput();
        
		
		 //QNo.2 (Related) Inserting the record into Database
		 TicketingDOAandOperations.insertingIntoDB(railwayReservations, property);
		 
		 //QNo.3 and 4 (Related) Updating the record with column name ‘is_senior_citizen’ and ‘amount_paid’.
		 TicketingDOAandOperations.updatingDB(railwayReservations, property);
		
	}

}


