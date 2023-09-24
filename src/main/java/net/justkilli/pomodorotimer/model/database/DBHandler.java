package net.justkilli.pomodorotimer.model.database;

public class DBHandler extends net.justkilli.killisessentials.database.DBHandler {

    private final DBAccessLayer sql;

    public DBHandler(DBAccessLayer sql) {
        super(sql);
        this.sql = sql;
    }
}
