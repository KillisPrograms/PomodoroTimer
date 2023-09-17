package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;

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
    private ColorDesign colorDesign;
    private FontDesign fontDesign;
    private BorderDesign borderDesign;
    private JPanel pnlNorth;
    private JButton btnWork, btnShortBreak, btnLongBreak;

    public MainWindow(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        this.colorDesign = colorDesign;
        this.fontDesign = fontDesign;
        this.borderDesign = borderDesign;
        init();
        build();
        updateDesign();
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

    public void updateDesign(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        setColorDesign(colorDesign);
        setFontDesign(fontDesign);
        setBorderDesign(borderDesign);
        updateDesign();
    }

    public void updateDesign() {
        designContentPane();
        designNorthPanel();
    }

    private void designContentPane() {
        getContentPane().setBackground(colorDesign.background());
    }

    private void designNorthPanel() {
        pnlNorth.setBackground(colorDesign.background());

        btnWork.setBackground(colorDesign.compBackground());
        btnWork.setForeground(colorDesign.buttonText());
        btnWork.setFont(fontDesign.buttons());
        btnWork.setBorder(borderDesign.buttons());

        btnShortBreak.setBackground(colorDesign.compBackground());
        btnShortBreak.setForeground(colorDesign.buttonText());
        btnShortBreak.setFont(fontDesign.buttons());
        btnShortBreak.setBorder(borderDesign.buttons());

        btnLongBreak.setBackground(colorDesign.compBackground());
        btnLongBreak.setForeground(colorDesign.buttonText());
        btnLongBreak.setFont(fontDesign.buttons());
        btnLongBreak.setBorder(borderDesign.buttons());
    }

    private void setColorDesign(ColorDesign colorDesign) {
        this.colorDesign = colorDesign;
    }

    private void setFontDesign(FontDesign fontDesign) {
        this.fontDesign = fontDesign;
    }

    private void setBorderDesign(BorderDesign borderDesign) {
        this.borderDesign = borderDesign;
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
