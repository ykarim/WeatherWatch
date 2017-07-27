package notification;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import util.FileLoad;
import view.ViewConstants;
import view.weatherPage.WeatherPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayApp {

    private static FileLoad fileLoad = new FileLoad();

    public static void initialize() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                addAppToTray();
            }
        });
    }

    private static void addAppToTray() {
        Toolkit.getDefaultToolkit();

        if (!SystemTray.isSupported()) {
            Platform.exit();
        } else {
            Platform.setImplicitExit(false);
        }

        final SystemTray tray = SystemTray.getSystemTray();

        final TrayIcon trayIcon = new TrayIcon(SwingFXUtils.fromFXImage(fileLoad.loadImageFile(ViewConstants.PROGRAM_ICON_LOCATION), null));
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(ViewConstants.PROGRAM_TITLE);

        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeatherPage.showStage();
            }
        });

        MenuItem titleItem = new MenuItem(ViewConstants.PROGRAM_TITLE);
        titleItem.setEnabled(false);
        titleItem.setFont(Font.decode(null).deriveFont(Font.BOLD));

        MenuItem openItem = new MenuItem("Open Application");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeatherPage.showStage();
            }
        });

        MenuItem exitItem = new MenuItem("Exit");
        final TrayIcon finalTrayIcon = trayIcon;
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(finalTrayIcon);
                Platform.exit();
            }
        });

        final PopupMenu trayMenu = new PopupMenu();
        trayMenu.add(titleItem);
        trayMenu.addSeparator();
        trayMenu.add(openItem);
        trayMenu.add(exitItem);
        trayIcon.setPopupMenu(trayMenu);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
