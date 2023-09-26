package net.justkilli.pomodorotimer.model;

import net.justkilli.pomodorotimer.model.database.DBHandler;

import java.util.List;

/**
 * The WorkCategoryModel class represents a model for the work categories in the application.
 * It provides methods to interact with the work category data in the database.
 */
public class WorkCategoryModel {

    private final DBHandler dbHandler;

    /**
     * Constructs a new WorkCategoryModel instance with the specified DBHandler.
     *
     * @param dbHandler the DBHandler object used for database operations
     */
    public WorkCategoryModel(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    /**
     * Retrieves all work categories from the database.
     *
     * @return a List of WorkCategory objects representing all work categories
     */
    public List<WorkCategory> getAllWorkCategories() {
        return dbHandler.getWorkCategories();
    }
    /**
     * Inserts a new work category into the database.
     *
     * @param workCategory the WorkCategory object to be added
     * @return true if the work category was successfully added, false otherwise
     */
    public boolean addWorkCategory(WorkCategory workCategory) {
        return dbHandler.insertWorkCategory(workCategory);
    }

    /**
     * Returns the next available work category ID.
     *
     * @return the next available work category ID
     */
    public int getNextWorkCategoryId() {
        return dbHandler.getLastWorkCategoryId() + 1;
    }
}
