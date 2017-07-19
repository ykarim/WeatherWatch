package prop;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import util.FileLoad;
import weather.Weather;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class WeatherImgImport {

    private static final String ignoreDTDTag = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    private static String backgroundImageXmlName = "imageLocations.xml";

    private static FileLoad fileLoader = new FileLoad();
    private static Element doc;

    private static boolean backgroundXmlLoaded = false;

    public static boolean loadBackgroundImageXml() {
        if (!backgroundXmlLoaded) {
            Document dom;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                dbf.setFeature(ignoreDTDTag, false);
                DocumentBuilder db = dbf.newDocumentBuilder();
                dom = db.parse(fileLoader.load(backgroundImageXmlName));

                doc = dom.getDocumentElement();
                backgroundXmlLoaded = true;
                return true;
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
                return false;
            } catch (SAXException sax) {
                sax.printStackTrace();
                return false;
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static Image getBackgroundImage(Weather.WeatherCondition condition) {
        if (backgroundXmlLoaded) {
            String imgLocation = getTextValue(doc, condition);
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

    private static String getTextValue(Element doc, Weather.WeatherCondition tag) {
        NodeList nl = doc.getElementsByTagName(tag.getName());
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            return nl.item(0).getFirstChild().getNodeValue();
        }
        return null;
    }
}
