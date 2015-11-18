package com.appdavid.proyectocsi.usersCliente;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by DAVID PUC POOT on 11/11/2015.
 */
public class adapFragment extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public adapFragment(FragmentManager fm,List<Fragment>fragments) {
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

