package watcher;

import java.util.ArrayList;

public class WatchDAO {

    private static ArrayList<Watcher> daoWatchers = new ArrayList<>();

    public static void addWatcher(Watcher watcher) {
        daoWatchers.add(watcher);
    }

    public static void notifyWatchers(Object updatedData) {
        for (Watcher watcher : daoWatchers) {
            watcher.updateData(updatedData);
        }
    }
}
