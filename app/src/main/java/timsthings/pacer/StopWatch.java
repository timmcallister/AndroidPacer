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
    long totalTime = 0;

    public StopWatch(Context context) {
        super(context);
    }

    public StopWatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StopWatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StopWatch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void start() {
        setBase(SystemClock.elapsedRealtime() - timeWhenPaused);
        super.start();
        this.setRunning(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.setRunning(false);
        timeWhenPaused = SystemClock.elapsedRealtime() - getBase();
    }

    public void reset(){
        stop();
        this.setRunning(false);
        setBase(SystemClock.elapsedRealtime());
        timeWhenPaused = 0;
        totalTime = 0;
    }

    public void addLap(){
        totalTime += getTime();
    }

    public long getTimeWhenPaused(){
        return timeWhenPaused;
    }

    public void setTimeWhenPaused(long time){
        timeWhenPaused = time;
        setBase(SystemClock.elapsedRealtime() - timeWhenPaused);
    }

    public long getTime(){
        return SystemClock.elapsedRealtime() - getBase();
    }

    public long getTotalTime() {
        return totalTime;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }
}
