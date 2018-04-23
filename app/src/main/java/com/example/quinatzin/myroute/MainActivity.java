package com.example.quinatzin.myroute;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quinatzin.myroute.model.User;
import com.example.quinatzin.myroute.utils.Constants;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton fab;

    private String mUsername;
    // private static final String USER = "user";
    public static final String ANONYMOUS = "anonymous";

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    public static final int RC_SIGN_IN = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Choose authentication providers
        final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(Constants.FIREBASE_LOCATION_USERS);
        // Initialize Firebase components
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Day tabs ids
        mTabLayout = findViewById(R.id.dayTabs);
        mTabLayout.setupWithViewPager(mViewPager);

        //Floating Action Button
        fab = findViewById(R.id.floatingActionButton);

        // add the tab
        mTabLayout.addTab(mTabLayout.newTab().setText("Sun"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Mon"));
        //mTabLayout.addTab(mTabLayout.newTab().setText("Tue"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Setup  the ViewPager with the selection adapter.
        mViewPager = findViewById(R.id.container_main);
        Pager adapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*mViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    Log.v("Tab 1", "action goes here");
                    tabOneSelected = false;
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // opens windows to add new customer
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditCustomer.class);
                startActivity(intent);
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    String userEmail = user.getEmail();
                    userEmail = userEmail.replace(".", ",");
                    onSignedInInitialize(user.getDisplayName(), userEmail);

                } else {
                    // user is signed out
                    // Create and launch sign-in intent
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (requestCode == RESULT_OK) {
                // sign-in succeeded, set up the UI
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was cancelled by the user, finished the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    /**
     * Toolbar obtain insert dummy data
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout_id:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * A Placeholder fragment containing a simple view
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The Fragment argument representing the section number for this fragment
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        /**
         * Return a new instance of this fragment for the given section
         */
        public static PlaceholderFragment newInstance(int sectionNum) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNum);
            fragment.setArguments(args);

            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText("Hello World");

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    private void onSignedInInitialize(String username, String email) {
        mUsername = username;
        Log.d("LOG IN NAME ", mUsername);
        String userEmail = email;

        Toast.makeText(this, "Sign in as: " + mUsername, Toast.LENGTH_LONG).show();

        //Customer customer = new Customer(mUsername,null,null,null,userEmail,null,null,null,null);
        //mDatabaseReference = mDatabaseReference.child(userEmail);
        /*HashMap<String, Object> timestampJoined = new HashMap<>();
        timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        User userAccount = new User(mUsername, userEmail, timestampJoined,true);
        */
        User userAccount = new User(mUsername, userEmail);
        //getAccountEmail(userAccount);
        mDatabaseReference.child(userEmail).setValue(userAccount);

    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;

    }
}
