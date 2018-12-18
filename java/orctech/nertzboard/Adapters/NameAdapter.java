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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import orctech.nertzboard.R;

/**
 * Created by justinjlee99 on 5/11/2018.
 */

public class NameAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> names;

    public NameAdapter(@NonNull Context context,
                       @NonNull ArrayList<String> objects) {
        names = objects;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public NameAdapter(Context context, ArrayList<String> items) {
//
//    }

    //1
    @Override
    public int getCount() {
        return names.size();
    }

    //2
    @Override
    public String getItem(int position) {
        return names.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        EditText nameField;
        TextView teamNum;
        Button addOrDeleteButton;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_name, null);
            holder.nameField = (EditText) convertView
                    .findViewById(R.id.name_edit);
            convertView.setTag(holder);

            String info = "Team #" + (position + 1) + ":";
            holder.teamNum = (TextView) convertView.findViewById(R.id.team_number);
            holder.teamNum.setText(info);
//            holder.addOrDeleteButton = (Button) convertView.findViewById(R.id.buttonAdd);
//
//            holder.captionEditText.setFocusable(true);
//            holder.captionEditText.requestFocus();
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        holder.nameField.setText(names.get(position));
        holder.nameField.setId(position);

        //we need to update adapter once we finish with editing
        holder.nameField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                names.set(textView.getId(), textView.getText().toString());
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
        holder.nameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                final EditText nameText = (EditText) v;
                if (!hasFocus) {
                    final int position = v.getId();
                    names.set(position, nameText.getText().toString());
                }
                else {
                    ((EditText) v).selectAll();
                }
            }
        });


        return convertView;
    }
}