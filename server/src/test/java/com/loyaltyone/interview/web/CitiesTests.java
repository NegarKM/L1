package com.loyaltyone.interview.web;

import com.loyaltyone.interview.web.bean.CityVO;
import com.loyaltyone.interview.web.controller.CityController;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;

public class CitiesTests {
    @Test
    public void getCities_ok() {
        try {
            CityController.getCitiesFromThirdParty();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCityDetails_OK() {
        try {
            String cityName = "Toronto";
            Double longitude = -79.39;
            Double latitude = 43.65;
            Double temperature = 275.37;
            CityVO entity = CityController.getCityDetailsFromThirdParty(cityName);

            then(Objects.requireNonNull(entity).getName()).isEqualTo(cityName);
            then(entity.getLatitude()).isEqualTo(latitude);
            then(entity.getLongitude()).isEqualTo(longitude);
            then(entity.getTemperature()).isCloseTo(temperature, Percentage.withPercentage(1));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
