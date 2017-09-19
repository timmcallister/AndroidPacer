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

    long timeWhenPaused = 0;

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
        setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        timeWhenPaused = SystemClock.elapsedRealtime() - getBase();
    }

    public void reset(){
        stop();
        setBase(SystemClock.elapsedRealtime());
        timeWhenPaused = 0;
    }

    public long getTimeWhenPaused(){
        return timeWhenPaused;
    }

    public void setTimeWhenPaused(long time){
        timeWhenPaused = time;
        setBase(SystemClock.elapsedRealtime() - timeWhenPaused);
    }
}
