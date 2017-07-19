package prop;

/**
 * Executed to update certain variables within the program after reading from properties file
 */
public interface PropertyUpdater {

    /**
     * Updates each variable with value
     *
     * @param propertiesDAO
     */
    void update(PropertiesDAO propertiesDAO);
}
