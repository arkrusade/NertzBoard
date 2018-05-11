package orctech.nertzboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import orctech.nertzboard.Adapters.TeamAdapter;

public class MainActivity extends AppCompatActivity {
    int scoreTemA = 0;
    int scoreTemB = 0;
    ListView mListView;
    int numTeams = 5;
    ArrayList<Integer> scores = new ArrayList<>(0);
    ArrayList<String> names = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
    ArrayList<Team> teams = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_teams);
        for (int i = 0; i < numTeams; i++) {
            scores.add(i);
            teams.add(new Team(names.get(i), scores.get(i)));
        }
        updateTable();
        mListView.setOnItemClickListener(mMessageClickedHandler);

    }
    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Context context = getApplicationContext();
            CharSequence text = "Hello toast! " + position;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    };
    public void updateTable() {
        TeamAdapter adapter = new TeamAdapter(this, teams);
        mListView.setAdapter(adapter);
    }


/*
    public void teamAOneScore(View view) {

        scoreTemA = scoreTemA + 1;
        displayForTeamA(scoreTemA);
    }

    public void teamATwoScore(View view) {
        scoreTemA = scoreTemA + 2;
        displayForTeamA(scoreTemA);
    }

    public void teamAThreeScore(View view) {
        scoreTemA = scoreTemA + 3;
        displayForTeamA(scoreTemA);
    }

    private void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText("" + score);
    }
/*
* Team A Code finish
* */

    /*
    * Team B Code Started
    *
    public void teamBOneScore(View view) {
        scoreTemB = scoreTemB + 1;
        displayForTeamB(scoreTemB);
    }

    public void teamBTwoScore(View view) {
        scoreTemB = scoreTemB + 2;
        displayForTeamB(scoreTemB);
    }

    public void teamBThreeScore(View view) {
        scoreTemB = scoreTemB + 3;
        displayForTeamB(scoreTemB);
    }

    private void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    * Team B Code finish
    *

    //Reset button code
    public void resetButton(View view) {
        scoreTemA = 0;
        scoreTemB = 0;
        displayForTeamA(0);
        displayForTeamB(0);

    }
    */
}