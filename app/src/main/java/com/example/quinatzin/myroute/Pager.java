package com.example.quinatzin.myroute;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Quinatzin on 4/4/2018.
 */

public class Pager extends FragmentStatePagerAdapter {

    //Day Tabs
    int tabCount;

    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                SundayFragment sundayTab = new SundayFragment();
                return sundayTab;
            case 1:
                MondayFragment mondayTab = new MondayFragment();
                return mondayTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
