package timsthings.pacer;

import java.util.ArrayList;

/**
 * Created by tim on 9/15/17.
 */

public class Run {

    private boolean partialLap;
    private int currentLap;
    private int numLaps;
    private long currentLapTime;
    private long targetLapTime;
    private long predictedLapTime;
    private long totalTime;
    private long targetTotalTime;
    private long predictedTotalTime;
    private ArrayList<Long> laps;


    // TODO: 9/20/17 Remove this
    // Begin testing verion
    public Run() {
        laps = new ArrayList<>();
        this.currentLap = 0;
    }

    public void addLap(Long lap) {
        laps.add(lap);
        this.currentLap++;
    }

    public void resetRun() {
        laps.clear();
        this.currentLap = 0;
    }

    // Calculates linear regression and forecasts total run time
    public long predictRunTime() {

        // TODO: 9/26/17 remove this test value
        int testLaps = 24;
        long predictedTime;

        double sampleMeanX = getSampleMeanX(laps.size());
        double sampleMeanY = getSampleMeanY();

        double slope = getSlope(sampleMeanX, sampleMeanY);

        double yIntercept = getYIntercept(sampleMeanX, sampleMeanY, slope);

        predictedTime = (long) ((slope * testLaps) + yIntercept);

        return predictedTime;

    }

    // Gets the slope of the linear regression line
    private double getSlope(double sampleX, double sampleY) {

        double dividend = 0;
        double divisor = 0;

        for (int i = 1; i < laps.size(); i++) {
            dividend += (i - sampleX) * (laps.get(i) - sampleY);
            divisor += Math.pow((i - sampleX), 2);
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

        return sum / this.currentLap;
    }


    public void incrementLap() {
        this.currentLap++;
    }

    public boolean isPartialLap() {
        return partialLap;
    }

    public void setPartialLap(boolean partialLap) {
        this.partialLap = partialLap;
    }

    public int getCurrentLap() {
        return laps.size();
    }


}




