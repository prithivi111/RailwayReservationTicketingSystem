package com.railway.model;

public class RailwayReservations {
	String passengerName;
	int passengerAge;
	String choosenSeat;
	String typeOfReservation;
	double amountPaid;
	Boolean isSeniorCitizen;
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public String getChoosenSeat() {
		return choosenSeat;
	}
	public void setChoosenSeat(String choosenSeat) {
		this.choosenSeat = choosenSeat;
	}
	public String getTypeOfReservation() {
		return typeOfReservation;
	}
	public void setTypeOfReservation(String typeOfReservation) {
		this.typeOfReservation = typeOfReservation;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Boolean getIsSeniorCitizen() {
		return isSeniorCitizen;
	}
	public void setIsSeniorCitizen(Boolean isSeniorCitizen) {
		this.isSeniorCitizen = isSeniorCitizen;
	}
	
	
}
