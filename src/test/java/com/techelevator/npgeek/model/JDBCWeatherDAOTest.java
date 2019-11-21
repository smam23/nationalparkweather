package com.techelevator.npgeek.model;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;

public class JDBCWeatherDAOTest extends DAOIntegrationTest{
	
	private JdbcTemplate jdbcTemplate;
	private WeatherDAO dao;
	
	@Before
	public void setup() {
	    dao = new JDBCWeatherDAO(getDataSource());
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	}

	@Test
	public void get_forecast_by_parkcode() {
		String sql = "SELECT COUNT(*) FROM weather WHERE parkcode = 'GNP'";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		rows.next();

		List<Weather> weatherReturned = dao.getForecastByPark("GNP");
		        
		Assert.assertNotNull(weatherReturned);
		Assert.assertEquals(5, weatherReturned.size());
	}
}
