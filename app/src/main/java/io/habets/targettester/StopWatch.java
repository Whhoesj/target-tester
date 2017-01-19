package io.habets.targettester;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wouter on 19-1-17.
 */

public class StopWatch {

    private static final String FORMAT = "mm:ss";

    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public String stopAndGetTime() {
        long totalTime = System.currentTimeMillis() - startTime;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        return formatter.format(new Date(totalTime));
    }
}
