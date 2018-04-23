package com.example.quinatzin.myroute;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quinatzin.myroute.utils.Constants;

import java.util.ArrayList;

/**
 * Created by kench on 4/9/2018.
 */

public class CustomerDetail extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 0;

    Uri actualUri;

    private TextView textViewPhone;
    private TextView textViewEmail;
    private TextView textViewAddress;
    private TextView textViewPrice;
    private TextView textViewNote;

    ImageButton imageBtn;
    ImageView imageViewDetail;
    //Toolbar toolbar;
    //Intent intent = getIntent();
    String firstNameString;
    String lastNameString;
    String phoneString;
    String emailString;
    String addressString;
    String priceString;
    String noteString;

    Spinner daySpinner;

    private Uri mCurrentData;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("Quin title");



        Intent intent = getIntent();
        mCurrentData = intent.getData();

        imageBtn = findViewById(R.id.imageButton);
        imageViewDetail = findViewById(R.id.imageViewDetal);

        textViewPhone = findViewById(R.id.textPhoneID);
        textViewEmail = findViewById(R.id.textEmail_ID);
        textViewAddress = findViewById(R.id.textAddressID);
        textViewPrice = findViewById(R.id.textPriceID);
        textViewNote = findViewById(R.id.noteTextID);

        //intent.putExtra("LAST_NAME_KEY", details[0]);
        //intent.putExtra("FIRST_NAME_KEY", details[1]);
        firstNameString = intent.getExtras().getString("LAST_NAME_KEY");
        lastNameString = intent.getExtras().getString("FIRST_NAME_KEY");

        // set toolbar title with customers name
        toolbar.setTitle(firstNameString + " " + lastNameString);
        setSupportActionBar(toolbar);

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

        // open image selector to upload new image
        // if valid
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryToOpenImageSelector();
            }
        });

    }

    /**
     * Method to try to open image selector
     */
    private void tryToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
        openImageSelector();
    }

    /**
     * Method to open phone gallery to upload image
     */
    private void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    /**
     * Check if request is empty if not then go ahead and
     * openImageSelector method
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                    // permission was granted
                }
            }
        }
    }

    /**
     * add value to image uploade
     * @param requestCode
     * @param resultCode
     * @param resultData
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code READ_REQUEST_CODE.
        // If the request code seen here doesn't match, it's the response to some other intent,
        // and the below code shouldn't run at all.

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.  Pull that uri using "resultData.getData()"

            if (resultData != null) {
                actualUri = resultData.getData();
                imageViewDetail.setImageURI(actualUri);
                imageViewDetail.invalidate();
                //toolbar.setBackground(actualUri);
                //toolbar.invalidate();
                //imageView.setImageURI(actualUri);
                //imageView.invalidate();
            }
        }
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
