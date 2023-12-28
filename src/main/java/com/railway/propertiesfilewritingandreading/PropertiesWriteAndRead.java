package com.railway.propertiesfilewritingandreading;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.railway.service.RailwayReservationService;

public class PropertiesWriteAndRead {
	
	static Logger log = Logger.getLogger(PropertiesWriteAndRead.class);
	
	public static void properitesWrite() {
		//Writing into properties file.	
		Properties properties = new Properties();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("C:/Users/s011271sur/eclipse-workspace/RailwayReservationTicketingSystem/src/main/resource/DBrelatedInformation.properties");
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		
		properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/railwayreservation");
        properties.setProperty("username" , "root"); 
        properties.setProperty("password", "Suraj123@"); 
        
       try {
		properties.store(fileWriter,"The properties information being stored.");
       } catch (IOException e) {
    	   log.error(e.getStackTrace());
		}
	}
	
	public static Properties propertiesRead() {
		
		Properties properties = new Properties();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("C:/Users/s011271sur/eclipse-workspace/RailwayReservationTicketingSystem/src/main/resource/DBrelatedInformation.properties");
		} catch (FileNotFoundException e) {
			log.error(e.getStackTrace());
		}

		try {
			properties.load(fileReader);
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		return properties;
		
		}
	

}
