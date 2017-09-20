package timsthings.pacer;

import java.text.SimpleDateFormat;

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
    TextView testView;
    TextView lapView;
    TextView lapTimeView;
    Run run;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_screen);

        timeFormatter = new SimpleDateFormat("mm:ss");
        run = new Run();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stopWatch = (StopWatch) findViewById(R.id.chronoWidget);
        startRecord = (Button) findViewById(R.id.startRecordButton);
        resetPause = (Button) findViewById(R.id.resetPauseButton);
        startRecord.setOnClickListener(this);
        resetPause.setOnClickListener(this);

        // for testing purposes
        testView = (TextView) findViewById(R.id.testDisplayView);
        lapView = (TextView) findViewById(R.id.lapNumberView);
        lapTimeView = (TextView) findViewById(R.id.lapTimeView);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.startRecordButton:
                if(stopWatch.getRunning()){             // stopwatch is running (record lap)
                    // TODO: 9/19/17 Implement record Lap
                    // start testing
                    testView.setText(timeFormatter.format(stopWatch.getTime()));
                    run.incrementLap();
                    lapView.setText(String.valueOf(run.getCurrentLap()));
                    if(run.getCurrentLap() == 13)
                        lapTimeView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWarning));
                    else if (run.getCurrentLap() == 20){
                        lapTimeView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCaution));
                        vibrator.vibrate(750);
                    }
                    else
                        lapTimeView.setBackgroundColor(0x00000000);
                    //stop testing
                    stopWatch.reset();
                    stopWatch.start();

                }
                else {                                  // stopwatch not running (start run)
                    stopWatch.start();
                    stopWatch.setRunning(true);
                    startRecord.setText(R.string.record_lap);
                    resetPause.setText(R.string.pause);
                }
                break;
            case R.id.resetPauseButton:
                if(stopWatch.getRunning()){             // stopwatch is running, (pause)
                    stopWatch.setRunning(false);
                    stopWatch.stop();
                    resetPause.setText(R.string.reset);
                    startRecord.setText(R.string.resume);
                }
                else {                                  // stopwatch not running (reset)
                    stopWatch.reset();
                    startRecord.setText(R.string.start_run);
                    // TODO: 9/19/17 Implement Run Reset
                    // TODO: 9/20/17 remove this
                    run.setCurrentLap(0);
                    lapView.setText(String.valueOf(run.getCurrentLap()));

                }
                break;
            default:
                break;
        }

    }
}
