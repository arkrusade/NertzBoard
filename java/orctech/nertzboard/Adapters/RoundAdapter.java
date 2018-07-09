package orctech.nertzboard.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import orctech.nertzboard.R;

public class RoundAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Integer> scores;

    public RoundAdapter(@NonNull Context context, ArrayList<Integer> scores) {
        this.scores = scores;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    //2
    @Override
    public Integer getItem(int position) {
        return scores.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        EditText scoreEdit;
        TextView teamName;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_round, null);
            holder.scoreEdit = (EditText) convertView
                    .findViewById(R.id.round_score_edit);

            String info = "Score Team #" + (position + 1) + ":";
            holder.teamName = (TextView) convertView.findViewById(R.id.team_name_round);
            holder.teamName.setText(info);

            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        String scoreString = scores.get(position) + "";
        holder.scoreEdit.setText(scoreString);
        holder.scoreEdit.setId(position);

        //we need to update adapter once we finish with editing
        holder.scoreEdit.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                scores.set(textView.getId(), Integer.parseInt(textView.getText().toString()));
                if (i == EditorInfo.IME_ACTION_DONE) {
                    textView.clearFocus();
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(
                            Context.INPUT_METHOD_SERVICE);

                    if (imm != null) {
                        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
        holder.scoreEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                final EditText nameText = (EditText) v;
                if (!hasFocus) {
                    final int position = v.getId();
                    scores.set(position, Integer.parseInt(nameText.getText().toString()));
                }
                else {
                    ((EditText) v).selectAll();
                }
            }
        });


        return convertView;
    }

}
