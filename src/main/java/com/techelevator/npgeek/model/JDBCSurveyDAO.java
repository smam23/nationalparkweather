package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JDBCSurveyDAO implements SurveyDAO {
	
	private JdbcTemplate jdbcTemplate;
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void submitSurvey(Survey survey) {

		String sqlInsertSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES(?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, survey.getParkCode().toUpperCase(), survey.getEmail(), survey.getState(), survey.getActivityLevel());
		}

	@Override
	public List<SurveyResult> surveyResults() {
		List<SurveyResult> surveys = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT park.parkname, survey_result.parkcode, COUNT(survey_result.*) AS votes FROM survey_result JOIN park ON survey_result.parkcode = park.parkcode GROUP BY survey_result.parkcode, park.parkname ORDER BY votes DESC, park.parkname ASC");
		while (results.next()) {
			surveys.add(mapRowToResults(results));
		}
		return surveys;
	}
	
	private SurveyResult mapRowToResults(SqlRowSet row) {
		SurveyResult surveyResult = new SurveyResult();
		surveyResult.setParkName(row.getString("parkname"));
		surveyResult.setParkCode(row.getString("parkcode").toLowerCase());
		surveyResult.setVoteCount(row.getInt("votes"));
		return surveyResult;
	}

		

}
