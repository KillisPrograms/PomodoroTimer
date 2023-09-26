package net.justkilli.pomodorotimer.model;

import net.justkilli.pomodorotimer.model.database.DBAccessLayer;
import net.justkilli.pomodorotimer.model.database.DBHandler;

/**
 * The WorkModel class represents the model of the work being performed.
 *
 * It maintains the state of the work being started or stopped, and communicates
 * with the database handler to record the start and stop times for the work.
 *
 * This class requires a DBAccessLayer and a DBHandler to be passed in during
 * construction.
 */
public class WorkModel {

    private final DBAccessLayer sql;
    private final DBHandler dbHandler;
    private boolean workStarted;

    /**
     * Constructs a new instance of the WorkModel class.
     *
     * @param sql       the DBAccessLayer object used for accessing the database
     * @param dbHandler the DBHandler object used for handling the database operations
     */
    public WorkModel(DBAccessLayer sql, DBHandler dbHandler) {
        this.sql = sql;
        this.dbHandler = dbHandler;
        workStarted = false;
    }

    /**
     * Starts the work with the specified category and type.
     *
     * @param category the category of the work
     * @param type     the type of the work
     * @return true if the work was started successfully, false otherwise
     */
    public boolean startWork(WorkCategory category, TimerType type) {
        if(isWorking()) return false;
        updateWorkStarted();
        return dbHandler.startTime(category, type);
    }

    /**
     * Stops the current work.
     *
     * @return true if the work was stopped successfully, false otherwise
     */
    public boolean stopWork() {
        if(!isWorking()) return false;
        updateWorkStarted();
        return dbHandler.stopTime();
    }


    /**
     * Checks if the work is currently in progress.
     *
     * @return true if the work is in progress, false otherwise
     */
    public boolean isWorking() {
        return workStarted;
    }

    /**
     * Updates the status of the work to indicate whether it has started or not.
     * If the work was started, this method will set the workStarted variable to false,
     * indicating that the work is not currently in progress. If the work was not
     * started, this method will set the workStarted variable to true, indicating that
     * the work is currently in progress.
     */
    private void updateWorkStarted() {
        workStarted = !workStarted;
    }
}
