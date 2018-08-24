package model;

import org.joda.time.DateTime;

public class Weather {

    private Location location;
    private Temperature temperature;
    private String condition;
    private Double humidity;
    private Double pressure;
    private DateTime sunriseTime;
    private DateTime sunsetTime;
    private DateTime weatherTime;

    public Weather(Location location, Temperature temperature, String condition, Double humidity, Double pressure,
                   DateTime sunriseTime, DateTime sunsetTime, DateTime weatherTime) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.pressure = pressure;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.weatherTime = weatherTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public DateTime getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(DateTime sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public DateTime getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(DateTime sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public DateTime getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(DateTime weatherTime) {
        this.weatherTime = weatherTime;
    }
}
