package net.justkilli.pomodorotimer.base;

import net.justkilli.killisessentials.config.handler.IConfigHandler;
import net.justkilli.killisessentials.config.handler.YAMLConfigHandler;
import net.justkilli.killisessentials.config.values.ConfigValue;
import net.justkilli.killisessentials.database.DatabaseCreator;
import net.justkilli.killisessentials.database.DatabaseTable;
import net.justkilli.pomodorotimer.controller.*;
import net.justkilli.pomodorotimer.gui.components.RoundBorder;
import net.justkilli.pomodorotimer.gui.design.*;
import net.justkilli.pomodorotimer.gui.windows.*;
import net.justkilli.pomodorotimer.model.*;
import net.justkilli.pomodorotimer.model.database.*;

import java.awt.*;
import java.util.List;

public class Main {
    private static final ColorDesign colorDesign = createColorDesign();
    private static final FontDesign fontDesign = createFontDesign();
    private static final BorderDesign borderDesign = createBorderDesign();
    private static final String CONFIG_FOLDER_NAME = "configs";
    private static final String MYSQL_CONFIG_NAME = "mysql.yml";
    private static DBAccessLayer sql;
    private static DBHandler dbHandler;
    private static WorkCategoryModel workCategoryModel;
    private static WorkModel workModel;


    public static void main(String[] args) {
        System.out.println("Hello world!");

        initializeDBLayer();
        MainWindow mainWindow = createMainWindowComponents();
        initializeControllers(mainWindow);

        mainWindow.setVisible(true);
    }

    /**
     * Initializes the database layer by creating necessary configuration, database access layer, and database handler objects.
     * The method performs the following steps:
     * <p></p>
     * <p>1. Creates a default MySQL configuration object using the specified configuration folder and MySQL configuration file name.</p>
     * <p>2. Initializes the database access layer using the created MySQL configuration.</p>
     * <p>3. Initializes the database handler using the initialized database access layer.</p>
     * <p>4. Creates necessary databases.</p>
     * <p></p>
     * Note: This method is static and private, meaning it can only be accessed internally within the class.
     * It should be called before performing any database operations to ensure that the database layer is properly initialized.
     */
    private static void initializeDBLayer() {
        IConfigHandler mysqlConfig = createDefaultMysqlConfig(String.format("%s/%s", CONFIG_FOLDER_NAME, MYSQL_CONFIG_NAME));
        sql = new DBAccessLayer(mysqlConfig);
        dbHandler = new DBHandler(sql);
        createDatabases();
    }

    /**
     * Creates the main window components.
     *
     * @return the MainWindow object representing the main window components
     */
    private static MainWindow createMainWindowComponents() {
        MainWindow mainWindow = new MainWindow(colorDesign, fontDesign, borderDesign);
        workCategoryModel = new WorkCategoryModel(dbHandler);
        workModel = new WorkModel(sql, dbHandler);

        return mainWindow;
    }

    /**
     * Initializes the controllers for the main window.
     *
     * @param mainWindow the main window instance
     */
    private static void initializeControllers(MainWindow mainWindow) {
        OpenWindowController openWindowController = new OpenWindowController(mainWindow);
        WorkCategoriesWindow workCategoriesWindow = openWindowController.getWindow();
        new WorkCategoriesController(mainWindow, workCategoriesWindow, workCategoryModel);
        new WorkController(mainWindow, workModel);
        new CloseWindowController(mainWindow, workCategoriesWindow, workModel, workCategoryModel);
        new SubmitWorkCategoryController(workCategoriesWindow, workCategoryModel);
    }

    /**
     * Creates a ColorDesign object with predefined colors.
     *
     * @return the ColorDesign object with predefined colors
     */
    private static ColorDesign createColorDesign() {
        return new ColorDesign(
            new Color(200, 200, 200),
            new Color(231, 76, 60),
            new Color(255, 165, 0),
            new Color(243, 156, 18),
            new Color(255, 87, 51),
            new Color(52, 73, 94),
            new Color(39, 55, 70),
            new Color(255, 136, 0)
        );
    }

    /**
     * Creates a FontDesign object.
     *
     * @return a FontDesign object containing multiple Font instances with different styles and sizes.
     */
    private static FontDesign createFontDesign() {
        return new FontDesign(
            new Font("Arial", Font.PLAIN, 15),
            new Font("Arial", Font.BOLD, 40),
            new Font("Arial", Font.PLAIN, 30),
            new Font("Arial", Font.PLAIN, 20),
            new Font("Arial", Font.PLAIN, 15),
            new Font("Arial", Font.BOLD, 20),
            new Font("Arial", Font.BOLD, 80)
        );
    }

    /**
     * Creates a custom border design for a component.
     *
     * @return The BorderDesign object representing the custom border design.
     */
    private static BorderDesign createBorderDesign() {
        return new BorderDesign(
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 2, 5)
        );
    }

    /**
     * Creates a default MySQL configuration file.
     * The method initializes a new instance of YAMLConfigHandler
     * with the given path and adds default values for SQL_HOST,
     * SQL_DATABASE, SQL_PORT, SQL_USERNAME, and SQL_PASSWORD
     * using the addDefaultValue method. Finally, the configuration
     * is saved to the file system using the save method.
     *
     * @param path the path to the configuration file
     * @return the created IConfigHandler instance for the MySQL configuration
     */
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

    /**
     * Creates the necessary databases for the application.
     */
    public static void createDatabases() {

        List<DatabaseTable> databaseTables = List.of(
                createWorkCategoryTable(),
                createTimeTable()
        );

        DatabaseCreator creator = new DatabaseCreator(sql, databaseTables);
        creator.create();
    }

    /**
     * Creates the "WorkCategory" table in the database.
     *
     * @return the created "WorkCategory" table as a DatabaseTable object
     */
    private static DatabaseTable createWorkCategoryTable() {
        return new DatabaseTable.DatabaseTableBuilder("WorkCategory")
                .addField(new DatabaseTable.Column("WorkCategoryID", DatabaseTable.ColumnType.INTEGER, true, true, true, null))
                .addField(new DatabaseTable.Column("Name", DatabaseTable.ColumnType.TEXT, false, false, true, null))
                .addField(new DatabaseTable.Column("Description", DatabaseTable.ColumnType.LONG_TEXT, false, false, true, null))
                .build();
    }
    /**
     * Creates the "Time" table in the database.
     *
     * @return the created "Time" table as a DatabaseTable object
     */
    private static DatabaseTable createTimeTable() {
        return new DatabaseTable.DatabaseTableBuilder("Time")
                .addField(new DatabaseTable.Column("TimeID", DatabaseTable.ColumnType.INTEGER, true, true, true, null))
                .addField(new DatabaseTable.Column("CategoryId", DatabaseTable.ColumnType.INTEGER, false, false, true, null))
                .addField(new DatabaseTable.Column("Type", DatabaseTable.ColumnType.TEXT, false, false, true, null))
                .addField(new DatabaseTable.Column("StartTimestamp", DatabaseTable.ColumnType.TIMESTAMP, false, false, true, "CURRENT_TIMESTAMP"))
                .addField(new DatabaseTable.Column("EndTimestamp", DatabaseTable.ColumnType.TIMESTAMP, false, false, false, null))
                .build();
    }
}