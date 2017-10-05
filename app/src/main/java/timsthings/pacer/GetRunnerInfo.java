package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class GetRunnerInfo extends AppCompatActivity implements View.OnClickListener {

    Button runnerContinueButton;
    RadioButton maleButton, femaleButton;
    TextView ageView;
    String[] ageTargets;
    String targetTime;
    Run run;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_runner_info);

        maleButton = (RadioButton) findViewById(R.id.radioMale);
        femaleButton = (RadioButton) findViewById(R.id.radioFemale);
        ageView = (TextView) findViewById(R.id.ageView);

        run = ((Run) getApplicationContext());
        runnerContinueButton = (Button) findViewById(R.id.runnerContinue);
        runnerContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.runnerContinue:

                if(validateAge()) {
                    if (maleButton.isChecked())
                        ageTargets = getResources().getStringArray(R.array.maleTargets);
                    else
                        ageTargets = getResources().getStringArray(R.array.femaleTargets);

                    age = Integer.parseInt(ageView.getText().toString());

                    if (age < 30)
                        targetTime = ageTargets[0];
                    else if (30 <= age && age < 40)
                        targetTime = ageTargets[1];
                    else if (40 <= age && age < 50)
                        targetTime = ageTargets[2];
                    else if (50 <= age && age < 60)
                        targetTime = ageTargets[3];
                    else
                        targetTime = ageTargets[4];

                    run.setTargetTime(targetTime);

                    Intent intent = new Intent(this, GetTrackInfo.class);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }

    }

    private boolean validateAge(){

        return (TextUtils.isDigitsOnly(ageView.getText())
                && Integer.parseInt(ageView.getText().toString()) < 0);
    }
}
