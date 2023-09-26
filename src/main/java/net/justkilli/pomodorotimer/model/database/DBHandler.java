package net.justkilli.pomodorotimer.model.database;

import net.justkilli.killisessentials.logging.LogLevel;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.TimerType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DBHandler class is responsible for handling database operations related to work categories and time entries.
 */
public class DBHandler extends net.justkilli.killisessentials.database.DBHandler {

    private final DBAccessLayer sql;

    /**
     * Creates a new instance of DBHandler with the given DBAccessLayer object.
     *
     * @param sql The DBAccessLayer object to be used for database access.
     */
    public DBHandler(DBAccessLayer sql) {
        super(sql);
        this.sql = sql;
    }

    /**
     * Retrieves a list of all work categories from the database.
     *
     * @return A list of WorkCategory objects representing the work categories.
     *         If there are no work categories in the database, an empty list is returned.
     */
    public List<WorkCategory> getWorkCategories() {
        List<WorkCategory> result = new ArrayList<>();
        ResultSet rs = sql.getWorkCategories();
        if(isResultSetEmpty(rs).isEmpty()) return result;
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

    /**
     * Starts a timer for the specified work category and work type.
     *
     * @param category The WorkCategory object representing the work category.
     * @param type The WorkType object representing the work type.
     * @return true if the timer was successfully started, false otherwise.
     */
    public boolean startTime(WorkCategory category, TimerType type) {
        return sql.startTime(category.id(), type.toString());
    }

    /**
     * Stops the timer for the last time entry.
     *
     * @return true if the timer was successfully stopped, false otherwise.
     */
    public boolean stopTime() {
        int lastTimeEntryId = getLastTimeId();
        return sql.stopTime(lastTimeEntryId);
    }

    /**
     * Retrieves the ID of the last time entry in the database.
     *
     * @return the ID of the last time entry.
     */
    public int getLastTimeId() {
        ResultSet rs = sql.getLastTimeId();
        if(isResultSetEmpty(rs).isEmpty()) return 1;
        try {
            return rs.getInt("TimeID");
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not get last TimeID", ex);
        } finally {
            closeResultSet(rs);
        }
        return 1;
    }

    /**
     * Retrieves the ID of the last work category entry in the database.
     *
     * @return the ID of the last work category entry.
     */
    public int getLastWorkCategoryId() {
        ResultSet rs = sql.getLastWorkCategoryId();
        if(isResultSetEmpty(rs).isEmpty()) return 1;
        try {
            return rs.getInt("WorkCategoryID");
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not get last WorkCategoryID", ex);
        } finally {
            closeResultSet(rs);
        }
        return 1;
    }

    /**
     * Inserts a new work category entry into the database.
     *
     * @param workCategory the work category to be inserted.
     * @return true if the insertion was successful, false otherwise.
     */
    public boolean insertWorkCategory(WorkCategory workCategory) {
        return sql.insertWorkCategory(workCategory.name(), workCategory.description());
    }

    /**
     * Retrieves a WorkCategory object from a ResultSet. The ResultSet should contain the following columns:
     * - WorkCategoryID: the ID of the work category.
     * - Name: the name of the work category.
     * - Description: the description of the work category.
     *
     * @param rs the ResultSet containing the work category information.
     * @return a WorkCategory object created from the ResultSet.
     * @throws SQLException if there is an error accessing the ResultSet.
     */
    private WorkCategory getWorkCategoryFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("WorkCategoryID");
        String name = rs.getString("Name");
        String description = rs.getString("Description");
        return new WorkCategory(id, name, description);
    }
}
