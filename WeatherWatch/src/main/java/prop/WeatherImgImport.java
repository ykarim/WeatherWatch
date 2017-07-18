package prop;

import network.WeatherCodes;
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
import java.util.HashMap;

public class WeatherImgImport {

    private static HashMap<Integer, Image> weatherBackgrounds = new HashMap<>();
    private static String backgroundImageXmlName = "imageLocations.xml";
    private static WeatherCodes weatherCodes = new WeatherCodes();
    private static FileLoad fileLoader = new FileLoad();

    private static boolean backgroundsLoaded = false;

    public static boolean loadBackgroundImageXml() {
        if (!backgroundsLoaded) {
            Document dom;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                        false);
                DocumentBuilder db = dbf.newDocumentBuilder();
                dom = db.parse(fileLoader.load(backgroundImageXmlName));

                Element doc = dom.getDocumentElement();

                String thunderstormImage = getTextValue(doc, "thunderstorm");
                if (thunderstormImage != null) {
                    if (!thunderstormImage.isEmpty()) {
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.THUNDERSTORM),
                                ImageIO.read(fileLoader.load(thunderstormImage)));
                    }
                }

                String drizzleImage = getTextValue(doc, "drizzle");
                if (drizzleImage != null) {
                    if (!drizzleImage.isEmpty()) {
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.DRIZZLE),
                                ImageIO.read(fileLoader.load(drizzleImage)));
                    }
                }

                String rainImage = getTextValue(doc, "rain");
                if (rainImage != null) {
                    if (!rainImage.isEmpty()) {
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.RAIN),
                                ImageIO.read(fileLoader.load(rainImage)));
                    }
                }

                String snowImage = getTextValue(doc, "snow");
                if (snowImage != null) {
                    if (!snowImage.isEmpty()) {
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.SNOW),
                                ImageIO.read(fileLoader.load(snowImage)));
                    }
                }

                String atmosphereImage = getTextValue(doc, "atmosphere");
                if (atmosphereImage != null) {
                    if (!atmosphereImage.isEmpty()) {
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.ATMOSPHERE),
                                ImageIO.read(fileLoader.load(atmosphereImage)));
                    }
                }

                String clearImage = getTextValue(doc, "clear");
                if (clearImage != null) {
                    if (!clearImage.isEmpty())
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.CLEAR),
                                ImageIO.read(fileLoader.load(clearImage)));
                }

                String extremeImage = getTextValue(doc, "extreme");
                if (extremeImage != null) {
                    if (!extremeImage.isEmpty())
                        weatherBackgrounds.put(weatherCodes.getCodeFromCondition(Weather.WeatherCondition.EXTREME),
                                ImageIO.read(fileLoader.load(extremeImage)));
                }

                backgroundsLoaded = true;
                return true;
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (SAXException sax) {
                sax.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        return false;
    }

    private static String getTextValue(Element doc, String tag) {
        NodeList nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            return nl.item(0).getFirstChild().getNodeValue();
        }
        return null;
    }

    public static Image getBkgImageFromCode(int code) {
        if (backgroundsLoaded) {
            return weatherBackgrounds.get(code);
        }
        return null;
    }
}
