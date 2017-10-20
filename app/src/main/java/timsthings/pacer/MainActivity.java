/*
    First screen, mainly serves to warn members not to use the application during an official test
 */

package timsthings.pacer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mainContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContinueButton = (Button) findViewById(R.id.mainContinue);
        mainContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.mainContinue:

                // Open next screen (Runner info screen)
                Intent intent = new Intent(this, GetRunnerInfo.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
