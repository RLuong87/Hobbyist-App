package com.hooked.app.payloads.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<StormGlass> weatherApi;

    public WeatherResponse() {
    }

    public List<StormGlass> getWeatherApi() {
        return weatherApi;
    }

    public void setWeatherApi(List<StormGlass> weatherApi) {
        this.weatherApi = weatherApi;
    }
}
