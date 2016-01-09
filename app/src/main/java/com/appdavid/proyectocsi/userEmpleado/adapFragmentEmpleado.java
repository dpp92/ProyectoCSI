package com.appdavid.proyectocsi.userEmpleado;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dpp on 10/12/15.
 */
public class adapFragmentEmpleado extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public adapFragmentEmpleado(FragmentManager fm,List<Fragment>fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

