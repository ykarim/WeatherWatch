package dataUpdate;

public interface UpdateTask {

    void refreshData();

    void setCityName(String cityName);

    void setZipCode(String zipCode, String countryCode);
}
