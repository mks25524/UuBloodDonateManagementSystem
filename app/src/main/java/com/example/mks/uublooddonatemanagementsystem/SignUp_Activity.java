package com.example.mks.uublooddonatemanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mks.uublooddonatemanagementsystem.api.APIService;
import com.example.mks.uublooddonatemanagementsystem.api.APIUrl;
import com.example.mks.uublooddonatemanagementsystem.helper.SharedPrefManager;
import com.example.mks.uublooddonatemanagementsystem.model.Result;
import com.example.mks.uublooddonatemanagementsystem.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp;
    private EditText nameEt,emailEt,passwordEt ,bloodgroupEt,cityEt,contactNOEt;
    private RadioGroup radioGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        btnSignUp= (Button) findViewById(R.id.btSignupScreen);
        nameEt= (EditText) findViewById(R.id.etName);
        emailEt= (EditText) findViewById(R.id.etemailSignup);
        passwordEt= (EditText) findViewById(R.id.etPasswordSignup);
        radioGender= (RadioGroup) findViewById(R.id.radioGender);
        bloodgroupEt= (EditText) findViewById(R.id.etBloodgroup);
        cityEt= (EditText) findViewById(R.id.etCity);
        contactNOEt= (EditText) findViewById(R.id.etContactno);
        btnSignUp.setOnClickListener(this);

    }
    private void userSignUp(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        //getting the user values
        final RadioButton radioSex = (RadioButton) findViewById(radioGender.getCheckedRadioButtonId());

        String name=nameEt.getText().toString().trim();
        String email=emailEt.getText().toString().trim();
        String password=passwordEt.getText().toString().trim();
        String gender=radioSex.getText().toString();
        String blodgroup=bloodgroupEt.getText().toString().trim();
        String city=cityEt.getText().toString().trim();
        String contactno=contactNOEt.getText().toString().trim();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        User user=new User(name,email,password,gender,blodgroup,city,contactno);

        //call
        Call<Result> call=service.createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getBloodGroup(),
                user.getCity(),
                user.getContactno()
        );
        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    //starting profile activity
                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
                    startActivity(new Intent(getApplicationContext(), Home_Screen_Activity.class));
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

        if(view==btnSignUp){
            userSignUp();
        }
    }
}
