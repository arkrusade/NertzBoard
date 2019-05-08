package orctech.nertzboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import orctech.nertzboard.Adapters.RoundAdapter;
import orctech.nertzboard.R;

public class RoundActivity extends AppCompatActivity {
    public static final String SCORE_ROUND = "orctech.nertzboard.SCORE_ROUND";
    public static final int CANCEL = 0;
    public static final int FINISH_WITH_SCORE = 1;
    int numTeams;
    ListView mListView;
    ArrayList<Integer> roundScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        Intent intent = getIntent();
        numTeams = intent.getIntExtra(SCORE_ROUND, 0);

        roundScores = new ArrayList<>(numTeams);
        mListView = (ListView) findViewById(R.id.list_round_layout);
        mListView.setItemsCanFocus(true);

        for (int i = 0; i < numTeams; i++) {
            roundScores.add(i, 0);
        }

        updateTable();


    }
    private void updateTable() {
        RoundAdapter adapt = new RoundAdapter(this, roundScores);
        mListView.setAdapter(adapt);
    }
    public void scoreRound(View view) {
        updateTable();
        Intent intent = new Intent(this, BoardActivity.class);
        Bundle b = new Bundle();
        b.putIntegerArrayList(SCORE_ROUND, roundScores);
        intent.putExtras(b);
        setResult(FINISH_WITH_SCORE, intent);
        finish();
    }
    // Activates EditText of name for team when clicked
//    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
//        public void onItemClick(AdapterView parent, View v, int position, long id) {
//            Context context = getApplicationContext();
//            EditText activate = v.findViewById(R.id.name_edit);
//            activate.selectAll();
//        }
//    };

}