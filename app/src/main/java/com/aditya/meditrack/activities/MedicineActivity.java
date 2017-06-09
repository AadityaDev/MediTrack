package com.aditya.meditrack.activities;

import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseAppCompat;
import com.aditya.meditrack.constants.AppConstant;
import com.aditya.meditrack.constants.Priority;
import com.aditya.meditrack.models.Medicine;
import com.aditya.meditrack.models.User;
import com.aditya.meditrack.preferences.DBHelper;
import com.aditya.meditrack.utils.StringUtils;

import java.util.Date;
import java.util.GregorianCalendar;

public class MedicineActivity extends BaseAppCompat implements AdapterView.OnItemSelectedListener {

    private ActionBar actionBar;
    private Bundle bundle;
    private AutoCompleteTextView name;
    private DatePicker toDate;
    private DatePicker fromDate;
    private CardView addMedicine;
    private EditText dosage;
    private Spinner spinner;
    private int position=0;
    private ArrayAdapter<CharSequence> adapter;
    private Medicine medicine;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        if(getSupportActionBar()!=null){
            actionBar=getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Add Medicine");
        }
        medicine=new Medicine();
        dbHelper=new DBHelper(getApplicationContext());
        Date today=new Date();
        name=(AutoCompleteTextView)findViewById(R.id.medicine_name);
        toDate=(DatePicker)findViewById(R.id.medicine_to_date);
        toDate.setMinDate(today.getTime());
        fromDate=(DatePicker)findViewById(R.id.medicine_from_date);
        fromDate.setMinDate(today.getTime());
        spinner=(Spinner)findViewById(R.id.priority_spinner);
        adapter=ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        dosage=(EditText)findViewById(R.id.medicine_dosage);
        addMedicine=(CardView)findViewById(R.id.add_medicine);
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GregorianCalendar calendarBeg=new GregorianCalendar(fromDate.getYear(),
                        fromDate.getMonth(),fromDate.getDayOfMonth());
                Date begin=calendarBeg.getTime();
                GregorianCalendar calendarEnd=new GregorianCalendar(toDate.getYear(),
                        toDate.getMonth(),toDate.getDayOfMonth());
                Date end=calendarEnd.getTime();
                if((StringUtils.isNotEmptyOrNull(name.getText().toString()))&&(StringUtils.isNotEmptyOrNull(dosage.getText().toString()))){
                    medicine.setName(name.getText().toString());
                    medicine.setFrom(begin);
                    medicine.setTo(end);
                    medicine.setPriority(spinner.getItemAtPosition(position).toString());
                    medicine.setDosage(Integer.valueOf(dosage.getText().toString()));
                    dbHelper.insertMedicine(medicine);
                    dbHelper.getAllMedicine();
                    Log.d(getTAG(),"DB Size: "+dbHelper.getAllMedicine().size());
                    Toast.makeText(getApplicationContext(),"Medicine added!",Toast.LENGTH_LONG).show();
                    onBackPressed();
                }else{
                    Toast.makeText(getApplicationContext(),"Enter medicine name!",Toast.LENGTH_LONG).show();
                }
            }
        });

        bundle=getIntent().getExtras();
        if(bundle!=null){
            final Medicine medicineSent=bundle.getParcelable(AppConstant.MEDICINE_SENT);
            if(medicineSent!=null){
                if(StringUtils.isNotEmptyOrNull(medicineSent.getName())){
                    name.setText(medicineSent.getName());
                }
                dosage.setText(medicineSent.getDosage()+"");
            }
        }
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.isDaily:
                if (checked){
                    medicine.setDaily(true);
                    medicine.setWeekly(false);
                    medicine.setMonthly(false);
                }
                break;
            case R.id.isWeekly:
                if (checked){
                    medicine.setDaily(false);
                    medicine.setWeekly(true);
                    medicine.setMonthly(false);
                }
                break;
            case R.id.isMonthly:
                if (checked){
                    medicine.setDaily(false);
                    medicine.setWeekly(false);
                    medicine.setMonthly(true);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        position=0;
    }
}
