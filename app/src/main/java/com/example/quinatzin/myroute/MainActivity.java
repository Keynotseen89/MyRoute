package com.example.quinatzin.myroute;

import android.content.Intent;
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

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }
    /**
     * Toolbar obtain insert dummy data
     * @param menu
     * @return
     */
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vaccine, menu);
        return true;
    }*/


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
}
