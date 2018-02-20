package com.first.team2052.lib;

import com.google.common.collect.Lists;
import edu.wpi.first.wpilibj.Notifier;

import java.util.List;

public class ControlLoop {
    private final Object runningThread = new Object();
    private double period = 1.0 / 100.0;
    private List<Loopable> loopables = Lists.newArrayList();
    boolean running = false;

    private Runnable runnable = () -> {
        if (!running)
            return;
        for (Loopable loopable : loopables) {
            loopable.update();
        }
    };

    private Notifier notifier;

    public ControlLoop(double period) {
        this.period = period;
        notifier = new Notifier(runnable);
    }

    public void start() {
        running = true;

        for (Loopable loopable : loopables) {
            loopable.onStart();
            System.out.println("Loopable Started: " + loopable.getClass().getName());
        }

        notifier.startPeriodic(period);
    }

    public void stop() {
        running = false;
        for (Loopable loopable : loopables) {
            loopable.onStop();
            System.out.println("Loopable Stopped: " + loopable.getClass().getName());
        }
        System.out.println("Stopped Control Loop");
    }

    public void addLoopable(Loopable loopable) {
        System.out.println("Loopable Added: " + loopable.getClass().getName() + "  Period: " + period);
        loopables.add(loopable);
    }
}
