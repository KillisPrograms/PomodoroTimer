package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;
import net.justkilli.pomodorotimer.model.WorkModel;

import java.awt.event.WindowAdapter;
import java.util.List;

/**
 * The CloseWindowController class manages the closing behavior of the MainWindow and WorkCategoriesWindow.
 * It implements WindowAdapter to listen for the window closing events of the two windows.
 *
 */
public class CloseWindowController {

    private final MainWindow mainWindow;
    private final WorkCategoriesWindow workCategoriesWindow;
    private final WorkModel workModel;
    private final WorkCategoryModel workCategoryModel;

    /**
     * Creates a new CloseWindowController object with the given main window, work categories window, work model, and work category model.
     *
     * @param mainWindow The main window object.
     * @param workCategoriesWindow The work categories window object.
     * @param workModel The work model object.
     * @param workCategoryModel The work category model object.
     */
    public CloseWindowController(MainWindow mainWindow, WorkCategoriesWindow workCategoriesWindow, WorkModel workModel, WorkCategoryModel workCategoryModel) {
        this.mainWindow = mainWindow;
        this.workCategoriesWindow = workCategoriesWindow;
        this.workModel = workModel;
        this.workCategoryModel = workCategoryModel;

        this.mainWindow.addWindowListener(new MainWindowCloseAdapter());
        this.workCategoriesWindow.addWindowListener(new WorkCategoriesWindowCloseAdapter());
    }

    /**
     * This class represents a WindowAdapter class that listens for the closing event of a main window
     * and performs certain actions when the window is being closed.
     */
    private class MainWindowCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            workModel.stopWork();
            System.exit(0);
        }
    }
    /**
     * An adapter class that listens for the close event of a window and performs specific actions.
     * This class is used by the WorkCategoriesWindow class to handle the closing of the window.
     */
    private class WorkCategoriesWindowCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            workCategoriesWindow.dispose();
            List<WorkCategory> workCategories = workCategoryModel.getAllWorkCategories();
            mainWindow.setWorkCategories(workCategories);
        }
    }
}
