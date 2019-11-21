package com.techelevator.npgeek.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;


public class JDBCParkDAOTest extends DAOIntegrationTest{
	
	private JdbcTemplate jdbcTemplate;
	private ParkDAO dao;
	
	@Before 
	public void setup() {
	    dao = new JDBCParkDAO(getDataSource());
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	    
	}
	
	@Test
	public void get_park_by_parkcode() {
		Park park = getNewPark();
		save(park);
		
		Park returnedPark = dao.getSelectedPark(park.getParkCode());
		
		Assert.assertNotNull(returnedPark);
		assertParksAreEqual(park, returnedPark);
	}
	
	@Test
	public void get_all_parks() {
		// Arrange
		String sql = "SELECT COUNT(*) AS numberOfParks FROM park";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		rows.next();
		int parkCount = rows.getInt("numberOfParks");

		Park parkOne = getNewPark();
		Park parkTwo = getNewPark();
		parkTwo.setParkCode("testTwo");
		
		save(parkOne);
		save(parkTwo);

		// Act
		List<Park> returnedParks = dao.getAllParks();
		// Assert
		Assert.assertNotNull(returnedParks);
		Assert.assertEquals(parkCount + 2, returnedParks.size());
	}
	
	private Park getNewPark() {
		Park park = new Park();
		park.setParkCode("test".toUpperCase());
		park.setParkName("test");
		park.setState("test");
		park.setAcreage(10l);
		park.setElevation(10l);
		park.setTrailMiles(10d);
		park.setNumberOfCampsites(10);
		park.setClimate("test");
		park.setYear(2000);
		park.setAnnualVisitorCount(10l);
		park.setQuote("test");
		park.setQuoteSource("test");
		park.setDescription("test");
		park.setEntryFee(10);
		park.setNumberOfAnimalSpecies(10);
		
		return park;
		
	}

	private void assertParksAreEqual(Park expected, Park actual) {
		assertEquals(expected.getParkName(), actual.getParkName());
	}
	
	private void save(Park park) {
		String insertSql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(insertSql, park.getParkCode(),
				park.getParkName(),
				park.getState(),
				park.getAcreage(), 
				park.getElevation(), 
				park.getTrailMiles(), 
				park.getNumberOfCampsites(), 
				park.getClimate(), 
				park.getYear(), 
				park.getAnnualVisitorCount(), 
				park.getQuote(), 
				park.getQuoteSource(), 
				park.getDescription(), 
				park.getEntryFee(), 
				park.getNumberOfAnimalSpecies());	    
	}

}
