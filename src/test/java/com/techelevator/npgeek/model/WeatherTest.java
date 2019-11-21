package com.techelevator.npgeek.model;

import org.junit.*;

public class WeatherTest {
	
	private Weather weather;
	
	@Before
	public void setup() {
		weather = new Weather();
	}

	@Test
	public void fahrenheit_of_32_converts_to_celsius() {
		Integer result = weather.tempConvert(32);
		
		Assert.assertEquals(0, result, 0);
	}
	
	@Test
	public void fahrenheit_of_212_converts_to_celsius() {
		Integer result = weather.tempConvert(212);
		
		Assert.assertEquals(100, result, 0);
	}
	
	@Test
	public void fahrenheit_of_50_converts_to_celsius() {
		Integer result = weather.tempConvert(50);
		
		Assert.assertEquals(10, result, 0);
	}
	
}
