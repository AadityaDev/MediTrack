package com.aditya.meditrack.activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseAppCompat;
import com.aditya.meditrack.fragments.AccountsFragment;
import com.aditya.meditrack.fragments.DailyMedicineFragment;
import com.aditya.meditrack.fragments.HomeFragment;
import com.aditya.meditrack.fragments.TodayMedicineFragment;

public class MainActivity extends BaseAppCompat implements HomeFragment.OnFragmentInteractionListener,
        AccountsFragment.OnFragmentInteractionListener,DailyMedicineFragment.OnFragmentInteractionListener,
        TodayMedicineFragment.OnFragmentInteractionListener{

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFragment();
    }

    private void openFragment(){
        fragment=new HomeFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
