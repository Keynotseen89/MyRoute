package com.example.quinatzin.myroute.data;

import com.example.quinatzin.myroute.Customer;
import com.example.quinatzin.myroute.CustomerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    public Boolean save(Customer customer) {
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

    // IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        customer.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Customer customerValue = ds.getValue(Customer.class);
            customer.add(customerValue);
        }
    }


    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS

    public ArrayList<Customer> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Customer customer = dataSnapshot.getValue(Customer.class);
                fetchData(dataSnapshot);
               /* Customer customerValue = dataSnapshot.getValue(Customer.class);
                System.out.println("Last Name: " + customerValue.getLastName());
                System.out.println("First Name: " + customerValue.getFirstName());
                System.out.println("Phone: " + customerValue.getPhoneNumber());
                System.out.println("Email: " + customerValue.getEmail());
                System.out.println("Price: " + customerValue.getPrice());
                System.out.println("Day: " + customerValue.getDay());
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
