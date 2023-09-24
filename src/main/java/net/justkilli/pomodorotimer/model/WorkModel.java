package net.justkilli.pomodorotimer.model;

import net.justkilli.pomodorotimer.model.database.DBAccessLayer;
import net.justkilli.pomodorotimer.model.database.DBHandler;

public class WorkModel {

    private final DBAccessLayer sql;
    private final DBHandler dbHandler;
    private boolean workStarted;

    public WorkModel(DBAccessLayer sql, DBHandler dbHandler) {
        this.sql = sql;
        this.dbHandler = dbHandler;
        workStarted = false;
    }

    public boolean startWork(WorkCategory category, WorkType type) {
        if(isWorking()) return false;
        updateWorkStarted();
        return dbHandler.startTime(category, type);
    }

    public boolean stopWork() {
        if(!isWorking()) return false;
        updateWorkStarted();
        return dbHandler.stopTime();
    }


    public boolean isWorking() {
        return workStarted;
    }

    private void updateWorkStarted() {
        workStarted = !workStarted;
    }

}
