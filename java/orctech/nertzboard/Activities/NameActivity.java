package orctech.nertzboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import orctech.nertzboard.Adapters.NameAdapter;
import orctech.nertzboard.R;

public class NameActivity extends AppCompatActivity {
    public static final String GAME_INIT = "orctech.nertzboard.GAME_INIT";
    int numTeams;
    ListView mListView;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent intent = getIntent();
        numTeams = intent.getIntExtra(MainActivity.NUM_TEAMS_INIT, 0);

        names = new ArrayList<>(numTeams);
        mListView = (ListView) findViewById(R.id.list_naming);
        mListView.setItemsCanFocus(true);

        for (int i = 0; i < numTeams; i++) {
            names.add(i, "Enter Name Here");
        }

        //TODO: remove debug
        for (int i = 0; i < numTeams; i++) {
            names.set(i, "" + (i + 1) + " " + (i + 1));
        }
        updateTable();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.set_names).callOnClick();
            }
        }, 1000);

//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                View entry = (View) mListView.getAdapter().getItem(0);
//                entry.findViewById(R.id.name_edit).requestFocus();
//            }
//        }, 1000);
//        mListView.setOnItemClickListener(mMessageClickedHandler);
    }

    private void updateTable() {
        NameAdapter adapt = new NameAdapter(this, names);
        mListView.setAdapter(adapt);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, BoardActivity.class);
        Bundle b = new Bundle();
        NameAdapter adapt = new NameAdapter(this, names);
        mListView.setAdapter(adapt);
        b.putStringArray(GAME_INIT, names.toArray(new String[names.size()]));
        intent.putExtras(b);
        startActivity(intent);
    }
    // Activates EditText of name for team when clicked
//    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView
// .OnItemClickListener() {
//        public void onItemClick(AdapterView parent, View v, int position, long id) {
//            Context context = getApplicationContext();
//            EditText activate = v.findViewById(R.id.name_edit);
//            activate.selectAll();
//        }
//    };

}