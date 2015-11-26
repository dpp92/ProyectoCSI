package com.appdavid.proyectocsi.userAdmin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dpp on 26/11/15.
 */
public class adapFragmentAdmin extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public adapFragmentAdmin(FragmentManager fm,List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
