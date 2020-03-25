package com.example.together_demo1.Fragment.Bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.together_demo1.R;

import java.util.List;

public class BusListAdapter extends BaseExpandableListAdapter {
    private List<String> stationlist;
    private List<String> busList;
    private Context mcontext;
    private LayoutInflater inflater;

    public BusListAdapter(List<String> stationlist, List<String> busList, Context mcontext) {
        this.stationlist = stationlist;
        this.busList = busList;
        this.mcontext = mcontext;
        this.inflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getGroupCount() {
        return stationlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return busList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return stationlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return busList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.exlist_item_station, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.tv_item_station.setText(stationlist.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.exlist_item_bus, parent,false);
        ViewHolder2 viewHolder2 = new ViewHolder2(convertView);
        viewHolder2.tv_item_bus.setText(busList.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public static
    class ViewHolder {
        public View rootView;
        public TextView tv_item_station;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_item_station = (TextView) rootView.findViewById(R.id.tv_item_station);
        }

    }
    public static
    class ViewHolder2 {
        public View rootView;
        public TextView tv_item_bus;

        public ViewHolder2(View rootView) {
            this.rootView = rootView;
            this.tv_item_bus = (TextView) rootView.findViewById(R.id.tv_item_bus);
        }

    }
}
