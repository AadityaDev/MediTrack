package com.aditya.meditrack.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.meditrack.MedicineAdapter;
import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseFragment;
import com.aditya.meditrack.models.Medicine;
import com.aditya.meditrack.models.User;
import com.aditya.meditrack.preferences.DBHelper;
import com.aditya.meditrack.uicomponents.ItemClickSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodayMedicineFragment extends BaseFragment {

    private User user;
    private OnFragmentInteractionListener mListener;
    private DBHelper dbHelper;
    private MedicineAdapter medicineAdapter;
    private List<Medicine> medicines;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public TodayMedicineFragment() {
        // Required empty public constructor
    }

    public static TodayMedicineFragment newInstance() {
        TodayMedicineFragment fragment = new TodayMedicineFragment();
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
        View view= inflater.inflate(R.layout.fragment_today_medicine, container, false);
        dbHelper=new DBHelper(getContext());
        medicines=new ArrayList<>();
        recyclerView=(RecyclerView)view.findViewById(R.id.medicines);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        medicines=new ArrayList<>();
        medicineAdapter=new MedicineAdapter(getContext(),medicines);
        getMedicines();
        recyclerView.setAdapter(medicineAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

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

    private void getMedicines(){
        medicines.addAll(dbHelper.getMedicinesForToday());
        medicineAdapter.notifyDataSetChanged();
    }
}
