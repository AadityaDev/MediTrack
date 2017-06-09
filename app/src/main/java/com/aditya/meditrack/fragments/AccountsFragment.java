package com.aditya.meditrack.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.meditrack.activities.EditProfileActivity;
import com.aditya.meditrack.activities.MedicineActivity;
import com.aditya.meditrack.R;
import com.aditya.meditrack.activities.SOSContactActivity;
import com.aditya.meditrack.activities.SearchActivity;
import com.aditya.meditrack.base.BaseFragment;
import com.aditya.meditrack.models.User;
import com.aditya.meditrack.preferences.UserStore;

public class AccountsFragment extends BaseFragment {

    private TextView name;
    private TextView age;
    private TextView email;
    private TextView mobile;
    private ImageView image;
    private TextView imageText;
    private ImageView editSOSContact;
    private TextView sosName;
    private TextView sosMobile;
    private TextView sosImageText;
    private ImageView sosImage;
    private ImageView editProfile;
    private FloatingActionButton addMedicine;
    private CardView medicineSettings;
    private User user;
    private UserStore userStore;
    private OnFragmentInteractionListener mListener;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public AccountsFragment() {
        // Required empty public constructor
    }

    public static AccountsFragment newInstance() {
        AccountsFragment fragment = new AccountsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        userStore=new UserStore(getContext());
        View view=inflater.inflate(R.layout.fragment_accounts, container, false);
        name=(TextView)view.findViewById(R.id.user_name);
        email=(TextView)view.findViewById(R.id.user_email);
        age=(TextView)view.findViewById(R.id.user_age);
        mobile=(TextView)view.findViewById(R.id.user_mobile);
        image=(ImageView)view.findViewById(R.id.user_image);
        imageText=(TextView) view.findViewById(R.id.user_image_text);
        editSOSContact=(ImageView)view.findViewById(R.id.sos_edit_profile);
        editSOSContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SOSContactActivity.class);
                startActivity(intent);
            }
        });
        sosName=(TextView)view.findViewById(R.id.sos_name);
        sosMobile=(TextView)view.findViewById(R.id.sos_mobile_text);
        sosImage=(ImageView)view.findViewById(R.id.sos_image);
        sosImageText=(TextView) view.findViewById(R.id.sos_image_text);
        addMedicine=(FloatingActionButton)view.findViewById(R.id.add_medicine);
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MedicineActivity.class);
                startActivity(intent);
            }
        });
        editProfile=(ImageView)view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        medicineSettings=(CardView)view.findViewById(R.id.edit_medicine);
        medicineSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
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

    public void openFragment(@NonNull Fragment fragment){
        fragmentManager=getChildFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void fillData(){
        name.setText(UserStore.getUserName());
        age.setText(UserStore.getAge());
        mobile.setText(UserStore.getUserMobile());
        email.setText(UserStore.getUserEmail());
        imageText.setText(UserStore.getUserName().toUpperCase());
    }
}
