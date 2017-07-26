package prop;

/**
 * Executed to store certain properties into files
 */
public interface PropertyStorage {

    /**
     * Specifies which properties to store into properties file
     *
     * @param propertiesDAO
     */
    void storeProperties(PropertiesDAO propertiesDAO);
}
