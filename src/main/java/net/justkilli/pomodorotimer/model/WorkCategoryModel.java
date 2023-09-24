package net.justkilli.pomodorotimer.model;

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
}
