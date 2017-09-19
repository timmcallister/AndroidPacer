package timsthings.pacer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class RunScreen extends AppCompatActivity implements View.OnClickListener {

    Button startRecord;
    Chronometer runChrono;

    long startTime, startLap, lapTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_screen);

        runChrono = (Chronometer) findViewById(R.id.runChrono);

        startRecord = (Button) findViewById(R.id.startRecord);
        startRecord.setOnClickListener(this);

        //// TODO: 9/11/17 Display TGT lap and total times

        //// TODO: 9/11/17 Implement core logic.
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.runChrono:
                runChrono.setBase(SystemClock.elapsedRealtime());
                runChrono.start();

            default:
                break;
        }

    }
}
