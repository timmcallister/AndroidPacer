/*
    Gets track information. Application predicts cumulative time at lap number given here.
    Application cannot predict using partial laps, so they must be ignored.
    First partial lap is set in this screen, if applicable.
 */

package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        // Access application 'Run' object
        run = ((Run) getApplicationContext());

        // initialize lap variable and associate objects with screen views
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

                /*
                Ensures value was entered in laps and determines if first lap is partial.
                Sets appropriate Run object variables
                 */

                if(validateLaps()) {
                    laps = Integer.parseInt(numLaps.getText().toString());

                    // If first lap is partial, it must be ignored
                    if (partialLapChk.isChecked())
                        laps -= 1;

                    run.setRunLaps((double) laps);
                    run.setPartialLap(partialLapChk.isChecked());

                    // Opens next screen (Run screen)
                    Intent intent = new Intent(this, RunScreen.class);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }
    }

    // Ensures value was entered in laps field
    private boolean validateLaps(){
        return !(numLaps.getText().toString().isEmpty());
    }
}
