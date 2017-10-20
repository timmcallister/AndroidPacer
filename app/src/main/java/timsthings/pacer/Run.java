/*
    Application-wide Run object. Object stores run state, stores number of laps, target time,
    partial first lap, and an array of cumulative lap times.
 */

package timsthings.pacer;

import android.app.Application;
import java.util.ArrayList;


/**
 * Created by tim on 9/15/17.
 */

public class Run extends Application {

    private boolean partialLap;
    private double runLaps;
    private String targetTime;
    private ArrayList<Long> laps;

    // Constructor for run object
    public Run() {
        this.laps = new ArrayList<>();
    }

    // Add lap to Run object
    public void addLap(Long lap) {
        laps.add(lap);
    }

    // Erases all lap data. Does not clear target time, number of laps, or partial lap
    public void resetRun() {
        laps.clear();
    }

    // Calculates linear regression and forecasts total run time
    public long predictRunTime() {

        long predictedTime;

        double sampleMeanX = getSampleMeanX(laps.size());
        double sampleMeanY = getSampleMeanY();

        double slope = getSlope(sampleMeanX, sampleMeanY);

        double yIntercept = getYIntercept(sampleMeanX, sampleMeanY, slope);

        predictedTime = (long) ((slope * runLaps) + yIntercept);

        return predictedTime;

    }

    // Gets the slope of the linear regression line
    private double getSlope(double sampleX, double sampleY) {

        double dividend = 0;
        double divisor = 0;

        for (int i = 0; i < laps.size(); i++) {
            dividend += ((i + 1) - sampleX) * (laps.get(i) - sampleY);
            divisor += Math.pow(((i + 1) - sampleX), 2);
        }

        return dividend / divisor;
    }

    // Gets the Y-intercept of the linear regression line
    private double getYIntercept(double sampleX, double sampleY, double slope) {

        return sampleY - (slope * sampleX);
    }

    // Gets the sample mean of the lap numbers
    private double getSampleMeanX(int x) {
        double sumToX = (x * (x + 1)) / 2;

        return sumToX / x;
    }

    // Gets the sample mean of the lap times
    private double getSampleMeanY() {
        long sum = 0;
        for (Long num : laps)
            sum += num;

        return sum / laps.size();
    }

    // if first lap is partial, it must be ignored.
    public boolean isPartialLap() {
        return partialLap;
    }

    public void setPartialLap(boolean partialLap) {
        this.partialLap = partialLap;
    }

    // Determine how many laps have been completed
    public int getCurrentLap() {
        return laps.size();
    }

    // Determines number of laps that will be completed for prediction purposes.
    public void setRunLaps(double runLaps) {

        this.runLaps = runLaps;
    }

    // Returns target time set by runner info screen
    public String getTargetTime() {
        return targetTime;
    }

    // Runner info screen sets this value
    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }
}






