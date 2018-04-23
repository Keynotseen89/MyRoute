package com.example.quinatzin.myroute.data;

import android.util.Log;

import com.example.quinatzin.myroute.model.Customer;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by quinatzin on 4/11/2018.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved = null;
    ArrayList<Customer> customer = new ArrayList<>();
    //CustomerAdapter customer;// = new CustomerAdapter;
    ChildEventListener mChildEventListener;

    /**
     * constructor for FireBaseHelper
     *
     * @param db
     */
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    /**
     * saving information
     *
     * @param customer
     * @return
     */
   /* public Boolean save(Customer customer) {
        if (customer == null) {
            saved = false;
        } else {
            try {
                db.child("User").push().setValue(customer);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    */
    // IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        customer.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Customer customerValue = ds.getValue(Customer.class);
            //Log.d("TESTING DATA", customerValue.getFirstName() + " " + customerValue.getAddress());
            customer.add(customerValue);
        }
    }

    private void fetchDataLogin(DataSnapshot dataSnapshot){

    }

    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS

    public ArrayList<Customer> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                  fetchData(dataSnapshot);
               /* Customer customerValue = dataSnapshot.getValue(Customer.class);
                customer.add(customerValue);
                */
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                 fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return customer;
    }
}
