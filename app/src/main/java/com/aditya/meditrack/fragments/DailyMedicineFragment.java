package com.aditya.meditrack.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class DailyMedicineFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;
    private User user;
    private DBHelper dbHelper;
    private MedicineAdapter medicineAdapter;
    private List<Medicine> medicines;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText searchMedicine;

    public DailyMedicineFragment() {
        // Required empty public constructor
    }

   public static DailyMedicineFragment newInstance() {
        DailyMedicineFragment fragment = new DailyMedicineFragment();
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
        View view= inflater.inflate(R.layout.fragment_daily_medicine, container, false);
        dbHelper=new DBHelper(getContext());
        medicines=new ArrayList<>();
        searchMedicine=(EditText)view.findViewById(R.id.search_box);
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
        addTextListener();
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
        medicines.addAll(dbHelper.getAllMedicine());
        medicineAdapter.notifyDataSetChanged();
    }

    public void addTextListener() {

        searchMedicine.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                final List<Medicine> filteredList = new ArrayList<>();
                for (int i = 0; i < medicines.size(); i++) {
                    final String text = medicines.get(i).getName().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(medicines.get(i));
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                if (filteredList.size() > 0) {
                    medicineAdapter = new MedicineAdapter(getContext(), filteredList);
                    recyclerView.setAdapter(medicineAdapter);
                    medicineAdapter.notifyDataSetChanged();  // data set changed
                }
                searchMedicine.setImeActionLabel(query, KeyEvent.KEYCODE_ENTER);
                if (medicines.size() <= 0) {
                    medicines = new ArrayList<>();
                    medicineAdapter = new MedicineAdapter(getContext(), medicines);
                    getMedicines();
                    recyclerView.setAdapter(medicineAdapter);
                    medicineAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
