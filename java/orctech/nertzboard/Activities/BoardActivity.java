package orctech.nertzboard.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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


        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            String[] names = b.getStringArray(NameActivity.GAME_INIT);
            assert names != null;
            game = new Game(names);
            updateTable();
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
        //TODO:

    }
    public void updateTable() {
        TeamAdapter adapter = new TeamAdapter(this, game.getTeams());
        mListView.setAdapter(adapter);
    }
}