package net.justkilli.pomodorotimer.model;

import net.justkilli.pomodorotimer.model.database.DBAccessLayer;
import net.justkilli.pomodorotimer.model.database.DBHandler;

import java.util.List;

public class WorkCategoryModel {

    private final DBHandler dbHandler;

    public WorkCategoryModel(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public List<WorkCategory> getAllWorkCategories() {
        return dbHandler.getWorkCategories();
    }
    public boolean addWorkCategory(WorkCategory workCategory) {
        return dbHandler.insertWorkCategory(workCategory);
    }

    public int getNextWorkCategoryId() {
        return dbHandler.getLastWorkCategoryId() + 1;
    }
}
