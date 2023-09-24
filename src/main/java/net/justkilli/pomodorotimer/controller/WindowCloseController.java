package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.model.WorkModel;

import java.awt.event.WindowAdapter;

public class WindowCloseController {

    private final MainWindow view;
    private final WorkModel model;

    public WindowCloseController(MainWindow view, WorkModel model) {
        this.view = view;
        this.model = model;

        view.addWindowListener(new WindowCloseAdapter());
    }

    private class WindowCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            model.stopWork();
            System.exit(0);
        }
    }
}
