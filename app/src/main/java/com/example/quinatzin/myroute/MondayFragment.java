package com.example.quinatzin.myroute;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by quinatzin on 4/5/2018.
 */

public class MondayFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.sunday_route,container, false);
        ListView mondayListView = (ListView) view.findViewById(R.id.list);
        View emptyView = view.findViewById(R.id.empty_view);
        mondayListView.setEmptyView(emptyView);
        return view;
    }
}
