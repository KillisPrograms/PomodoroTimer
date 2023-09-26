package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.WorkCategoriesWindow;
import net.justkilli.pomodorotimer.model.WorkCategory;
import net.justkilli.pomodorotimer.model.WorkCategoryModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * The SubmitWorkCategoryController class is responsible for handling the submission
 * of work categories in the WorkCategoriesWindow view. It listens for the submit button
 * action and performs the necessary actions to add a new work category to the model
 * and update the view accordingly.
 */
public class SubmitWorkCategoryController {

    private WorkCategoriesWindow workCategoriesWindow;
    private WorkCategoryModel model;

    /**
     * Constructs a new SubmitWorkCategoryController.
     *
     * @param workCategoriesWindow The workCategoriesWindow to submit work categories.
     * @param model The model representing the work category.
     */
    public SubmitWorkCategoryController(WorkCategoriesWindow workCategoriesWindow, WorkCategoryModel model) {
        this.workCategoriesWindow = workCategoriesWindow;
        this.model = model;

        workCategoriesWindow.addSubmitBtnActionListener(new SubmitWorkCategoryActionListener());
    }

    /**
     * This private class implements the ActionListener interface and is used for handling the submit button action
     * in the Work Categories window. It adds a new work category to the model and updates the view accordingly.
     */
    private class SubmitWorkCategoryActionListener implements ActionListener {
        private final List<WorkCategory> workCategories;

        public SubmitWorkCategoryActionListener() {
            workCategories = model.getAllWorkCategories();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = workCategoriesWindow.getNameTextFieldText();
            String description = workCategoriesWindow.getDescriptionTextFieldText();

            if(!canAddWorkCategory(name, description)) showErrorMessage("Please enter all Required Information to add a new Work Category!");
            else if(!addWorkCategoryToModel(name, description)) showErrorMessage("Could not add a new Work Category!");
            else updateWorkCategoriesInView();
        }
        /**
         * Clears the view components by setting the text of nameTextField and descriptionTextField to an empty string.
         */
        private void clearViewComponents() {
            workCategoriesWindow.setNameTextFieldText("");
            workCategoriesWindow.setDescriptionTextFieldText("");
        }
        /**
         * Updates the work categories displayed in the view by setting the workCategories
         * array to the work categories in the given list.
         */
        private void updateWorkCategoriesInView() {
            workCategoriesWindow.setWorkCategories(workCategories.toArray(new WorkCategory[0]));
        }

        /**
         * Adds a work category to the model.
         *
         * @param name        the name of the work category
         * @param description the description of the work category
         * @return true if the work category was successfully added to the model, false otherwise
         */
        private boolean addWorkCategoryToModel(String name, String description) {
            WorkCategory workCategory = new WorkCategory(model.getNextWorkCategoryId(), name, description);
            if (!model.addWorkCategory(workCategory)) return false;
            workCategories.add(workCategory);
            clearViewComponents();
            return true;
        }
        /**
         * Displays an error message as a dialog box.
         *
         * @param message The error message to be displayed.
         */
        private void showErrorMessage(String message) {
            JOptionPane.showMessageDialog(workCategoriesWindow, message);
        }

        /**
         * Determines whether a work category can be added.
         *
         * @param name        the name of the work category
         * @param description the description of the work category
         * @return true if the name and description of the work category are not blank, false otherwise
         */
        private boolean canAddWorkCategory(String name, String description) {
            return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(description);
        }
    }
}
