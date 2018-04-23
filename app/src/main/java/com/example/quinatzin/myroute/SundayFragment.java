package com.example.quinatzin.myroute;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;


import com.example.quinatzin.myroute.data.FirebaseHelper;
import com.example.quinatzin.myroute.model.Customer;
import com.example.quinatzin.myroute.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by kench on 4/4/2018.
 */

public class SundayFragment extends Fragment {

    DatabaseReference db;
    FirebaseHelper helper;

    private static final String TAG = "SundayFragment";

    public static final String ANONYMOUS = "jim@hotmail,com";

    private ListView sundayListView;

    //private String mUsername;
    //private CustomerAdapter mCustomerAdapter;
    CustomerAdapter mCustomerAdapter;
    ArrayList<Customer> customer = new ArrayList<>();

    // FireBase instance variable
    private FirebaseDatabase mFirebaseDatabase;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sunday_route, container, false);

        sundayListView = view.findViewById(R.id.list);
        final View emptyView = view.findViewById(R.id.empty_view);
        sundayListView.setEmptyView(emptyView);

        // INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_LOCATION_ADDRESS);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        db = mFirebaseDatabase.getReference().child(Constants.FIREBASE_LOCATION_ADDRESS);

        //db = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_ADDRESS);
        // helper = new FirebaseHelper(db);
        // ADAPTER
        //mCustomerAdapter = new CustomerAdapter(getActivity(), helper.retrieve());
        //sundayListView.setAdapter(mCustomerAdapter);
        // customer.clear();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //String userId = dataSnapshot.getKey();
                //String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //String userId = dataSnapshot.getKey();
                //String userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                //String userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                //userId = userId.replace(".", ",");

                Log.d("USER LOG KEY", userId);

                DatabaseReference keyRef = FirebaseDatabase.getInstance()
                        .getReference()
                        .child(Constants.FIREBASE_LOCATION_ADDRESS)
                        .child(userId);

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            Customer customerVal = ds.getValue(Customer.class);
                            customer.add(customerVal);
                            mCustomerAdapter = new CustomerAdapter(getContext(), customer);
                            sundayListView.setAdapter(mCustomerAdapter);
                            mCustomerAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        sundayListView.setAdapter(mCustomerAdapter);
                        mCustomerAdapter.notifyDataSetChanged();
                    }
                };
                keyRef.addListenerForSingleValueEvent(valueEventListener);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        db.addListenerForSingleValueEvent(eventListener);

        /*db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //sundayListView.setAdapter(mCustomerAdapter);
                //mCustomerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //sundayListView.setAdapter(mCustomerAdapter);
                //mCustomerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                //sundayListView.setAdapter(mCustomerAdapter);
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
        });*/
        return view;
    }

}


