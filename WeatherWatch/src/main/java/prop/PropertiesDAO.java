package prop;

import java.io.*;

public class PropertiesDAO {

    private String propFileStorageLocation;
    private java.util.Properties properties;

    PropertiesDAO(String propFileStorageLocation) {
        this.propFileStorageLocation = propFileStorageLocation;
        properties = new java.util.Properties();
    }

    public String getPropFileStorageLocation() {
        return propFileStorageLocation;
    }

    void storeProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    String getProperty(String key) {
        return properties.getProperty(key);
    }

    boolean loadPropertiesFile() {
        if (new File(propFileStorageLocation).exists()) {
            try {
                properties.loadFromXML(new FileInputStream(propFileStorageLocation));
                return true;
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    void savePropertiesToXml() {
        try {
            properties.storeToXML(new FileOutputStream(propFileStorageLocation), null);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
