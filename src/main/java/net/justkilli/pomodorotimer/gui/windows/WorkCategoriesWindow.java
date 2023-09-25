package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;
import net.justkilli.pomodorotimer.model.WorkCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public WorkCategoriesWindow(ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        this.colorDesign = colorDesign;
        this.fontDesign = fontDesign;
        this.borderDesign = borderDesign;
        init();
        build();
        updateDesign();
    }

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout(10, 10));
    }

    private void build() {

        pnlWest = buildWestPanel();
        pnlCenter = buildCenterPanel();

        getContentPane().add(pnlWest, BorderLayout.WEST);
        getContentPane().add(pnlCenter, BorderLayout.CENTER);
    }

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
    public WorkCategory[] getWorkCategories() {
        ListModel<WorkCategory> model = lWorkCategories.getModel();
        WorkCategory[] workCategories = new WorkCategory[model.getSize()];
        for(int i = 0; i < workCategories.length; i++) {
            workCategories[i] = model.getElementAt(i);
        }
        return workCategories;
    }

}
