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
    TextView predictedTimeView;
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
        predictedTimeView = (TextView) findViewById(R.id.predictedRunTimeView);


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
//                    if(run.getCurrentLap() == 13) {
//                        lapView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWarning));
//                        lapView.setText("ABC");
//                    }
//                    else if (run.getCurrentLap() == 20){
//                        lapTimeView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCaution));
//                        lapTimeView.setText("Test message");
//                        vibrator.vibrate(750);
//                    }
//                    else {
//                        lapTimeView.setBackgroundColor(0x00000000);
//                        lapView.setBackgroundColor(0x00000000);
//                        lapTimeView.setText("MM:SS");
//                    }
                    //stop testing
                    stopWatch.addLap();
                    run.addLap(stopWatch.getTotalTime());
                    stopWatch.reset();
                    stopWatch.start();

                    if(run.getCurrentLap() > 2)
                        predictedTimeView.setText(timeFormatter.format(run.predictRunTime()));

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
                    // TODO: 9/19/17 Implement Run Reset
                    // TODO: 9/20/17 remove this
                    run.resetRun();
                    lapView.setText(String.valueOf(run.getCurrentLap()));
                }
                break;
            default:
                break;
        }

    }
}
