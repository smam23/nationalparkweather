package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO {

	private static final String SELECT_PARKS_SQL = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park";

	private JdbcTemplate jdbcTemplate;
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		
		List<Park> allParks = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_PARKS_SQL + " ORDER BY parkname asc");
		while (results.next()) {
			allParks.add(mapRowToPark(results));
		}
		
		return allParks;
	}
	

	@Override
	public Park getSelectedPark(String parkCode) {
		
		String selectSql = SELECT_PARKS_SQL + " WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, parkCode.toUpperCase());
		Park selectedPark = new Park();
		
		while (results.next()) {
			selectedPark = mapRowToPark(results);
		}
		
		return selectedPark;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode").toLowerCase());
		park.setParkName(row.getString("parkname"));
		park.setAcreage(row.getLong("acreage"));
		park.setElevation(row.getLong("elevationinfeet"));
		park.setTrailMiles(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYear(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getLong("annualvisitorcount"));
		park.setQuote(row.getString("inspirationalquote"));
		park.setQuoteSource(row.getString("inspirationalquotesource"));
		park.setDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		park.setState(row.getString("state"));
		return park;
	}
}
