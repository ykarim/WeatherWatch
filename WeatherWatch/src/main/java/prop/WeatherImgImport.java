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

    private static FileLoad fileLoader = new FileLoad();
    private static Element xml;

    private static boolean backgroundXmlLoaded = false;

    public static boolean loadBackgroundImageXml() {
        if (!backgroundXmlLoaded) {
            if (fileLoader.loadXml(backgroundImageXmlName) != null) {
                xml = fileLoader.loadXml(backgroundImageXmlName);
                backgroundXmlLoaded = true;
                return true;
            }
        }
        return false;
    }

    public static Image getBackgroundImage(Weather.WeatherCondition condition) {
        if (backgroundXmlLoaded) {
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
}
