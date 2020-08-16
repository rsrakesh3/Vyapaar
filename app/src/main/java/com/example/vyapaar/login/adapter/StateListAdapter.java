package com.example.vyapaar.login.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.vyapaar.R;
import com.example.vyapaar.common.model.State;
import com.example.vyapaar.common.model.StateList;

import java.util.ArrayList;
import java.util.List;

public class StateListAdapter implements SpinnerAdapter {

    private StateList list;
    private Context mContext;
    protected static final int EXTRA = 1;
    private List<String> district;

    public StateListAdapter(Context context, StateList stateList){
        list = stateList;
       /* State state = new State();
        state.setState("Select State");
        district = new ArrayList<>();
        state.setDistricts(district);
        list.getStates().add(0, state);*/
        mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
            TextView label = (TextView) convertView.findViewById(android.R.id.text1);
        if(position==0){
            label.setTextColor(Color.GRAY);
            label.setEnabled(false);
            label.setClickable(false);
            label.setText(list.getStates().get(position).getState());
        }else {
            label.setTextColor(Color.BLACK);
            label.setText(list.getStates().get(position).getState());
        }
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        int count = list.getStates().size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        return list.getStates().get((position));
    }

    @Override
    public long getItemId(int position) {
        //position >= EXTRA ? adapter.getItemId(position - EXTRA) : position - EXTRA;
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.state_list_item, parent, false);
        }
        TextView label = (TextView) convertView.findViewById(R.id.text_state);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        if(position==0){
            label.setHint("Select State");
            label.setTextColor(Color.GRAY);
            label.setEnabled(false);
            label.setClickable(false);
        }else {
            label.setText(list.getStates().get(position).getState());
        }

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
