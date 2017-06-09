package com.aditya.meditrack.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.aditya.meditrack.MedicineAdapter;
import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseAppCompat;
import com.aditya.meditrack.constants.AppConstant;
import com.aditya.meditrack.models.Medicine;
import com.aditya.meditrack.preferences.DBHelper;
import com.aditya.meditrack.uicomponents.ItemClickSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends BaseAppCompat {

    private ActionBar actionBar;
    private DBHelper dbHelper;
    private MedicineAdapter medicineAdapter;
    private List<Medicine> medicines;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText searchMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if(getSupportActionBar()!=null){
            actionBar=getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Search Medicine");
        }
        dbHelper=new DBHelper(getApplicationContext());
        medicines=new ArrayList<>();
        searchMedicine=(EditText)findViewById(R.id.search_box);
        recyclerView=(RecyclerView)findViewById(R.id.medicines);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        medicines=new ArrayList<>();
        medicineAdapter=new MedicineAdapter(getApplicationContext(),medicines);
        getMedicines();
        recyclerView.setAdapter(medicineAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                openMedicineActivity(medicines.get(position));
            }
        });
        addTextListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                if (filteredList.size() > 0) {
                    medicineAdapter = new MedicineAdapter(getApplicationContext(), filteredList);
                    recyclerView.setAdapter(medicineAdapter);
                    medicineAdapter.notifyDataSetChanged();  // data set changed
                }
                searchMedicine.setImeActionLabel(query, KeyEvent.KEYCODE_ENTER);
                if (medicines.size() <= 0) {
                    medicines = new ArrayList<>();
                    medicineAdapter = new MedicineAdapter(getApplicationContext(), medicines);
                    getMedicines();
                    recyclerView.setAdapter(medicineAdapter);
                    medicineAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void openMedicineActivity(@NonNull Medicine medicine){
        Intent intent=new Intent(getApplicationContext(),MedicineActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable(AppConstant.MEDICINE_SENT,medicine);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
