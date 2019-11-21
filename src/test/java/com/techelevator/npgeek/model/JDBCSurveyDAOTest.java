package com.techelevator.npgeek.model;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;

public class JDBCSurveyDAOTest extends DAOIntegrationTest {
	private JdbcTemplate jdbcTemplate;
	private SurveyDAO dao;
	
	@Before 
	public void setup() {
	    dao = new JDBCSurveyDAO(getDataSource());
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	    
	}
	
	@Test
	public void submit_survey() {
		// Arrange
		Survey survey = new Survey();
		survey.setParkCode("testCode".toUpperCase());
		survey.setEmail("testEmail");
		survey.setState("testState");
		survey.setActivityLevel("testLevel");

		// Act
		dao.submitSurvey(survey);
		    
		// Assert
		Assert.assertNotEquals(0d, survey.getId());
		Survey returnedSurvey = getSurveyByEmail(survey.getEmail());
		Assert.assertEquals(survey.getEmail(), returnedSurvey.getEmail());
		    
		}
	
//	Test below not passing
	@Test
	public void get_survey_results() {
		// Arrange
		String sql = "TRUNCATE survey_result CASCADE";
		jdbcTemplate.update(sql);
		
		Survey resultOne = getNewSurvey();
		Survey resultTwo = getNewSurvey();
		resultTwo.setId(2);
		
		dao.submitSurvey(resultOne);
		dao.submitSurvey(resultTwo);

		// Act
		List<SurveyResult> returnedResults = dao.surveyResults();
		// Assert
		Assert.assertNotNull(returnedResults);
		Assert.assertEquals(2, returnedResults.size());
	}

	private Survey getSurveyByEmail(String email) {

	    String selectSql = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result WHERE emailaddress = ?";
	    
	    SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, email);
	    
	    Survey survey = null;
	    if (rows.next()) {
	        survey = mapRowToSurvey(rows);
	    }
	    
	    return survey;
	}
	
	private Survey mapRowToSurvey(SqlRowSet row) {
		Survey survey = new Survey();
		survey.setParkCode(row.getString("parkcode").toLowerCase());
		survey.setEmail(row.getString("emailaddress"));
		survey.setState(row.getString("state"));
		survey.setActivityLevel(row.getString("activitylevel"));
		return survey;
	}
	
	private Survey getNewSurvey() {
		Survey testSurvey = new Survey();
//		testSurvey.setId(1);
		testSurvey.setParkCode("test");
		testSurvey.setEmail("email");
		testSurvey.setState("state");
		testSurvey.setActivityLevel("activityLevel");
		return testSurvey;
	}	

}
