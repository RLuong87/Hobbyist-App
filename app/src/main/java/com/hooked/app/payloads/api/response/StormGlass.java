package com.hooked.app.payloads.api.response;

public class StormGlass {

    private AirTemperature airTemperature;
    private WaveHeight waveHeight;

    private static class AirTemperature {

        private float dwd;
        private float noaa;
        private float sg;
        private float smhi;

        public float getDwd() {
            return dwd;
        }

        public void setDwd(float dwd) {
            this.dwd = dwd;
        }

        public float getNoaa() {
            return noaa;
        }

        public void setNoaa(float noaa) {
            this.noaa = noaa;
        }

        public float getSg() {
            return sg;
        }

        public void setSg(float sg) {
            this.sg = sg;
        }

        public float getSmhi() {
            return smhi;
        }

        public void setSmhi(float smhi) {
            this.smhi = smhi;
        }
    }

    private String time;

    private class WaveHeight {

        private float dwd;
        private float icon;
        private float meteo;
        private float noaa;
        private float sg;

        public float getDwd() {
            return dwd;
        }

        public void setDwd(float dwd) {
            this.dwd = dwd;
        }

        public float getIcon() {
            return icon;
        }

        public void setIcon(float icon) {
            this.icon = icon;
        }

        public float getMeteo() {
            return meteo;
        }

        public void setMeteo(float meteo) {
            this.meteo = meteo;
        }

        public float getNoaa() {
            return noaa;
        }

        public void setNoaa(float noaa) {
            this.noaa = noaa;
        }

        public float getSg() {
            return sg;
        }

        public void setSg(float sg) {
            this.sg = sg;
        }
    }

    public AirTemperature getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(AirTemperature airTemperature) {
        this.airTemperature = airTemperature;
    }

    public WaveHeight getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(WaveHeight waveHeight) {
        this.waveHeight = waveHeight;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
