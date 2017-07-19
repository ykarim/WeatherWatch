package prop;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.FileLoad;
import weather.Weather;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class WeatherImgImport {

    private static String backgroundImageXmlName = "imageLocations.xml";
    private static String iconImageXmlName = "iconLocations.xml";

    private static FileLoad fileLoader = new FileLoad();
    private static Element backgroundXml;
    private static Element iconXml;

    private static boolean backgroundXmlLoaded = false;
    private static boolean iconXmlLoaded = false;

    public static boolean[] initiate() {
        loadBackgroundImageXml();
        loadBackgroundIconsXml();
        return new boolean[]{backgroundXmlLoaded, iconXmlLoaded};
    }

    private static boolean loadBackgroundImageXml() {
        if (!backgroundXmlLoaded) {
            if (fileLoader.loadXml(backgroundImageXmlName) != null) {
                backgroundXml = fileLoader.loadXml(backgroundImageXmlName);
                backgroundXmlLoaded = true;
                return true;
            }
        }
        return false;
    }

    private static boolean loadBackgroundIconsXml() {
        if (!iconXmlLoaded) {
            if (fileLoader.loadXml(iconImageXmlName) != null) {
                iconXml = fileLoader.loadXml(iconImageXmlName);
                iconXmlLoaded = true;
                return true;
            }
        }
        return false;
    }

    public static Image getBackgroundImage(Weather.WeatherCondition condition) {
        if (backgroundXmlLoaded) {
            return getWeatherImageFromXml(backgroundXml, condition);
        }
        return null;
    }

    public static Image getIconImage(Weather.WeatherCondition condition) {
        if (iconXmlLoaded) {
            return getWeatherImageFromXml(iconXml, condition);
        }
        return null;
    }

    private static String getTextValue(Element xml, Weather.WeatherCondition tag) {
        NodeList nl = xml.getElementsByTagName(tag.getName());
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            return nl.item(0).getFirstChild().getNodeValue();
        }
        return null;
    }

    private static Image getWeatherImageFromXml(Element xml, Weather.WeatherCondition condition) {
        String imgLocation = getTextValue(xml, condition);
        if (imgLocation != null) {
            if (!imgLocation.isEmpty()) {
                try {
                    return ImageIO.read(fileLoader.load(imgLocation));
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
        return null;
    }
}
