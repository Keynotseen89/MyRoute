package com.example.quinatzin.myroute;

import android.app.ListFragment;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quinatzin.myroute.data.FirebaseHelper;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kench on 4/4/2018.
 */

public class SundayFragment extends Fragment {

    DatabaseReference db;
    FirebaseHelper helper;

    private static final String TAG = "SundayFragment";

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKKER = 2;

    private ListView sundayListView;

    //private String mUsername;
    //private CustomerAdapter mCustomerAdapter;
    CustomerAdapter mCustomerAdapter;
    // FireBase instance variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sunday_route, container, false);

        sundayListView = view.findViewById(R.id.list);
        View emptyView = view.findViewById(R.id.empty_view);
        sundayListView.setEmptyView(emptyView) ;
        // INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        // ADAPTER
        mCustomerAdapter = new CustomerAdapter(getContext(), helper.retrieve());
        //sundayListView.setAdapter(mCustomerAdapter);


        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sundayListView.setAdapter(mCustomerAdapter);
               // mCustomerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                sundayListView.setAdapter(mCustomerAdapter);
               // mCustomerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                sundayListView.setAdapter(mCustomerAdapter);
                //lv.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                ///lv.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // lv.setAdapter(adapter);
               // adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

}


