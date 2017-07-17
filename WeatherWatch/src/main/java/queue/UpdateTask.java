package queue;

import java.net.URL;

public interface UpdateTask {

    void setRequest(URL url);

    void refreshData();
}
