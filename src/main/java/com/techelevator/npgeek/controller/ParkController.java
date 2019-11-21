package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDAO;
import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDAO;

@Controller
@SessionAttributes("temperature")
public class ParkController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private WeatherDAO weatherDao;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showHomePage(@RequestParam (value="temperature", required = false) String temperature, HttpServletRequest request) {

		List<Park> parkList = parkDao.getAllParks();
		
		request.setAttribute("parks", parkList);
		if(temperature != null) {
			request.setAttribute("temperature", temperature);
		}
		
		return "homePage";
	}

	@RequestMapping(path="/detail", method=RequestMethod.GET)
	public String showParkDetails(@RequestParam (value="temperature", required = false) String temperature, @RequestParam String parkCode, ModelMap map) {
		
		Park park = parkDao.getSelectedPark(parkCode);
		List<Weather> forecast = weatherDao.getForecastByPark(parkCode);
		map.addAttribute("park", park);
		map.addAttribute("forecast", forecast);
		if(temperature != null) {
			map.addAttribute("temperature", temperature);
		}
		return "detail";
	}
	
	@RequestMapping(path="/detail", method=RequestMethod.POST)
	public String changeTemp(@RequestParam (value="temperature", required = false) String temperature, String parkCode, ModelMap map) {
		
		Park park = parkDao.getSelectedPark(parkCode);
		List<Weather> forecast = weatherDao.getForecastByPark(parkCode);
		map.addAttribute("temperature", temperature);
		map.addAttribute("park", park);
		map.addAttribute("forecast", forecast);
		
		
		return "redirect:/detail";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String showSurvey(ModelMap map) {
		List<Park> parkList = parkDao.getAllParks();
		map.addAttribute("parks", parkList);
		if(!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		return "survey";
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") Survey survey,
			BindingResult result, ModelMap map) {
		List<Park> parkList = parkDao.getAllParks();
		map.addAttribute("parks", parkList);
		
		if(result.hasErrors()) {
			return "survey";
		}
		surveyDao.submitSurvey(survey);
		return "redirect:/favoriteParks";
	}
	
	@RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
	public String showFavorites(HttpServletRequest request) {
		List<SurveyResult> resultList = surveyDao.surveyResults();
		request.setAttribute("results", resultList);
		return "favoriteParks";
	}
}
