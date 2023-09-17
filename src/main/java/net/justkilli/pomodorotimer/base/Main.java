package net.justkilli.pomodorotimer.base;

import net.justkilli.killisessentials.config.handler.IConfigHandler;
import net.justkilli.killisessentials.config.handler.YAMLConfigHandler;
import net.justkilli.killisessentials.config.values.ConfigValue;
import net.justkilli.pomodorotimer.gui.components.RoundBorder;
import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.gui.windows.MainWindow;

import java.awt.*;

public class Main {
    private static final ColorDesign colorDesign = new ColorDesign(
            new Color(44, 62, 80),
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
            new Font("Arial", Font.BOLD, 20)
    );
    private static final BorderDesign borderDesign = new BorderDesign(
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 5, 10),
            new RoundBorder(colorDesign.compBackground(), 5, 10)
    );
    private static final String CONFIG_FOLDER_NAME = "configs";
    private static final String MYSQL_CONFIG_NAME = "mysql.yml";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        createDefaultMysqlConfig(String.format("%s/%s", CONFIG_FOLDER_NAME, MYSQL_CONFIG_NAME));
        MainWindow window = new MainWindow(colorDesign, fontDesign, borderDesign);
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