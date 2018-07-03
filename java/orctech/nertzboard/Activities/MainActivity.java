package orctech.nertzboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import orctech.nertzboard.R;

public class MainActivity extends AppCompatActivity {
    public static final String NUM_TEAMS_INIT = "orctech.nertzboard.NUM_TEAMS_INIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText num_text = (EditText) findViewById(R.id.num_teams_init);
        num_text.setText("2");
        final Button b = findViewById(R.id.set_num_teams);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                b.performClick();
            }
        }, 1000);
    }

    public void initializeTeams(View view) {
        Intent intent = new Intent(this, NameActivity.class);
        EditText num_text = (EditText) findViewById(R.id.num_teams_init);
        intent.putExtra(NUM_TEAMS_INIT, Integer.parseInt(num_text.getText().toString()));
        startActivity(intent);
    }

}