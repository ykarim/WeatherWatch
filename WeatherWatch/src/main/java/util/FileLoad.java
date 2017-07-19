package util;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class FileLoad {

    private static final String ignoreDTDTag = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    public InputStream load(String fileName) {
        return (FileLoad.class.getClassLoader().getResourceAsStream(fileName));
    }

    public Element loadXml(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(ignoreDTDTag, false);
            return dbf.newDocumentBuilder().parse(load(fileName)).getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
