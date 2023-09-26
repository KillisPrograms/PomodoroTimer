package net.justkilli.pomodorotimer.model.database;

import net.justkilli.killisessentials.config.handler.IConfigHandler;

import java.sql.ResultSet;

/**
 * This is the DBAccessLayer class, which extends the net.justkilli.killisessentials.database.DBAccessLayer class.
 * It provides methods to access the database and perform various operations.
 */
public class DBAccessLayer extends net.justkilli.killisessentials.database.DBAccessLayer {
    /**
     * Initializes a new instance of the DBAccessLayer class.
     *
     * @param mysqlConfigHandler An instance of the IConfigHandler interface that provides the configuration information for the MySQL database.
     */
    public DBAccessLayer(IConfigHandler mysqlConfigHandler) {
        super(mysqlConfigHandler);
    }

    /**
     * Retrieves the work categories from the database.
     *
     * @return A ResultSet object containing the work categories retrieved from the database.
     */
    public ResultSet getWorkCategories() {
        String sqlQuery = "SELECT * FROM WorkCategory";
        return querySQLRequest(sqlQuery);
    }

    /**
     * Starts the timer for a specific work category and type in the database.
     *
     * @param categoryId The ID of the work category.
     * @param type       The type of the timer.
     * @return true if the timer was successfully started, false otherwise.
     */
    public boolean startTime(int categoryId, String type) {
        String sqlQuery = String.format("INSERT INTO Time (CategoryId, Type) VALUES (%d, '%s')", categoryId, type);
        return executeSQLRequest(sqlQuery);
    }

    /**
     * Stops the timer for a specific time entry in the database.
     *
     * @param timeId The ID of the time entry.
     * @return true if the timer was successfully stopped, false otherwise.
     */
    public boolean stopTime(int timeId) {
        String sqlQuery = "UPDATE Time SET EndTimestamp=CURRENT_TIMESTAMP WHERE TimeID=" + timeId;
        return executeSQLRequest(sqlQuery);
    }

    /**
     * Retrieves the last inserted time ID from the database.
     *
     * @return A ResultSet object containing the last time ID. Returns null if there is no time entry in the database.
     */
    public ResultSet getLastTimeId() {
        String sqlQuery = "SELECT TimeID FROM Time ORDER BY TimeID DESC LIMIT 1";
        return querySQLRequest(sqlQuery);
    }

    /**
     * Retrieves the last inserted work category ID from the database.
     *
     * @return A ResultSet object containing the last work category ID. Returns null if there is no work category entry in the database.
     */
    public ResultSet getLastWorkCategoryId() {
        String sqlQuery = "SELECT WorkCategoryID FROM WorkCategory ORDER BY WorkCategoryID DESC LIMIT 1";
        return querySQLRequest(sqlQuery);
    }

    /**
     * Inserts a new work category entry into the database.
     *
     * @param name        The name of the work category.
     * @param description The description of the work category.
     * @return True if the work category is successfully inserted into the database, false otherwise.
     */
    public boolean insertWorkCategory(String name, String description) {
        String sqlQuery = String.format("INSERT INTO WorkCategory (Name, Description) VALUES ('%s', '%s')", name, description);
        return executeSQLRequest(sqlQuery);
    }
}
