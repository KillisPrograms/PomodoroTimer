package net.justkilli.pomodorotimer.controller;

import net.justkilli.pomodorotimer.gui.windows.MainWindow;
import net.justkilli.pomodorotimer.model.WorkModel;
import net.justkilli.pomodorotimer.model.WorkType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class WorkController {

    private final MainWindow view;
    private final WorkModel model;
    private WorkTimer timerObj;
    private Timer timer;

    public WorkController(MainWindow view, WorkModel model) {
        this.view = view;
        this.model = model;

        view.addBtnWorkActionListener(new WorkBtnActionListener());
    }

    private class WorkBtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.isWorking()) {
                model.stopWork();
                timer.cancel();
                view.changeBtnWorkText("Work");
                view.updateTimer("00:00:00");
            } else {
                model.startWork(view.getSelectedWorkCategory(), WorkType.WORK);
                view.changeBtnWorkText("Work started");
                timerObj = new WorkTimer();
                timer = new Timer();
                timer.schedule(timerObj, 0L, 1000L);
            }
        }
    }

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
