package com.hooked.app.payloads.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAPI {

    private Location location;
    private Current current;

    private class Location {

        private String name;
        private String region;
        private String country;
        private String lat;
        private String lon;
        private String tz_id;
        private String localtime_epoch;
        private String localtime;

        public Location() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTz_id() {
            return tz_id;
        }

        public void setTz_id(String tz_id) {
            this.tz_id = tz_id;
        }

        public String getLocaltime_epoch() {
            return localtime_epoch;
        }

        public void setLocaltime_epoch(String localtime_epoch) {
            this.localtime_epoch = localtime_epoch;
        }

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {

            this.localtime = localtime;
        }
    }

    private class Current {

        private String last_updated_epoch;
        private String last_updated;
        private String temp_c;
        private String temp_f;
        private String is_day;
        private Condition condition;

        public Current() {
        }

        private static class Condition {

            private String text;
            private String icon;
            private String code;

            public Condition() {
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {

                this.code = code;
            }
        }

        private String wind_mph;
        private String wind_kph;
        private String wind_degree;
        private String wind_dir;
        private String pressure_mb;
        private String pressure_in;
        private String precip_mm;
        private String precip_in;
        private String humidity;
        private String cloud;
        private String feelslike_c;
        private String feelslike_f;
        private String vis_km;
        private String vis_miles;
        private String uv;
        private String gust_mph;
        private String gust_kph;

        public String getLast_updated_epoch() {
            return last_updated_epoch;
        }

        public void setLast_updated_epoch(String last_updated_epoch) {
            this.last_updated_epoch = last_updated_epoch;
        }

        public String getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(String last_updated) {
            this.last_updated = last_updated;
        }

        public String getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(String temp_c) {
            this.temp_c = temp_c;
        }

        public String getTemp_f() {
            return temp_f;
        }

        public void setTemp_f(String temp_f) {
            this.temp_f = temp_f;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public String getWind_mph() {
            return wind_mph;
        }

        public void setWind_mph(String wind_mph) {
            this.wind_mph = wind_mph;
        }

        public String getWind_kph() {
            return wind_kph;
        }

        public void setWind_kph(String wind_kph) {
            this.wind_kph = wind_kph;
        }

        public String getWind_degree() {
            return wind_degree;
        }

        public void setWind_degree(String wind_degree) {
            this.wind_degree = wind_degree;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getPressure_mb() {
            return pressure_mb;
        }

        public void setPressure_mb(String pressure_mb) {
            this.pressure_mb = pressure_mb;
        }

        public String getPressure_in() {
            return pressure_in;
        }

        public void setPressure_in(String pressure_in) {
            this.pressure_in = pressure_in;
        }

        public String getPrecip_mm() {
            return precip_mm;
        }

        public void setPrecip_mm(String precip_mm) {
            this.precip_mm = precip_mm;
        }

        public String getPrecip_in() {
            return precip_in;
        }

        public void setPrecip_in(String precip_in) {
            this.precip_in = precip_in;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getFeelslike_c() {
            return feelslike_c;
        }

        public void setFeelslike_c(String feelslike_c) {
            this.feelslike_c = feelslike_c;
        }

        public String getFeelslike_f() {
            return feelslike_f;
        }

        public void setFeelslike_f(String feelslike_f) {
            this.feelslike_f = feelslike_f;
        }

        public String getVis_km() {
            return vis_km;
        }

        public void setVis_km(String vis_km) {
            this.vis_km = vis_km;
        }

        public String getVis_miles() {
            return vis_miles;
        }

        public void setVis_miles(String vis_miles) {
            this.vis_miles = vis_miles;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getGust_mph() {
            return gust_mph;
        }

        public void setGust_mph(String gust_mph) {
            this.gust_mph = gust_mph;
        }

        public String getGust_kph() {
            return gust_kph;
        }

        public void setGust_kph(String gust_kph) {
            this.gust_kph = gust_kph;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
