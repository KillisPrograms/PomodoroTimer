package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenWindowController {

    private MainWindow view;
    private WorkCategoriesWindow window;

    public OpenWindowController(MainWindow view) {
        this.view = view;
        window = new WorkCategoriesWindow(view.getColorDesign(), view.getFontDesign(), view.getBorderDesign());
        view.addOpenWorkCategoriesActionListener(new OpenWorkCategoriesWindowActionListener());
    }

    public WorkCategoriesWindow getWindow() {
        return window;
    }

    private class OpenWorkCategoriesWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            window.setVisible(true);
        }
    }

}
