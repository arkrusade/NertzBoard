package orctech.nertzboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import orctech.nertzboard.R;
import orctech.nertzboard.Models.Team;

/**
 * Created by justinjlee99 on 5/11/2018.
 */

public class TeamAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Team> mDataSource;

    public TeamAdapter(Context context, ArrayList<Team> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_team, parent, false);

        // Get title element
        TextView nameTextView =
                (TextView) rowView.findViewById(R.id.team_name);

// Get description element
        TextView scoreTextView =
                (TextView) rowView.findViewById(R.id.team_score);

        Team a = (Team) getItem(position);

        nameTextView.setText(a.getName());
        String num = ""+a.getScore();
        scoreTextView.setText(num);
        //descTextView.setText(a.getDescription());

        return rowView;
    }
}