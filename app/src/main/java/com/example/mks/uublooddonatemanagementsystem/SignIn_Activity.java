package com.example.mks.uublooddonatemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mks.uublooddonatemanagementsystem.api.APIService;
import com.example.mks.uublooddonatemanagementsystem.api.APIUrl;
import com.example.mks.uublooddonatemanagementsystem.helper.SharedPrefManager;
import com.example.mks.uublooddonatemanagementsystem.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button  btSignin;
    private EditText etEmail,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);
        btSignin= (Button) findViewById(R.id.btSignin);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btSignin.setOnClickListener(this);
    }
    private void userSignIn(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.userLogin(email, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), Home_Screen_Activity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View view) {

        if(view==btSignin){
            userSignIn();
        }
    }
}
