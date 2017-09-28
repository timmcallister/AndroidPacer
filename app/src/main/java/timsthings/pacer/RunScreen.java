package timsthings.pacer;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.os.Vibrator;
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
    int lapDisplayNumber;

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
        lapDisplayNumber = 0;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.startRecordButton:
                if(stopWatch.getRunning()){             // stopwatch is running (record lap)

                    if(!run.isPartialLap() || run.getCurrentLap() != 1)
                        run.addLap(stopWatch.getTotalTime());

                    testView.setText(timeFormatter.format(stopWatch.getTime()));
                    lapDisplayNumber++;

                    //// TODO: 9/28/17 change this back
                    lapView.setText(timeFormatter.format(stopWatch.getTotalTime()));
                    stopWatch.reset();
                    stopWatch.start();

                    if(run.getCurrentLap() > 1)
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
                    run.resetRun();
                    lapDisplayNumber = 0;
                    lapView.setText(String.valueOf(lapDisplayNumber));
                    testView.setText(timeFormatter.format(stopWatch.getTime()));

                }
                break;
            default:
                break;
        }

    }
}
