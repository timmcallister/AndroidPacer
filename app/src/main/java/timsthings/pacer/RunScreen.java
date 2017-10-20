package timsthings.pacer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RunScreen extends AppCompatActivity implements View.OnClickListener {

    Button startRecord, resetPause;
    SimpleDateFormat timeFormatter;
    StopWatch stopWatch;
    TextView lastLapTimeView;
    TextView lapView;
    TextView predictedTimeView;
    TextView targetTimeView;
    Run run;
    Vibrator vibrator;
    int lapDisplayNumber;
    long targetTime, predictedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_screen);

        // Access application run object
        run = ((Run) getApplicationContext());

        // formatting display of time objects which are stored as longs in minute:second format
        timeFormatter = new SimpleDateFormat("mm:ss");
        timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Prepare vibrator object to notify user
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // attaching views to program objects, add click listeners
        stopWatch = (StopWatch) findViewById(R.id.chronoWidget);
        startRecord = (Button) findViewById(R.id.startRecordButton);
        resetPause = (Button) findViewById(R.id.resetPauseButton);
        startRecord.setOnClickListener(this);
        resetPause.setOnClickListener(this);
        lastLapTimeView = (TextView) findViewById(R.id.lastLapTimeView);
        lapView = (TextView) findViewById(R.id.lapNumberView);
        predictedTimeView = (TextView) findViewById(R.id.predictedTimeView);
        targetTimeView = (TextView) findViewById(R.id.targetRunTimeView);
        targetTimeView.setText(run.getTargetTime());

        // format parsing is a checked exception, must be in a try/catch
        try {
            // Display target time
            targetTime = timeFormatter.parse(run.getTargetTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // This number is for the display only. It could be different than the Run laps
        // if there is a partial first lap.
        lapDisplayNumber = 0;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.startRecordButton:
                if(stopWatch.getRunning()){             // stopwatch is running (record lap)

                    stopWatch.addLap();                 // Adds lap time to cumulative time

                    // Partial lap cannot be used in predictions, therefore if the current lap is
                    // partial do not add it to the Run object.
                    if(run.isPartialLap())
                        run.setPartialLap(false);
                    else
                        run.addLap(stopWatch.getTotalTime());

                    // Display last lap time and increment the display lap
                    lastLapTimeView.setText(timeFormatter.format(stopWatch.getTime()));
                    lapDisplayNumber++;
                    lapView.setText(String.valueOf(lapDisplayNumber));

                    // Reset lap time
                    stopWatch.reset();
                    stopWatch.start();

                    // Run prediction algorithm uses
                    // at least two lap times (not counting a partial lap)
                    if(run.getCurrentLap() > 1) {

                        // Predict and display a total time
                        predictedTime = run.predictRunTime();
                        predictedTimeView.setText(timeFormatter.format(predictedTime));

                        // Determine if runner is on pace to meet target time. If not, notify user.
                        if(targetTime < predictedTime){
                            predictedTimeView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWarning));
                            vibrator.vibrate(750);
                        }
                        else
                            predictedTimeView.setBackgroundColor(0x00000000);


                    }

                }
                else {                                  // stopwatch not running (start run)

                    // Starts stopwatch and changes button text
                    stopWatch.start();
                    startRecord.setText(R.string.record_lap);
                    resetPause.setText(R.string.pause);
                }
                break;
            case R.id.resetPauseButton:
                if(stopWatch.getRunning()){             // stopwatch is running, (pause)

                    // Stops stopwatch and changes button text
                    stopWatch.stop();
                    resetPause.setText(R.string.reset);
                    startRecord.setText(R.string.resume);
                }
                else {                                  // stopwatch not running (reset)

                    // Resets stopwatch, changes button text, resets lap time, lap count,
                    // predicted time fields and variables. Clears Run object lap ArrayList.
                    stopWatch.reset();
                    startRecord.setText(R.string.start_run);
                    run.resetRun();
                    lapDisplayNumber = 0;
                    stopWatch.setTotalTime(0);
                    lapView.setText(String.valueOf(lapDisplayNumber));
                    lastLapTimeView.setText(timeFormatter.format(stopWatch.getTime()));
                    predictedTimeView.setText("00:00");
                    predictedTimeView.setBackgroundColor(0x00000000);

                }
                break;
            default:
                break;
        }

    }
}
