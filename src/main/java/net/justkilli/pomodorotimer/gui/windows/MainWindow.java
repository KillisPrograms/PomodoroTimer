package net.justkilli.pomodorotimer.gui.windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final String TITLE = "Pomodoro Timer";

    public MainWindow() {
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
    }

}
