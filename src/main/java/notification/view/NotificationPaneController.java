package notification.view;

import notification.events.WeatherEvent;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import util.Constants;


class NotificationPaneController {

    private WeatherEvent weatherEvent;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(Constants.DATE_TIME_FORMAT);

    NotificationPaneController(WeatherEvent weatherEvent) {
        this.weatherEvent = weatherEvent;
    }

    String getWeatherEventText() {
        StringBuilder weatherEventTextBuilder = new StringBuilder();
        weatherEventTextBuilder.append(weatherEvent.getCondition());
        weatherEventTextBuilder.append("at");
        weatherEventTextBuilder.append(dateTimeFormatter.print(weatherEvent.getForecast().getTimeOfWeather()));
        weatherEventTextBuilder.append(".");
        return weatherEventTextBuilder.toString();
    }
}
