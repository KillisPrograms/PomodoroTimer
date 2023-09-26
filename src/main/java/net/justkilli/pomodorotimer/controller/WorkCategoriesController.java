package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;

import java.util.List;

/**
 * The WorkCategoriesController class is responsible for managing the interaction
 * between the MainWindow, WorkCategoriesWindow, and WorkCategoryModel.
 */
public class WorkCategoriesController {

    private final MainWindow view;
    private final WorkCategoriesWindow viewWorkCategories;
    private final WorkCategoryModel model;

    /**
     * Constructs a new instance of WorkCategoriesController.
     *
     * @param view               the main window view
     * @param viewWorkCategories the work categories window view
     * @param model              the work category model
     */
    public WorkCategoriesController(MainWindow view, WorkCategoriesWindow viewWorkCategories, WorkCategoryModel model) {
        this.view = view;
        this.viewWorkCategories = viewWorkCategories;
        this.model = model;

        setWorkCategoriesWindowWorkCategories();
        setMainWindowWorkCategories();
    }

    /**
     * Sets the work categories for the main window view.
     */
    private void setMainWindowWorkCategories() {
        view.setWorkCategories(loadAllWorkCategories());
    }
    /**
     * Sets the work categories for the work categories window view.
     *
     * This method retrieves all work categories from the database, and sets them to the work
     * categories view in the work categories window. The work categories are retrieved using
     * the loadAllWorkCategories() method.
     *
     * @see #loadAllWorkCategories()
     */
    private void setWorkCategoriesWindowWorkCategories() {
        List<WorkCategory> workCategories = loadAllWorkCategories();
        viewWorkCategories.setWorkCategories(workCategories.toArray(new WorkCategory[0]));
    }
    /**
     * Loads all work categories from the database.
     *
     * This method retrieves all work categories from the database by calling the getAllWorkCategories() method
     * from the model. The retrieved work categories are then returned as a List of WorkCategory objects.
     *
     * @return A List of WorkCategory objects representing all the work categories in the database.
     */
    private List<WorkCategory> loadAllWorkCategories() {
       return model.getAllWorkCategories();
    }
}
