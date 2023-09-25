package net.justkilli.pomodorotimer.model.database;

import net.justkilli.killisessentials.config.handler.IConfigHandler;

import java.sql.ResultSet;

public class DBAccessLayer extends net.justkilli.killisessentials.database.DBAccessLayer {
    public DBAccessLayer(IConfigHandler mysqlConfigHandler) {
        super(mysqlConfigHandler);
    }

    public ResultSet getWorkCategories() {
        String sqlQuery = "SELECT * FROM WorkCategory";
        return querySQLRequest(sqlQuery);
    }

    public boolean startTime(int categoryId, String type) {
        String sqlQuery = String.format("INSERT INTO Time (CategoryId, Type) VALUES (%d, '%s')", categoryId, type);
        return executeSQLRequest(sqlQuery);
    }

    public boolean stopTime(int timeId) {
        String sqlQuery = "UPDATE Time SET EndTimestamp=CURRENT_TIMESTAMP WHERE TimeID=" + timeId;
        return executeSQLRequest(sqlQuery);
    }

    public ResultSet getLastTimeId() {
        String sqlQuery = "SELECT TimeID FROM Time ORDER BY TimeID DESC LIMIT 1";
        return querySQLRequest(sqlQuery);
    }

    public ResultSet getLastWorkCategoryId() {
        String sqlQuery = "SELECT WorkCategoryID FROM WorkCategory ORDER BY WorkCategoryID DESC LIMIT 1";
        return querySQLRequest(sqlQuery);
    }

    public boolean insertWorkCategory(String name, String description) {
        String sqlQuery = String.format("INSERT INTO WorkCategory (Name, Description) VALUES ('%s', '%s')", name, description);
        return executeSQLRequest(sqlQuery);
    }

}
