package com.example.quinatzin.myroute;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by quinatzin on 4/9/2018.
 */

public class AddEditCustomer extends AppCompatActivity {

    private String mUsername;
    // EditText to read Last name
    private EditText mLastName;
    // EditText to read First name
    private EditText mFirstName;
    // EditText to read PhoneNumber
    private EditText mPhoneNumber;
    // EditText to read Email
    private EditText mEmail;
    // EditText to read Price
    private EditText mPrice;
    // EditText to read Route day
    private EditText mRouteDay;
    // EditText to read Address
    private EditText mAddress;
    // EditText to read Notes
    private EditText mNotes;


    String lastNameString, firstNameString, phoneString, emailString, priceString,
            dayString, addressString, notesString;

    private static final String TAG = "AddEditCustomer";

    public static final String ANONYMOUS = "anonymous";
    // Firebase instance variable
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private boolean isEmpty = true;
    private Uri mCurrentData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edit);

        //Set up toolbar for cancel and save
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //mCurrentData = intent.getData();

        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("User");

        // Set values to Empty text field by their ID
        mLastName = findViewById(R.id.editTextLastName);
        mFirstName = findViewById(R.id.editTextName);
        mPhoneNumber = findViewById(R.id.editTextPhone);
        mEmail = findViewById(R.id.editTextEmail);
        mPrice = findViewById(R.id.editTextPrice);
        mRouteDay = findViewById(R.id.editRouteDay);
        mAddress = findViewById(R.id.editTextAddress);
        mNotes = findViewById(R.id.editNoteId);


        if ( getIntent().getExtras() != null ) {
            phoneString = intent.getExtras().getString("PHONE_KEY");
            emailString = intent.getExtras().getString("EMAIL_KEY");
            addressString = intent.getExtras().getString("ADDRESS_KEY");
            priceString = intent.getExtras().getString("PRICE_KEY");
            notesString = intent.getExtras().getString("NOTES_KEY");
            populateInformation();
        }
    }

    private void populateInformation() {

        //   String lastNameString = intent.getExtras().getString("LAST_NAME");
        //  String firstNameString = intent.getExtras().getString("FIRST_NAME");
        /*String phoneString = intent.getExtras().getString("PHONE");
        String emailString = intent.getExtras().getString("EMAIL");
        String addressString = intent.getExtras().getString("ADDRESS");
        String priceString = intent.getExtras().getString("PRICE");
        String noteString = intent.getExtras().getString("NOTES");
        //String dayString = intent.getExtras().getString("DAY");
        */
        //mLastName.setText(lastNameString);
        //mFirstName.setText(firstNameString);
        mPhoneNumber.setText(phoneString);
        mEmail.setText(emailString);
        mPhoneNumber.setText(priceString);
        //mRouteDay.setText(dayString);
        mAddress.setText(addressString);
        mNotes.setText(notesString);
    }

    /**
     * inflate menu into AddEditCustomer class toolbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_detail, menu);
        return true;
    }

    /**
     * Selectable dropdown list from menu bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.insert_dummy) {
        //  return true;
        //}
        switch (item.getItemId()) {
            case R.id.action_save_id:
                //Log.v("VaccineHistoryFragment.class", "Insert");
                finish();
                saveItem();
                return true;
            case R.id.action_cancel_id:
                return true;
        }
        //return super.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    //Discard changes dialog method
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        //Create an AlertDialog.Builder and set the message, and click listeners
        // for the position and nagative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Unsaved Changes");
        builder.setPositiveButton("Discard", discardButtonClickListener);
        builder.setNegativeButton("Keep editing", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        //Create and show AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * save data into Firebase
     */
    private void saveItem() {

        String lastNameString = mLastName.getText().toString().trim();
        String firstNameString = mFirstName.getText().toString().trim();
        String phoneString = mPhoneNumber.getText().toString().trim();
        String emailString = mEmail.getText().toString().trim();
        String priceString = mPrice.getText().toString().trim();
        String routeDayString = mRouteDay.getText().toString().trim();
        String addressString = mAddress.getText().toString().trim();
        String noteString = mNotes.getText().toString().trim();


       /* ContentValues values = new ContentValues();
        values.put()
        */
        Customer customerInformation = new Customer(mUsername, lastNameString, firstNameString, phoneString, emailString,
                priceString, routeDayString, addressString, noteString);
        mDatabaseReference.push().setValue(customerInformation);

        Toast.makeText(this, " Saved ", Toast.LENGTH_LONG).show();
    }


}