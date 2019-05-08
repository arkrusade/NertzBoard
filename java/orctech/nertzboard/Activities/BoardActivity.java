package orctech.nertzboard.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import orctech.nertzboard.Adapters.TeamAdapter;
import orctech.nertzboard.Models.Game;
import orctech.nertzboard.R;

public class BoardActivity extends AppCompatActivity {
    ListView mListView;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        mListView = (ListView) findViewById(R.id.list_teams);
        mListView.setOnItemClickListener(mMessageClickedHandler);

        Intent intent = this.getIntent();
        if (intent.hasExtra(NameActivity.GAME_INIT)) {
            Bundle b = intent.getExtras();
            String[] names = b.getStringArray(NameActivity.GAME_INIT);
            game = new Game(names);
        }

        updateTable();
        autoRun();
    }

    public void autoRun() {
                new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.end_round).callOnClick();
            }
        }, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RoundActivity.FINISH_WITH_SCORE) {
            if (data.hasExtra(RoundActivity.SCORE_ROUND)) {
                ArrayList<Integer> scores = data.getExtras().getIntegerArrayList(RoundActivity.SCORE_ROUND);
                game.addScores(scores);
                updateTable();
            }
        }
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView
            .OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Context context = getApplicationContext();
            CharSequence text = "Team number " + (position + 1);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    };

    public void endRound(View v) {
        Intent intent = new Intent(this, RoundActivity.class);
        intent.putExtra(RoundActivity.SCORE_ROUND, game.getTeams().size());
        startActivityForResult(intent, 0);
    }

    public void updateTable() {
        TeamAdapter adapter = new TeamAdapter(this, game.getTeams());
        mListView.setAdapter(adapter);
    }
}