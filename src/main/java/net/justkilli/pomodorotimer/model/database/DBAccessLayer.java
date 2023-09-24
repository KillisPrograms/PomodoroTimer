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

}
