package net.response;

import org.apache.http.StatusLine;

public interface WeatherResponse {

    /**
     * Gets received response's status
     *
     * @return API response's StatusLine including status code to determine success of request
     */
    StatusLine getStatus();
}
