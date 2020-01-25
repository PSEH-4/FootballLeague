package com.mayank.football.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mayank.football.service.RestRequestClient;





@RestController
@RequestMapping("/api")
public class FootballController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	RestRequestClient requesthandler;

	public String getFootballApiKey() {
		return environment.getProperty("football.api.key");
	}
	
	public String StandingUrl() {
		return environment.getProperty("standings.endpoint.url");
	}
	
	public String CountryUrl() {
		return environment.getProperty("country.endpoint.url");
	}
	
	public String LeagueUrl() {
		return environment.getProperty("league.endpoint.url");
	}

	
	
	 
	 
	 @RequestMapping(value="/getStanding", method=RequestMethod.GET)
		
		public ResponseEntity<JSONArray> getStandings(@RequestParam(value = "league", defaultValue = "148") String league) throws IOException{
			
			String result = requesthandler.getResponse(StandingUrl()+league+getFootballApiKey());
			 JSONParser parser = new JSONParser();
			 JSONArray obj = null;
			try {
				obj = (JSONArray)parser.parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity.ok(obj);
		}
	 
	 @RequestMapping(value="/getbyCountries", method=RequestMethod.GET)
		
		public ResponseEntity<JSONArray> getCountries() throws IOException{
			
			
			String result = requesthandler.getResponse(CountryUrl()+getFootballApiKey());
			 JSONParser parser = new JSONParser();
			 JSONArray obj = null;
			try {
				obj = (JSONArray)parser.parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity.ok(obj);
		}
	 
	 @RequestMapping(value="/getbyLeagues", method=RequestMethod.GET)
		
		public ResponseEntity<JSONArray> getLeagues(@RequestParam(value = "country", defaultValue = "41") String country) throws IOException{
			
			String result = requesthandler.getResponse(LeagueUrl()+country+getFootballApiKey());
			 JSONParser parser = new JSONParser();
			 JSONArray obj = null;
			try {
				obj = (JSONArray)parser.parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity.ok(obj);
		}
	

}
