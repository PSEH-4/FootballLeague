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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;





@RestController
@RequestMapping("/api")
public class FootballController {
	
	@Autowired
	private Environment environment;

	public String getFootballApiKey() {
		return environment.getProperty("football.api.key");
	}
	
	 @GetMapping("/getStandings")
	 public ResponseEntity<JSONArray>getStanding(@RequestParam(value = "leagueid") String leagueid)
	 {
		JSONArray resp=new JSONArray();
	
		 RestTemplate restTemplate = new RestTemplate();
		 if(leagueid!=null && !leagueid.isEmpty())
		 {
			 
			 String transactionUrl = "https://apiv2.apifootball.com";

			 UriComponentsBuilder builder = UriComponentsBuilder
			     .fromUriString(transactionUrl)
			     // Add query parameter
			     .queryParam("action", "get_standings")
			     .queryParam("league_id",leagueid)
			     .queryParam("APIkey",getFootballApiKey());

			
			 String result = restTemplate.getForObject(builder.toUriString(), String.class);
			System.out.println(result);
		   
		
		 JSONParser parser = new JSONParser();
		 JSONArray obj = null;
		try {
			obj = (JSONArray)parser.parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(int i=0;i<obj.size();i++)
		 {
			 JSONObject new_data=new JSONObject();
			 JSONObject data=(JSONObject) obj.get(i);			
			 new_data.put("country_name",data.get("country_name"));
			 new_data.put("league_id",data.get("league_id"));
			 new_data.put("league_name",data.get("league_name"));
			 new_data.put("team_id",data.get("team_id"));
			 new_data.put("team_name",data.get("team_name"));
			 new_data.put("overall_league_position",data.get("overall_league_position"));
	resp.add(new_data);
		 } 
		 
		 }
		 
		 return ResponseEntity.ok(resp);
		 
	 }
	

}
