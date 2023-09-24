package net.justkilli.pomodorotimer.model.database;

import net.justkilli.killisessentials.logging.ILogger;
import net.justkilli.killisessentials.logging.LogLevel;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends net.justkilli.killisessentials.database.DBHandler {

    private final DBAccessLayer sql;

    public DBHandler(DBAccessLayer sql) {
        super(sql);
        this.sql = sql;
    }

    public List<WorkCategory> getWorkCategories() {
        List<WorkCategory> result = new ArrayList<>();
        ResultSet rs = sql.getWorkCategories();
        if(resultSetIsEmpty(rs).isEmpty()) return result;
        try {
            do {
                result.add(getWorkCategoryFromResultSet(rs));
            } while(rs.next());
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not load all Work Categories", ex);
        } finally {
            closeResultSet(rs);
        }
        return result;
    }

    public boolean startTime(WorkCategory category, WorkType type) {
        return sql.startTime(category.id(), type.toString());
    }

    public boolean stopTime() {
        int lastTimeEntryId = getLastTimeId();
        return sql.stopTime(lastTimeEntryId);
    }

    public int getLastTimeId() {
        ResultSet rs = sql.getLastTimeId();
        if(resultSetIsEmpty(rs).isEmpty()) return 1;
        try {
            return rs.getInt("TimeID");
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not get last TimeID", ex);
        } finally {
            closeResultSet(rs);
        }
        return 1;
    }

    private WorkCategory getWorkCategoryFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("WorkCategoryID");
        String name = rs.getString("Name");
        String description = rs.getString("Description");
        return new WorkCategory(id, name, description);
    }

}
