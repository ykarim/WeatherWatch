package prop;

public class ProgramPropertyStore {

    private static final String propertyFileLocation = System.getProperty("user.home") + "/WeatherWatch/prop.xml";
    private static PropertiesDAO propertiesDAO = new PropertiesDAO(propertyFileLocation);

    public static void loadPropertiesFromFile(PropertyUpdater... propertyUpdater) {
        if (propertiesDAO.loadPropertiesFile()) {
            for (PropertyUpdater updaters : propertyUpdater) {
                updaters.update(propertiesDAO);
            }
        } else {
            //Using default properties
        }
    }

    public static void savePropertiesToFile(PropertyStorage... propertyStorage) {
        for (PropertyStorage storage : propertyStorage) {
            storage.storeProperties(propertiesDAO);
        }
        propertiesDAO.savePropertiesToXml();
    }
}
