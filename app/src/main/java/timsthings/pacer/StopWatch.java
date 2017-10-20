/*
    Chronometer object use to get and display time.
 */

package timsthings.pacer;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

/**
 * Created by tim on 9/19/17.
 * Referenced David Stosik from Stack overflow -
 * https://stackoverflow.com/questions/5594877/android-chronometer-pause
 */

public class StopWatch extends Chronometer {

    boolean isRunning = false;
    long timeWhenPaused = 0;
    long totalTime = 0;                 // Cumulative time since started.

    // Constructors
    public StopWatch(Context context) {
        super(context);
    }

    public StopWatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StopWatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Starts/restarts stopwatch counting, taking into account whether it was paused.
    @Override
    public void start() {
        setBase(SystemClock.elapsedRealtime() - timeWhenPaused);
        super.start();
        this.setRunning(true);
    }

    // Stops stopwatch counting, records time when stopped.
    @Override
    public void stop() {
        super.stop();
        this.setRunning(false);
        timeWhenPaused = SystemClock.elapsedRealtime() - getBase();
    }

    // Erases paused time so clock will start at 0
    public void reset(){
        stop();
        this.setRunning(false);
        setBase(SystemClock.elapsedRealtime());
        timeWhenPaused = 0;
    }

    // Returns cumulative time since started
    public long getTotalTime(){
        return totalTime;
    }

    // Adds lap time to cumulative time
    public void addLap(){
        totalTime += getTime();
    }

    // Gets current time from stopwatch
    public long getTime(){
        return SystemClock.elapsedRealtime() - getBase();
    }

    // Determine whether stopwatch is running
    public Boolean getRunning() {
        return isRunning;
    }

    // Sets running value
    public void setRunning(Boolean running) {
        isRunning = running;
    }

    // Sets cumulative value (used for resetting clock)
    public void setTotalTime(long time){
        this.totalTime = time;
    }
}
