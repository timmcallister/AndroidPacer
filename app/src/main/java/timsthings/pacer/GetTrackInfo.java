package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetTrackInfo extends AppCompatActivity implements View.OnClickListener {

    Button trackContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_track_info);

        trackContinueButton = (Button) findViewById(R.id.trackContinue);
        trackContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //// TODO: 9/11/17 Implement track setup, create target laps
        
        switch (view.getId()){

            case R.id.trackContinue:
                Intent intent = new Intent(this, RunScreen.class);
                startActivity(intent);

            default:
                break;
        }
    }
}
