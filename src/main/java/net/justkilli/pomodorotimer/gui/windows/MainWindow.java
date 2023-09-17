package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.model.WorkCategory;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private static final String TITLE = "Pomodoro Timer";
    private static final String BTN_WORK_TEXT = "Work";
    private static final String BTN_SHORT_BRAKE_TEXT = "Short Brake";
    private static final String BTN_LONG_BRAKE_TEXT = "Long Brake";
    private static final String BTN_START_TEXT = "Start";
    private static final Dimension BTN_SIZE = new Dimension(150, 50);
    private static final Dimension CB_SIZE = new Dimension(500, 50);
    private static final Dimension CONTROL_PNL_SIZE = new Dimension(0, 75);
    private ColorDesign colorDesign;
    private FontDesign fontDesign;
    private BorderDesign borderDesign;
    private JPanel pnlNorth, pnlSouth;
    private JButton btnWork, btnShortBreak, btnLongBreak, btnStart;
    private JComboBox<WorkCategory> cbWorkCategories;

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
        pnlSouth = buildSouthPanel();

        getContentPane().add(pnlNorth, BorderLayout.NORTH);
        getContentPane().add(pnlSouth, BorderLayout.SOUTH);
    }

    public JPanel buildNorthPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panel.setPreferredSize(CONTROL_PNL_SIZE);

        btnWork = new JButton(BTN_WORK_TEXT);
        btnWork.setPreferredSize(BTN_SIZE);

        btnShortBreak = new JButton(BTN_SHORT_BRAKE_TEXT);
        btnShortBreak.setPreferredSize(BTN_SIZE);

        btnLongBreak = new JButton(BTN_LONG_BRAKE_TEXT);
        btnLongBreak.setPreferredSize(BTN_SIZE);

        panel.add(btnWork);
        panel.add(btnShortBreak);
        panel.add(btnLongBreak);

        return panel;
    }

    public JPanel buildSouthPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setPreferredSize(CONTROL_PNL_SIZE);

        btnStart = new JButton(BTN_START_TEXT);
        btnStart.setPreferredSize(BTN_SIZE);

        cbWorkCategories = new JComboBox<>();
        cbWorkCategories.setPreferredSize(CB_SIZE);

        panel.add(cbWorkCategories);
        panel.add(btnStart);

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
        designBasePanel(pnlNorth);

        designButton(btnWork);
        designButton(btnShortBreak);
        designButton(btnLongBreak);

    }


        btnWork.setBackground(colorDesign.compBackground());
        btnWork.setForeground(colorDesign.buttonText());
        btnWork.setFont(fontDesign.buttons());
        btnWork.setBorder(borderDesign.buttons());

    private void designButton(JButton button) {
        button.setBackground(colorDesign.compBackground());
        button.setForeground(colorDesign.buttonText());
        button.setFont(fontDesign.buttons());
        button.setBorder(borderDesign.buttons());
    }


    private void designBasePanel(JPanel panel) {
        panel.setBackground(colorDesign.background());
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
    //NORTH PANEL START
    public void addBtnWorkActionListener(ActionListener listener) {
        btnWork.addActionListener(listener);
    }

    public void addBtnShortBreakActionListener(ActionListener listener) {
        btnShortBreak.addActionListener(listener);
    }

    public void addBtnLongBreakActionListener(ActionListener listener) {
        btnLongBreak.addActionListener(listener);
    }
    //NORTH PANEL END

    //SOUTH PANEL START
    public void setWorkCategories(List<WorkCategory> categories) {
        categories.forEach(cbWorkCategories::addItem);
    }

    public WorkCategory getSelectedWorkCategory() {
        return (WorkCategory) cbWorkCategories.getSelectedItem();
    }

    public void addBtnStartActionListener(ActionListener listener) {
        btnStart.addActionListener(listener);
    }

    //SOUTH PANEL END
}
