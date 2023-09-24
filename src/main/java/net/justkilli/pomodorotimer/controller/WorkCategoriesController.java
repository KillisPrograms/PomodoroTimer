package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;

import java.util.List;

public class WorkCategoriesController {

    private MainWindow view;
    private WorkCategoryModel model;

    public WorkCategoriesController(MainWindow view, WorkCategoryModel model) {
        this.view = view;
        this.model = model;

        loadAllWorkCategories();
    }

    private void loadAllWorkCategories() {
        List<WorkCategory> categories = model.getAllWorkCategories();
        view.setWorkCategories(categories);
    }

}
