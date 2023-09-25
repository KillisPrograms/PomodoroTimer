package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;

import java.util.Arrays;
import java.util.List;

public class WorkCategoriesController {

    private MainWindow view;
    private WorkCategoriesWindow viewWorkCategories;
    private WorkCategoryModel model;

    public WorkCategoriesController(MainWindow view, WorkCategoryModel model) {
        this.view = view;
        this.model = model;

        setMainWindowWorkCategories();
    }

    public WorkCategoriesController(WorkCategoriesWindow viewWorkCategories, WorkCategoryModel model) {
        this.viewWorkCategories = viewWorkCategories;
        this.model = model;

        setWorkCategoriesWindowWorkCategories();
    }

    private void setMainWindowWorkCategories() {
        view.setWorkCategories(loadAllWorkCategories());
    }
    private void setWorkCategoriesWindowWorkCategories() {
        List<WorkCategory> workCategories = loadAllWorkCategories();
        WorkCategory[] workCategoriesArray = listToArray(workCategories);
        viewWorkCategories.setWorkCategories(workCategoriesArray);
    }

    private WorkCategory[] listToArray(List<WorkCategory> workCategories) {
        WorkCategory[] result = new WorkCategory[workCategories.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = workCategories.get(i);
        }
        return result;
    }

    private List<WorkCategory> loadAllWorkCategories() {
       return model.getAllWorkCategories();
    }
}
