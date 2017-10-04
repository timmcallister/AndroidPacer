package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetRunnerInfo extends AppCompatActivity implements View.OnClickListener {

    Button runnerContinueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_runner_info);

        runnerContinueButton = (Button) findViewById(R.id.runnerContinue);
        runnerContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //// TODO: 9/11/17 Implement target time selection logic

        switch (view.getId()){

            case R.id.runnerContinue:
                Intent intent = new Intent(this, GetTrackInfo.class);
                startActivity(intent);

                break;

            default:
                break;
        }

    }
}
