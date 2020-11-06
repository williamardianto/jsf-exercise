package com.example.view;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named
@ViewScoped
public class TemperatureConverter implements Serializable {

	private Double celsius = null;
	private Double fahrenheit = null;
	
	public Double getCelsius() {
		return celsius;
	}
	public void setCelsius(Double celsius) {
		this.celsius = celsius;
	}
	public Double getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(Double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
	
	public void toCelsius() {
		this.celsius = truncateDecimalPoint((this.fahrenheit - 32.0) * (5.0/9.0));
	}
	
	public void toFahrenheit() {
		this.fahrenheit = truncateDecimalPoint(this.celsius * (9.0/5.0) + 32.0);
	}
	
	private Double truncateDecimalPoint(Double input) {
		return BigDecimal.valueOf(input).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
