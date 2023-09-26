package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The OpenWindowController class is responsible for managing the opening of the WorkCategoriesWindow.
 * It acts as the controller between the MainWindow and the WorkCategoriesWindow,
 * allowing the user to open the WorkCategoriesWindow when a specific action is performed
 * on the MainWindow.
 */
public class OpenWindowController {

    private MainWindow view;
    private WorkCategoriesWindow window;

    /**
     * OpenWindowController is a class that handles the opening of a new window
     * in the application.
     *
     * @param view The MainWindow object that represents the main application window.
     */
    public OpenWindowController(MainWindow view) {
        this.view = view;
        window = new WorkCategoriesWindow(view.getColorDesign(), view.getFontDesign(), view.getBorderDesign());
        view.addOpenWorkCategoriesActionListener(new OpenWorkCategoriesWindowActionListener());
    }

    public WorkCategoriesWindow getWindow() {
        return window;
    }

    /**
     * An implementation of the ActionListener interface that shows the
     * work categories window when an action event is received.
     */
    private class OpenWorkCategoriesWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            window.setVisible(true);
        }
    }

}
