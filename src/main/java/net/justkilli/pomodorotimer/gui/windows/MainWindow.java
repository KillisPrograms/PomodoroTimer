package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.model.WorkCategory;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the main window of the Pomodoro Timer application.
 */
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

    /**
     * Creates a new instance of MainWindow with the specified color design, font design, and border design.
     *
     * @param colorDesign The color design to be used by the main window.
     * @param fontDesign The font design to be used by the main window.
     * @param borderDesign The border design to be used by the main window.
     */
    public MainWindow(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        init();
        build();
        updateDesign(colorDesign, fontDesign, borderDesign);
    }

    /**
     * Initializes the frame by setting the default close operation,
     * the window size, the title, and the layout of the content pane.
     */
    private void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout(10, 10));
    }

    /**
     * Builds the frame by creating and initializing the panels for the north, south,
     * and center sections, setting the menu bar, and adding the panels to the content pane.
     */
    public void build() {
        pnlNorth = buildNorthPanel();
        pnlSouth = buildSouthPanel();
        pnlCenter = buildCenterPanel();

        setJMenuBar(buildMenuBar());

        getContentPane().add(pnlNorth, BorderLayout.NORTH);
        getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        getContentPane().add(pnlCenter, BorderLayout.CENTER);
    }

    /**
     * Builds the menu bar by creating and initializing the menu bar, menus, and menu items.
     *
     * @return The built menu bar.
     */
    private JMenuBar buildMenuBar() {
        menuBar = new JMenuBar();

        mWindows = new JMenu("Windows");

        miOpenWorkCategories = new JMenuItem("WorkCategories");

        mWindows.add(miOpenWorkCategories);
        menuBar.add(mWindows);

        return menuBar;
    }

    /**
     * Builds the north panel by creating and initializing a JPanel with buttons for work, short break, and long break.
     *
     * @return The built north panel.
     */
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

    /**
     * Builds the south panel by creating and initializing a JPanel with a button for starting the timer and a combo box for work categories.
     *
     * @return The built south panel.
     */
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

    /**
     * Builds the center panel by creating and initializing a JPanel with a single label for displaying the timer.
     *
     * @return The built center panel.
     */
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

    /**
     * Updates the design of the component by setting the color design, font design, and border design.
     *
     * @param colorDesign The color design to be applied.
     * @param fontDesign The font design to be applied.
     * @param borderDesign The border design to be applied.
     */
    public void updateDesign(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        setColorDesign(colorDesign);
        setFontDesign(fontDesign);
        setBorderDesign(borderDesign);
        updateDesign();
    }

    /**
     * Updates the design of the component by calling various methods to design different parts of the component.
     * This method should be called after setting the color design, font design, and border design of the component.
     */
    public void updateDesign() {
        designContentPane();
        designMenuBar();
        designNorthPanel();
        designSouthPanel();
        designCenterPanel();
    }

    /**
     * Designs the content pane of the component by setting the background color.
     * This method should be called after setting the color design, font design, and border design of the component.
     */
    private void designContentPane() {
        getContentPane().setBackground(colorDesign.background());
    }

    /**
     * Designs the menu bar of the component by setting the background color, border, font, and colors of the menu items.
     * This method should be called after setting the color design, font design, and border design of the component.
     */
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
    }

    /**
     * Designs the components of the north panel by calling the appropriate design methods.
     * This method should be called after setting the color design, font design, and border design of the component.
     * @see WindowUtils
     */
    private void designNorthPanel() {
        WindowUtils.designBasePanel(pnlNorth, colorDesign, fontDesign, borderDesign);
        WindowUtils.designButton(btnWork, colorDesign, fontDesign, borderDesign);
        WindowUtils.designButton(btnShortBreak, colorDesign, fontDesign, borderDesign);
        WindowUtils.designButton(btnLongBreak, colorDesign, fontDesign, borderDesign);

    }

    /**
     * Designs the components of the south panel by calling the appropriate design methods.
     * This method should be called after setting the color design, font design, and border design of the component.
     * @see WindowUtils
     */
    private void designSouthPanel() {
        WindowUtils.designBasePanel(pnlSouth, colorDesign, fontDesign, borderDesign);
        WindowUtils.designComboBox(cbWorkCategories, colorDesign, fontDesign, borderDesign);
        WindowUtils.designButton(btnStart, colorDesign, fontDesign, borderDesign);
    }

    /**
     * Designs the components of the center panel by calling the appropriate design methods.
     * This method should be called after setting the color design, font design, and border design of the component.
     * @see WindowUtils
     */
    private void designCenterPanel() {
        WindowUtils.designBasePanel(pnlCenter, colorDesign, fontDesign, borderDesign);
        WindowUtils.designLabel(lblTimer, colorDesign, fontDesign, borderDesign);
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
        cbWorkCategories.removeAllItems();
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
