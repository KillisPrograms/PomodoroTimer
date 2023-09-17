package net.justkilli.pomodorotimer.base;

import net.justkilli.killisessentials.config.handler.IConfigHandler;
import net.justkilli.killisessentials.config.handler.YAMLConfigHandler;
import net.justkilli.killisessentials.config.values.ConfigValue;
import net.justkilli.pomodorotimer.gui.windows.MainWindow;

public class Main {

    private static final String CONFIG_FOLDER_NAME = "configs";
    private static final String MYSQL_CONFIG_NAME = "mysql.yml";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        createDefaultMysqlConfig(String.format("%s/%s", CONFIG_FOLDER_NAME, MYSQL_CONFIG_NAME));
        MainWindow window = new MainWindow();
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

}