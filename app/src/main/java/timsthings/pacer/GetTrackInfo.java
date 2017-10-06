package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class GetTrackInfo extends AppCompatActivity implements View.OnClickListener {

    Button trackContinueButton;
    CheckBox partialLapChk;
    TextView numLaps;
    int laps;
    Run run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_track_info);

        run = ((Run) getApplicationContext());

        laps = 0;
        numLaps = (TextView) findViewById(R.id.numberLaps);
        partialLapChk = (CheckBox) findViewById(R.id.partialLapCheck);
        trackContinueButton = (Button) findViewById(R.id.trackContinue);
        trackContinueButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        
        switch (view.getId()){

            case R.id.trackContinue:

                if(validateLaps()) {
                    laps = Integer.parseInt(numLaps.getText().toString());

                    if (partialLapChk.isChecked())
                        laps -= 1;

                    run.setRunLaps((double) laps);
                    run.setPartialLap(partialLapChk.isChecked());

                    Intent intent = new Intent(this, RunScreen.class);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }
    }

    private boolean validateLaps(){
        return !(numLaps.getText().toString().isEmpty());
    }
}
