package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.model.WorkModel;
import net.justkilli.pomodorotimer.model.TimerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The WorkController class is responsible for controlling the interaction between the MainWindow view and the WorkModel model.
 */
public class WorkController {

    private final MainWindow view;
    private final WorkModel model;
    private WorkTimer timerObj;
    private Timer timer;

    /**
     * The WorkController class is responsible for controlling the interaction between the MainWindow view and the WorkModel model.
     *
     * @param view The MainWindow object representing the user interface.
     * @param model The WorkModel object representing the data and logic.
     */
    public WorkController(MainWindow view, WorkModel model) {
        this.view = view;
        this.model = model;

        view.addBtnWorkActionListener(new WorkBtnActionListener());
    }

    /**
     * Private inner class that implements ActionListener interface.
     * Handles the actions performed when the work button is clicked.
     */
    private class WorkBtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.isWorking()) handleStopWork();
            else handleStartWork();
        }

        /**
         * Stops the work, cancels the timer, and updates the view.
         */
        private void handleStopWork() {
            model.stopWork();
            timer.cancel();
            view.changeBtnWorkText("Work");
            view.updateTimer("00:00:00");
        }

        /**
         * Starts the work, updates the view, and schedules the timer to update the timer display every second.
         */
        private void handleStartWork() {
            model.startWork(view.getSelectedWorkCategory(), TimerType.WORK);
            view.changeBtnWorkText("Work started");
            timerObj = new WorkTimer();
            timer = new Timer();
            timer.schedule(timerObj, 0L, 1000L);
        }

    }

    /**
     * A class representing a work timer.
     */
    private class WorkTimer extends TimerTask {
        private LocalTime time = LocalTime.MIN;
        @Override
        public void run() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time = time.plusSeconds(1);
            view.updateTimer(time.format(formatter));
        }
    }

}
