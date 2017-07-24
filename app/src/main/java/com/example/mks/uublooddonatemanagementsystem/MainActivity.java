package com.example.mks.uublooddonatemanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mks.uublooddonatemanagementsystem.helper.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btSignIn,btSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //if user is already logged in openeing the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home_Screen_Activity.class));
        }
        btSignIn= (Button) findViewById(R.id.buttonSignIn);
        btSignUp= (Button) findViewById(R.id.buttonSignUp);
        btSignIn.setOnClickListener(this);
        btSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btSignIn){
            startActivity(new Intent(this,SignIn_Activity.class));
        }else if(view==btSignUp){
            startActivity(new Intent(this,SignUp_Activity.class));
        }
    }
}
