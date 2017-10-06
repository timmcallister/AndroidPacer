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

        run = ((Run) getApplicationContext());

        timeFormatter = new SimpleDateFormat("mm:ss");
        timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // attaching views to program objects
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

        try {
            targetTime = timeFormatter.parse(run.getTargetTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // this number is for the display only. It could be different than the Run laps.
        lapDisplayNumber = 0;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.startRecordButton:
                if(stopWatch.getRunning()){             // stopwatch is running (record lap)

                    stopWatch.addLap();

                    if(run.isPartialLap())
                        run.setPartialLap(false);
                    else
                        run.addLap(stopWatch.getTotalTime());

                    lastLapTimeView.setText(timeFormatter.format(stopWatch.getTime()));
                    lapDisplayNumber++;

                    lapView.setText(String.valueOf(lapDisplayNumber));
                    stopWatch.reset();
                    stopWatch.start();

                    if(run.getCurrentLap() > 1) {

                        predictedTime = run.predictRunTime();
                        predictedTimeView.setText(timeFormatter.format(predictedTime));

                        if(targetTime < predictedTime){
                            predictedTimeView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWarning));
                            vibrator.vibrate(750);
                        }
                        else
                            predictedTimeView.setBackgroundColor(0x00000000);


                    }

                }
                else {                                  // stopwatch not running (start run)
                    stopWatch.start();
                    startRecord.setText(R.string.record_lap);
                    resetPause.setText(R.string.pause);
                }
                break;
            case R.id.resetPauseButton:
                if(stopWatch.getRunning()){             // stopwatch is running, (pause)
                    stopWatch.stop();
                    resetPause.setText(R.string.reset);
                    startRecord.setText(R.string.resume);
                }
                else {                                  // stopwatch not running (reset)
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
