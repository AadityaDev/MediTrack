package com.aditya.meditrack.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.meditrack.R;
import com.aditya.meditrack.base.BaseAppCompat;
import com.aditya.meditrack.models.User;
import com.aditya.meditrack.preferences.UserStore;
import com.aditya.meditrack.utils.StringUtils;

public class EditProfileActivity extends BaseAppCompat {

    private ActionBar actionBar;
    private EditText name;
    private EditText age;
    private EditText email;
    private EditText mobile;
    private ImageView image;
    private TextView imageText;
    private CardView saveProfile;
    private UserStore userStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userStore=new UserStore(getApplicationContext());
        if(getSupportActionBar()!=null){
            actionBar=getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Edit Profile");
        }
        name=(EditText) findViewById(R.id.user_name);
        email=(EditText) findViewById(R.id.user_email);
        age=(EditText) findViewById(R.id.user_age);
        mobile=(EditText) findViewById(R.id.user_mobile);
        image=(ImageView)findViewById(R.id.user_image);
        imageText=(TextView) findViewById(R.id.user_image_text);
        saveProfile=(CardView)findViewById(R.id.save_pofile);
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!StringUtils.isNotEmptyOrNull(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter your name",Toast.LENGTH_SHORT).show();
                }else if(!StringUtils.isNotEmptyOrNull(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter your email",Toast.LENGTH_SHORT).show();
                }else if(!StringUtils.isNotEmptyOrNull(age.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter your age",Toast.LENGTH_SHORT).show();
                }else if(!StringUtils.isNotEmptyOrNull(mobile.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter your mobile",Toast.LENGTH_SHORT).show();
                }else {
                    UserStore.setUserName(name.getText().toString());
                    UserStore.setUserEmail(email.getText().toString());
                    UserStore.setAge(Integer.valueOf(age.getText().toString()));
                    UserStore.setUserMobile(mobile.getText().toString());
                    finish();
                }
            }
        });

        name.setText(UserStore.getUserName());
        age.setText(UserStore.getAge());
        mobile.setText(UserStore.getUserMobile());
        email.setText(UserStore.getUserEmail());
        imageText.setText(UserStore.getUserName().toUpperCase());
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
}
