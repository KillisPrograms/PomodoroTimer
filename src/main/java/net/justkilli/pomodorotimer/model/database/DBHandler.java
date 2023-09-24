package net.justkilli.pomodorotimer.model.database;

import net.justkilli.killisessentials.logging.ILogger;
import net.justkilli.killisessentials.logging.LogLevel;
import net.justkilli.pomodorotimer.model.WorkCategory;

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

    private WorkCategory getWorkCategoryFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("WorkCategoryID");
        String name = rs.getString("Name");
        String description = rs.getString("Description");
        return new WorkCategory(id, name, description);
    }
}
