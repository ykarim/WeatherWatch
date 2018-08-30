package model;

import org.joda.time.DateTime;

import java.net.URL;

public class Weather {

    private Location location;
    private Temperature temperature;
    private Temperature highTemperature;
    private Temperature lowTemperature;
    private String condition;
    private Double humidity;
    private Double pressure;
    private DateTime sunriseTime;
    private DateTime sunsetTime;
    private DateTime weatherTime;
    private URL imageURL;

    public Weather(Location location, Temperature temperature, Temperature highTemperature, Temperature lowTemperature,
                   String condition, Double humidity, Double pressure,
                   DateTime sunriseTime, DateTime sunsetTime, DateTime weatherTime, URL imageURL) {
        this.location = location;
        this.temperature = temperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.condition = condition;
        this.humidity = humidity;
        this.pressure = pressure;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.weatherTime = weatherTime;
        this.imageURL = imageURL;
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

    public Temperature getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(Temperature highTemperature) {
        this.highTemperature = highTemperature;
    }

    public Temperature getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(Temperature lowTemperature) {
        this.lowTemperature = lowTemperature;
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

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }
}
