package com.techelevator.npgeek.model;

import java.util.List;

public interface SurveyDAO {
	
	public void submitSurvey(Survey survey);
	
	public List<SurveyResult> surveyResults();
	

}
