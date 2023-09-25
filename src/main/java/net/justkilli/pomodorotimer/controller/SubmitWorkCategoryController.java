package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitWorkCategoryController {

    private WorkCategoriesWindow view;
    private WorkCategoryModel model;

    public SubmitWorkCategoryController(WorkCategoriesWindow view, WorkCategoryModel model) {
        this.view = view;
        this.model = model;

        view.addSubmitBtnActionListener(new SubmitWorkCategoryActionListener());
    }

    private class SubmitWorkCategoryActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameTextFieldText();
            String description = view.getDescriptionTextFieldText();
            if(!checkRequirements(name, description)) {
                JOptionPane.showMessageDialog(view, "Please enter all Required Information to add a new Work Category!");
                return;
            }
            WorkCategory workCategory = new WorkCategory(model.getNextWorkCategoryId(), name, description);
            if(!model.addWorkCategory(workCategory)) {
                JOptionPane.showMessageDialog(view, "Could not add a new Work Category!");
                return;
            }
            view.setNameTextFieldText("");
            view.setDescriptionTextFieldText("");
            view.setWorkCategories(addWorkCategory(view.getWorkCategories(), workCategory));
        }
        private WorkCategory[] addWorkCategory(WorkCategory[] workCategories, WorkCategory workCategory) {
            WorkCategory[] result = new WorkCategory[workCategories.length + 1];
            for(int i = 0; i < workCategories.length; i++) {
                result[i] = workCategories[i];
            }
            result[workCategories.length] = workCategory;
            return result;
        }
        private boolean checkRequirements(String name, String description) {
            if(name == null || description == null) return false;
            return ! name.trim().isEmpty() && ! description.trim().isEmpty();
        }

    }

}
