package net.justkilli.pomodorotimer.gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private static final String TITLE = "Pomodoro Timer";
    private static final String BTN_WORK_TEXT = "Work";
    private static final String BTN_SHORT_BRAKE_TEXT = "Short Brake";
    private static final String BTN_LONG_BRAKE_TEXT = "Long Brake";
    private static final Dimension NORTH_BTN_SIZE = new Dimension(150, 50);
    private static final Dimension NORTH_PNL_SIZE = new Dimension(0, 75);
    private JPanel pnlNorth;
    private JButton btnWork, btnShortBreak, btnLongBreak;

    public MainWindow() {
        init();
        build();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout(10, 10));
    }

    public void build() {
        pnlNorth = buildNorthPanel();

        getContentPane().add(pnlNorth, BorderLayout.NORTH);
    }

    public JPanel buildNorthPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panel.setPreferredSize(NORTH_PNL_SIZE);

        btnWork = new JButton(BTN_WORK_TEXT);
        btnWork.setPreferredSize(NORTH_BTN_SIZE);

        btnShortBreak = new JButton(BTN_SHORT_BRAKE_TEXT);
        btnShortBreak.setPreferredSize(NORTH_BTN_SIZE);

        btnLongBreak = new JButton(BTN_LONG_BRAKE_TEXT);
        btnLongBreak.setPreferredSize(NORTH_BTN_SIZE);

        panel.add(btnWork);
        panel.add(btnShortBreak);
        panel.add(btnLongBreak);

        return panel;
    }
    public void addBtnWorkActionListener(ActionListener listener) {
        btnWork.addActionListener(listener);
    }

    public void addBtnShortBreakActionListener(ActionListener listener) {
        btnShortBreak.addActionListener(listener);
    }

    public void addBtnLongBreakActionListener(ActionListener listener) {
        btnLongBreak.addActionListener(listener);
    }

}
