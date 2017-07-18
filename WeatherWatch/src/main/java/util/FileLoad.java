package util;

import java.io.InputStream;

public class FileLoad {

    public InputStream load(String fileName) {
        return (FileLoad.class.getClassLoader().getResourceAsStream(fileName));
    }
}
