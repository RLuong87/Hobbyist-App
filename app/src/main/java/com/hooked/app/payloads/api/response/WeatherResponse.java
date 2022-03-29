package com.hooked.app.payloads.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<WeatherAPI> weatherApi;

    public WeatherResponse() {
    }

    public List<WeatherAPI> getWeatherApi() {
        return weatherApi;
    }

    public void setWeatherApi(List<WeatherAPI> weatherApi) {
        this.weatherApi = weatherApi;
    }
}
