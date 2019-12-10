package com.example.weather;

public class StringsDataBase {
    private String temp =  "°";
    private String cityName;
    private String smallDescription;
    private String mainTemp;
    private String mainDescription;
    private String minTemp;
    private String maxTemp;
    private String time;
    private String sunrise;
    private String sunset;
    private String clouds;
    private String humidity;
    private String windSpeed;
    private String pressure;
    private String windDeg;
    private String visibility;
    private String units;

    public String convertDegreeToCardinalDirection(int directionInDegrees) {
        String directions[] = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        return directions[ (int)Math.round((  ((double)directionInDegrees % 360) / 45)) % 8 ];
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getMainTemp() {
        return mainTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public String getClouds() {
        return clouds;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTime() {
        return time;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
    }

    public void setMainTemp(String mainTemp) {
        this.mainTemp = mainTemp + temp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp + temp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp + temp;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds + " %";
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity + " %";
    }

    public void setPressure(String pressure) {
        this.pressure = pressure + " GPa";
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setVisibility(String visibility) {
        double visibilityMath = Math.round(Double.valueOf(visibility) / 1000);
        this.visibility = visibilityMath + " km";
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed + " km/h";
    }
}
