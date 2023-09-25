package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.components.ColorArrowUI;
import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.model.WorkCategory;

import java.awt.event.WindowAdapter;
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
    private JPanel pnlNorth, pnlSouth, pnlCenter;
    private JButton btnWork, btnShortBreak, btnLongBreak, btnStart;
    private JComboBox<WorkCategory> cbWorkCategories;
    private JLabel lblTimer;
    private JMenuBar menuBar;
    private JMenu mWindows;
    private JMenuItem miOpenWorkCategories;

    public MainWindow(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        this.colorDesign = colorDesign;
        this.fontDesign = fontDesign;
        this.borderDesign = borderDesign;
        init();
        build();
        updateDesign();
    }

    private void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout(10, 10));
    }

    public void build() {
        pnlNorth = buildNorthPanel();
        pnlSouth = buildSouthPanel();
        pnlCenter = buildCenterPanel();

        setJMenuBar(buildMenuBar());

        getContentPane().add(pnlNorth, BorderLayout.NORTH);
        getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        getContentPane().add(pnlCenter, BorderLayout.CENTER);
    }

    private JMenuBar buildMenuBar() {
        menuBar = new JMenuBar();

        mWindows = new JMenu("Windows");

        miOpenWorkCategories = new JMenuItem("WorkCategories");

        mWindows.add(miOpenWorkCategories);
        menuBar.add(mWindows);

        return menuBar;
    }

    private JPanel buildNorthPanel() {
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

    private JPanel buildSouthPanel() {
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

    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(CONTROL_PNL_SIZE);

        lblTimer = new JLabel();
        updateTimer("00:00:00");
        lblTimer.setHorizontalAlignment(JLabel.CENTER);
        lblTimer.setVerticalAlignment(JLabel.CENTER);

        panel.add(lblTimer);

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
        designMenuBar();
        designNorthPanel();
        designSouthPanel();
        designCenterPanel();
    }

    private void designContentPane() {
        getContentPane().setBackground(colorDesign.background());
    }

    private void designMenuBar() {
        menuBar.setBackground(colorDesign.background());
        menuBar.setBorder(borderDesign.components());

        mWindows.setForeground(colorDesign.text());
        mWindows.setBackground(colorDesign.compBackground());
        mWindows.setFont(fontDesign.text());
        mWindows.setBorder(null);

        miOpenWorkCategories.setBackground(colorDesign.compBackground());
        miOpenWorkCategories.setForeground(colorDesign.text());
        miOpenWorkCategories.setFont(fontDesign.text());
        miOpenWorkCategories.setBorder(borderDesign.components());
        /*menuBar = new JMenuBar();

        mWindows = new JMenu("Windows");

        miOpenWorkCategories = new JMenuItem("WorkCategories");

        mWindows.add(miOpenWorkCategories);
        menuBar.add(mWindows);*/

    }

    private void designNorthPanel() {
        designBasePanel(pnlNorth);

        designButton(btnWork);
        designButton(btnShortBreak);
        designButton(btnLongBreak);

    }

    private void designSouthPanel() {
        designBasePanel(pnlSouth);

        designComboBox(cbWorkCategories);
        designButton(btnStart);
    }

    private void designCenterPanel() {
        designBasePanel(pnlCenter);

        designLabel(lblTimer);
    }

    private void designButton(JButton button) {
        button.setBackground(colorDesign.compBackground());
        button.setForeground(colorDesign.buttonText());
        button.setFont(fontDesign.buttons());
        button.setBorder(borderDesign.buttons());
    }

    private void designComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(colorDesign.compBackground());
        comboBox.setForeground(colorDesign.text());
        comboBox.setFont(fontDesign.text());
        comboBox.setBorder(borderDesign.components());
        comboBox.setUI(new ColorArrowUI(colorDesign.compBackground(), colorDesign.compBackground(), colorDesign.text(), colorDesign.compBackground()));
    }

    private void designLabel(JLabel label) {
        label.setBackground(colorDesign.compBackground());
        label.setForeground(colorDesign.text());
        label.setFont(fontDesign.timer());
        label.setBorder(borderDesign.components());
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
    public void updateTimer(String newValue) {
        lblTimer.setText(newValue);
    }
    public void changeBtnWorkText(String text) {
        btnWork.setText(text);
    }
    public void addOpenWorkCategoriesActionListener(ActionListener listener) {
        miOpenWorkCategories.addActionListener(listener);
    }

    public ColorDesign getColorDesign() {
        return colorDesign;
    }

    public FontDesign getFontDesign() {
        return fontDesign;
    }

    public BorderDesign getBorderDesign() {
        return borderDesign;
    }
}
