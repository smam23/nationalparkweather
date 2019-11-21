package com.techelevator.npgeek.model;

public class Weather {

	private String parkCode;
	private Integer forecastDay;
	private Integer low;
	private Integer high;
	private String forecast;
	private Integer celsiusLow;
	private Integer celsiusHigh;
	
	public Integer getCelsiusLow() {
		return celsiusLow;
	}
	public void setCelsiusLow() {
		this.celsiusLow = tempConvert(low);
	}
	public Integer getCelsiusHigh() {
		return celsiusHigh;
	}
	public void setCelsiusHigh() {
		
		this.celsiusHigh = tempConvert(high);
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public Integer getForecastDay() {
		return forecastDay;
	}
	public void setForecastDay(Integer forecastDay) {
		this.forecastDay = forecastDay;
	}
	public Integer getLow() {
		return low;
	}
	public void setLow(Integer low) {
		this.low = low;
		setCelsiusLow();
	}
	public Integer getHigh() {
		return high;
	}
	public void setHigh(Integer high) {
		this.high = high;
		setCelsiusHigh();
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		if (forecast.equals("partly cloudy")) {
		forecast = "partlyCloudy";
		}
		this.forecast = forecast;
	}
	
	public Integer tempConvert(Integer fTemp) {
		Integer cTemp = (fTemp -32) * 5/9;
		return cTemp;
	}
}