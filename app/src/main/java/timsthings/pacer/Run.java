package timsthings.pacer;

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

    public Run(boolean partialLap, int numLaps, long predictedLapTime) {
        this.partialLap = partialLap;
        this.numLaps = numLaps;
        this.predictedLapTime = predictedLapTime;
    }

    public boolean isPartialLap() {
        return partialLap;
    }

    public void setPartialLap(boolean partialLap) {
        this.partialLap = partialLap;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    public int getNumLaps() {
        return numLaps;
    }

    public void setNumLaps(int numLaps) {
        this.numLaps = numLaps;
    }

    public long getCurrentLapTime() {
        return currentLapTime;
    }

    public void setCurrentLapTime(long currentLapTime) {
        this.currentLapTime = currentLapTime;
    }

    public long getTargetLapTime() {
        return targetLapTime;
    }

    public void setTargetLapTime(long targetLapTime) {
        this.targetLapTime = targetLapTime;
    }

    public long getPredictedLapTime() {
        return predictedLapTime;
    }

    public void setPredictedLapTime(long predictedLapTime) {
        this.predictedLapTime = predictedLapTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getTargetTotalTime() {
        return targetTotalTime;
    }

    public void setTargetTotalTime(long targetTotalTime) {
        this.targetTotalTime = targetTotalTime;
    }

    public long getPredictedTotalTime() {
        return predictedTotalTime;
    }

    public void setPredictedTotalTime(long predictedTotalTime) {
        this.predictedTotalTime = predictedTotalTime;
    }
}
