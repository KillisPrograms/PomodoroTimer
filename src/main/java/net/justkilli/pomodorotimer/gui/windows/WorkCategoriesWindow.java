package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.model.WorkCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The WorkCategoriesWindow class represents a window that allows users to manage work categories.
 * It extends the JFrame class to provide window functionality.
 */
public class WorkCategoriesWindow extends JFrame {
    private static final String TITLE = "Pomodoro Timer WorkCategories";
    private final ColorDesign colorDesign;
    private final FontDesign fontDesign;
    private final BorderDesign borderDesign;
    private JPanel pnlWest, pnlCenter;
    private JList<WorkCategory> lWorkCategories;
    private JScrollPane spWorkCategoriesContainer;
    private JTextField tfName, tfDescription;
    private JLabel lblName, lblDescription;
    private JButton btnSubmit;

    /**
     * Initializes and builds a WorkCategoriesWindow with the given color, font, and border designs.
     * Updates the design of the window.
     *
     * @param colorDesign  the color design for the window
     * @param fontDesign   the font design for the window
     * @param borderDesign the border design for the window
     */
    public WorkCategoriesWindow(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        this.colorDesign = colorDesign;
        this.fontDesign = fontDesign;
        this.borderDesign = borderDesign;
        init();
        build();
        updateDesign();
    }

    /**
     * Initializes the WorkCategoriesWindow with default settings:
     * - Sets the default close operation to DISPOSE_ON_CLOSE.
     * - Sets the size of the window to 800x800 pixels.
     * - Sets the title of the window to "Work Categories".
     * - Sets the layout manager of the content pane to BorderLayout with horizontal and vertical gaps of 10 pixels.
     */
    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout(10, 10));
    }

    /**
     * Builds the WorkCategoriesWindow by following these steps:
     * 1. Calls the {@link #buildWestPanel()} method to create and retrieve the west panel.
     * 2. Calls the {@link #buildCenterPanel()} method to create and retrieve the center panel.
     * 3. Adds the west panel to the content pane using the BorderLayout.WEST constraint.
     * 4. Adds the center panel to the content pane using the BorderLayout.CENTER constraint.
     */
    private void build() {

        pnlWest = buildWestPanel();
        pnlCenter = buildCenterPanel();

        getContentPane().add(pnlWest, BorderLayout.WEST);
        getContentPane().add(pnlCenter, BorderLayout.CENTER);
    }

    /**
     * Builds the west panel for the WorkCategoriesWindow.
     * @return the constructed west panel
     */
    private JPanel buildWestPanel() {
        Dimension pnlSize = new Dimension(200, 700);
        JPanel panel = new JPanel();
        panel.setPreferredSize(pnlSize);

        lWorkCategories = new JList<>();
        spWorkCategoriesContainer = new JScrollPane(lWorkCategories);
        spWorkCategoriesContainer.setPreferredSize(pnlSize);

        panel.add(spWorkCategoriesContainer);
        return panel;
    }

    /**
     * Builds the center panel for the WorkCategoriesWindow.
     * @return the constructed center panel
     */
    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);
        Dimension textFieldSize = new Dimension(300, 30);
        Dimension pnlSize = new Dimension(1000, 40);

        JPanel pnlName = new JPanel();
        pnlName.setMaximumSize(pnlSize);
        tfName = new JTextField();
        tfName.setPreferredSize(textFieldSize);
        lblName = new JLabel("Name");
        lblName.setLabelFor(tfName);
        pnlName.add(lblName);
        pnlName.add(tfName);

        JPanel pnlDescription = new JPanel();
        pnlDescription.setMaximumSize(pnlSize);
        tfDescription = new JTextField();
        tfDescription.setPreferredSize(textFieldSize);
        lblDescription = new JLabel("Description");
        lblDescription.setLabelFor(tfDescription);
        pnlDescription.add(lblDescription);
        pnlDescription.add(tfDescription);

        btnSubmit = new JButton("Submit");
        btnSubmit.setMaximumSize(new Dimension(100, 40));

        panel.add(pnlName);
        panel.add(pnlDescription);
        panel.add(btnSubmit);

        return panel;
    }

    public void updateDesign() {

    }

    public void setWorkCategories(WorkCategory[] categories) {
        lWorkCategories.setListData(categories);
    }

    public void setNameTextFieldText(String newText) {
        tfName.setText(newText);
    }
    public void setDescriptionTextFieldText(String newText) {
        tfDescription.setText(newText);
    }
    public String getNameTextFieldText() {
        return tfName.getText();
    }
    public String getDescriptionTextFieldText() {
        return tfDescription.getText();
    }

    public void addSubmitBtnActionListener(ActionListener listener) {
        btnSubmit.addActionListener(listener);
    }
}
