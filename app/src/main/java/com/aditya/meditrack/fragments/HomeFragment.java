package com.aditya.meditrack.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseFragment;
import com.aditya.meditrack.models.User;
import com.aditya.meditrack.uicomponents.PagerTabWidget;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private User user;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private OnFragmentInteractionListener mListener;
    private PagerTabWidget tabWidget;
    private ViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView= inflater.inflate(R.layout.fragment_home, container, false);
        initTab(itemView);
        return itemView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void initTab(@NonNull View itemView) {
        tabWidget = (PagerTabWidget) itemView.findViewById(R.id.home_tabWidget);
        tabWidget.setDividerInvisible();
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_action_bar, null);

        tabWidget.addTab(LayoutInflater.from(getContext()).inflate(R.layout.bottom_bar_contact, null));
        tabWidget.addTab(LayoutInflater.from(getContext()).inflate(R.layout.bottom_bar_engage, null));
        tabWidget.addTab(LayoutInflater.from(getContext()).inflate(R.layout.bottom_bar_job, null));

        viewPager = (ViewPager) itemView.findViewById(R.id.main_viewPager);
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(homePagerAdapter);

        tabWidget.setmViewPager(viewPager);
        tabWidget.setmOnTabSelectedListener(new PagerTabWidget.OnTabSelectedListener() {
            @Override
            public void onSelected(List<View> tabViews, int position) {
//                Toast.makeText(getContext(), "tab" + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {

        int TABS_COUNT=3;
        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
//                    fragment= TodayMedicineFragment.newInstance();
                    return TodayMedicineFragment.newInstance();
                case 1:
//                    fragment= DailyMedicineFragment.newInstance();
                    return DailyMedicineFragment.newInstance();
                case 2:
//                    fragment= AccountsFragment.newInstance();
                    return AccountsFragment.newInstance();
                default:
//                    fragment= TodayMedicineFragment.newInstance();
                    return TodayMedicineFragment.newInstance();
            }
//            return fragment;
        }

        @Override
        public int getCount() {
            return TABS_COUNT;
        }
    }

    public void openFragment(@NonNull Fragment fragment){
        fragmentManager=getChildFragmentManager();
        fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
