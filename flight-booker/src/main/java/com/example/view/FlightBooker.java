package com.example.view;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Named
@ViewScoped
public class FlightBooker implements Serializable {

	private String flightType="one-way flight";

	private boolean disableReturn=true;
	
	String pattern = "dd.MM.yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	private String toDate = simpleDateFormat.format(new Date());
	private String returnDate = simpleDateFormat.format(new Date());
	
	private boolean toDateError = false;
	private boolean returnDateError = false;
	
	private boolean disableBook=false;

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	
	public boolean isDisableReturn() {
		return disableReturn;
	}

	public void setDisableReturn(boolean disableReturn) {
		this.disableReturn = disableReturn;
	}	

	public boolean isDisableBook() {
		return disableBook;
	}

	public void setDisableBook(boolean disableBook) {
		this.disableBook = disableBook;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	public boolean isToDateError() {
		return toDateError;
	}

	public void setToDateError(boolean toDateError) {
		this.toDateError = toDateError;
	}

	public boolean isReturnDateError() {
		return returnDateError;
	}

	public void setReturnDateError(boolean returnDateError) {
		this.returnDateError = returnDateError;
	}

	public void changeFlightType() {
		if (this.flightType.equals("one-way flight")) {
			this.disableReturn = true;
			if (isValidDate(this.toDate)) {
				this.disableBook=false;
			}else {
				this.disableBook=true;
			}
		}else {
			this.disableReturn = false;
			if (isValidReturnDate() && isValidDate(this.toDate) && isValidDate(this.returnDate)) {
				this.disableBook=false;
			}else {
				this.disableBook=true;
			}
		}
	}
	
	public void toDateChanged() {
		if (isValidDate(this.toDate)) {
			this.disableBook=false;
			this.toDateError=false;
		}else {
			this.disableBook=true;
			this.toDateError=true;
		}
	}
	
	public void returnDateChanged() {
		if (isValidDate(this.returnDate)) {
			this.returnDateError=false;
			if (isValidReturnDate()) {
				this.disableBook=false;
			}else {
				this.disableBook=true;
			}
		}else {
			this.disableBook=true;
			this.returnDateError=true;
		}
	}
	
	private boolean isValidDate(String date) {
		try {
			DateTimeFormatter.ofPattern(this.pattern).parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidReturnDate() {
		try {
			this.simpleDateFormat.setLenient(false);
			Date toDate = this.simpleDateFormat.parse(this.toDate);
			Date returnDate = this.simpleDateFormat.parse(this.returnDate);
			
			if(returnDate.compareTo(toDate) >= 0) {
				return true;
			}else {
				return false;
			}
		}catch (ParseException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


}
