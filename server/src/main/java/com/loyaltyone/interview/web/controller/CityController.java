package com.loyaltyone.interview.web.controller;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.web.bean.CityVO;
import com.loyaltyone.interview.web.bean.PostVO;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mashape.unirest.http.Headers;

@RestController
public class CityController {
    @GetMapping("/getCities")
    public ResponseEntity<CityVO> getAllCities() {
        try {
            getCitiesFromThirdParty();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void getCitiesFromThirdParty() throws UnirestException {
        HttpResponse<String> response =
                Unirest.get("https://wft-geo-db.p.rapidapi.com/v1/geo/cities?limit=20000&countryIds=2")
                .header("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .header("x-rapidapi-key", "a5515e130bmsh9e9f6108329299fp15f975jsnc1e4dfb62494")
                .asString();
        if (response.getStatus() == HttpStatus.OK.value()) {
            String body = response.getBody();
            System.out.println(body);
        } else {
            System.out.println(response.getStatus());
        }
    }

    @GetMapping("/getCityDetails")
    public ResponseEntity<CityVO> getCityDetails(@RequestParam(name="cityName") String cityName) {
        try {
            CityVO cityVO = getCityDetailsFromThirdParty(cityName);
            if (cityVO == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //TODO: we can return detailed errors
            }
            return new ResponseEntity<>(cityVO, HttpStatus.OK);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static CityVO getCityDetailsFromThirdParty(String cityName) throws UnirestException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + ",CA&appid=87910c8235d363ce3379edeb900d86c3";
        HttpResponse<String> response =
                Unirest.get(url)
                .asString();
        if (response.getStatus() == HttpStatus.OK.value()) {
            String body = response.getBody();
            System.out.println("body is " + body);

            JSONObject bodyJson = new JSONObject(response.getBody());
            JSONObject coordObj = bodyJson.getJSONObject("coord");
            Double longitude = coordObj.getDouble("lon");
            Double latitude = coordObj.getDouble("lat");
            JSONObject mainObj = bodyJson.getJSONObject("main");
            Double temperature = mainObj.getDouble("temp");

            return createCityVO(cityName, latitude, longitude, temperature);
        } else {
            System.out.println(response.getStatus());
            //TODO: we can throw related exceptions regarding response.getStatus()
/*
            if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            } else if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            }
*/
            return null;
        }
    }

    private static CityVO createCityVO(String cityName, Double latitude, Double longitude, Double temperature) {
        temperature = temperature - 273.15;
        String temperatureInString = Math.round(temperature) + " °C";

        String latitudeInString = "";
        if (latitude > 0) {
            latitudeInString = latitude + " °N";
        } else {
            latitudeInString = latitude * -1 + " °S";
        }

        String longitudeInString = "";
        if (longitude > 0) {
            longitudeInString = longitude + " °E";
        } else {
            longitudeInString = longitude * -1 + " °W";
        }

        return new CityVO(cityName, latitudeInString, longitudeInString, temperatureInString);
    }
}
