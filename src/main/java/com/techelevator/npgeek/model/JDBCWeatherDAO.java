package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private static final String SELECT_FORECAST_SQL = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather";

	private JdbcTemplate jdbcTemplate;
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getForecastByPark(String parkCode) {

		String selectSql = SELECT_FORECAST_SQL + " WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSql, parkCode.toUpperCase());
		List<Weather> forecast = new ArrayList<>();
		
		while (results.next()) {
			forecast.add(mapRowToForecast(results));
		}
		return forecast;
	}

	private Weather mapRowToForecast(SqlRowSet row) {
		Weather weather = new Weather();
		
		weather.setParkCode(row.getString("parkcode").toLowerCase());
		weather.setForecastDay(row.getInt("fivedayforecastvalue"));
		weather.setLow(row.getInt("low"));
		weather.setHigh(row.getInt("high"));
		weather.setForecast(row.getString("forecast"));
		return weather;
	}

}
