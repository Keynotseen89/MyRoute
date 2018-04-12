package com.example.quinatzin.myroute;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kench on 4/9/2018.
 */

public class CustomerDetail extends AppCompatActivity {

    private TextView textViewPhone;
    private TextView textViewEmail;
    private TextView textViewAddress;
    private TextView textViewPrice;
    private TextView textViewNote;
    //Intent intent = getIntent();
    String phoneString;
    String emailString;
    String addressString;
    String priceString;
    String noteString;

    private Uri mCurrentData;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        mCurrentData = intent.getData();

        textViewPhone = findViewById(R.id.textPhoneID);
        textViewEmail = findViewById(R.id.textEmail_ID);
        textViewAddress = findViewById(R.id.textAddressID);
        textViewPrice = findViewById(R.id.textPriceID);
        textViewNote = findViewById(R.id.noteTextID);

        phoneString = intent.getExtras().getString("PHONE_KEY");
        emailString = intent.getExtras().getString("EMAIL_KEY");
        addressString = intent.getExtras().getString("ADDRESS_KEY");
        priceString = intent.getExtras().getString("PRICE_KEY");
        noteString = intent.getExtras().getString("NOTES_KEY");

        textViewPhone.setText(phoneString);
        textViewEmail.setText(emailString);
        textViewAddress.setText(addressString);
        textViewPrice.setText(priceString);
        textViewNote.setText(noteString);

    }

    /**
     * inflate menu into AddEditCustomer class toolbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
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
            case R.id.edit_data:
                //Log.v("VaccineHistoryFragment.class", "Insert");
                openEditData();
                return true;
            case R.id.delete_data:
                return true;
        }
        //return super.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    private void openEditData(){
        Intent inputIntent = new Intent(CustomerDetail.this, AddEditCustomer.class);
        inputIntent.putExtra("PHONE_KEY", phoneString);
        inputIntent.putExtra("EMAIL_KEY", emailString);
        inputIntent.putExtra("ADDRESS_KEY", addressString);
        inputIntent.putExtra("PRICE_KEY", priceString);
        inputIntent.putExtra("NOTES_KEY", noteString);
        startActivity(inputIntent);
        //addressString = intent.getExtras().getString("ADDRESS_KEY");
        //priceString = intent.getExtras().getString("PRICE_KEY");
        //noteString = intent.getExtras().getString("NOTES_KEY");
    }

    /*private void openEditData() {
        //Intent intent = new Intent(AddEditCustomer.class);
        Intent intent = new Intent(CustomerDetail.this, AddEditCustomer.class);
        //Intent intent = new Intent(context, CustomerDetail.class);
        intent.putExtra("LAST_NAME_KEY", false);
        intent.putExtra("FIRST_NAME_KEY", details[1]);
        intent.putExtra("PHONE_KEY", details[2]);
        intent.putExtra("EMAIL_KEY", details[3]);
        intent.putExtra("PRICE_KEY", details[4]);
        intent.putExtra("DAY_KEY", details[5]);
        intent.putExtra("ADDRESS_KEY", details[6]);
        intent.putExtra("NOTES_KEY", details[7]);
        startActivity(intent);
    }*/

}
