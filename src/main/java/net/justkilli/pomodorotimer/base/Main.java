package net.justkilli.pomodorotimer.base;

import net.justkilli.killisessentials.config.handler.IConfigHandler;
import net.justkilli.killisessentials.config.handler.YAMLConfigHandler;
import net.justkilli.killisessentials.config.values.ConfigValue;
import net.justkilli.killisessentials.database.DatabaseCreator;
import net.justkilli.killisessentials.database.DatabaseTable;
import net.justkilli.pomodorotimer.gui.components.RoundBorder;
import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.database.DBAccessLayer;
import net.justkilli.pomodorotimer.model.database.DBHandler;

import java.awt.*;
import java.util.List;

public class Main {
    private static final ColorDesign colorDesign = new ColorDesign(
            new Color(200, 200, 200),
            new Color(231, 76, 60),
            new Color(255, 165, 0),
            new Color(243, 156, 18),
            new Color(255, 87, 51),
            new Color(52, 73, 94),
            new Color(39, 55, 70),
            new Color(255, 136, 0)
    );
    private static final FontDesign fontDesign = new FontDesign(
            new Font("Arial", Font.PLAIN, 15),
            new Font("Arial", Font.BOLD, 40),
            new Font("Arial", Font.PLAIN, 30),
            new Font("Arial", Font.PLAIN, 20),
            new Font("Arial", Font.PLAIN, 15),
            new Font("Arial", Font.BOLD, 20),
            new Font("Arial", Font.BOLD, 80)
    );
    private static final BorderDesign borderDesign = new BorderDesign(
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 2, 5)
    );
    private static final String CONFIG_FOLDER_NAME = "configs";
    private static final String MYSQL_CONFIG_NAME = "mysql.yml";
    private static DBAccessLayer sql;
    private static DBHandler dbHandler;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        IConfigHandler mysqlConfig = createDefaultMysqlConfig(String.format("%s/%s", CONFIG_FOLDER_NAME, MYSQL_CONFIG_NAME));
        sql = new DBAccessLayer(mysqlConfig);
        dbHandler = new DBHandler(sql);
        createDatabases();
        MainWindow window = new MainWindow(colorDesign, fontDesign, borderDesign);
        window.setWorkCategories(List.of(
                new WorkCategory(1, "GameDev Unreal Engine", "Game Development with Unreal Engine"),
                new WorkCategory(2, "GameDev Unity Engine", "Game Development with Unity Engine"),
                new WorkCategory(3, "Random Project", "Game Development with Unity Engine"),
                new WorkCategory(4, "Day Planer", "Game Development with Unity Engine"),
                new WorkCategory(5, "Website", "Game Development with Unity Engine")
        ));
        window.updateTimer("11:11");
        window.setVisible(true);
    }

    public static IConfigHandler createDefaultMysqlConfig(String path) {
        IConfigHandler mysqlConfigHandler = new YAMLConfigHandler(path);
        mysqlConfigHandler.addDefaultValue(ConfigValue.SQL_HOST);
        mysqlConfigHandler.addDefaultValue(ConfigValue.SQL_DATABASE);
        mysqlConfigHandler.addDefaultValue(ConfigValue.SQL_PORT);
        mysqlConfigHandler.addDefaultValue(ConfigValue.SQL_USERNAME);
        mysqlConfigHandler.addDefaultValue(ConfigValue.SQL_PASSWORD);
        mysqlConfigHandler.save();
        return mysqlConfigHandler;
    }

    public static void createDatabases() {

        List<DatabaseTable> databaseTables = List.of(
                createWorkCategoryTable()
        );

        DatabaseCreator creator = new DatabaseCreator(sql, databaseTables);
        creator.create();
    }

    private static DatabaseTable createWorkCategoryTable() {
        return new DatabaseTable.DatabaseTableBuilder("WorkCategory")
                .addField(new DatabaseTable.Column("WorkCategoryID", DatabaseTable.ColumnType.INTEGER, true, true, true, null))
                .addField(new DatabaseTable.Column("Name", DatabaseTable.ColumnType.TEXT, false, false, true, null))
                .addField(new DatabaseTable.Column("Description", DatabaseTable.ColumnType.LONG_TEXT, false, false, true, null))
                .build();
    }
}