package com.example.mks.uublooddonatemanagementsystem.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mks.uublooddonatemanagementsystem.R;
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

/**
 * Created by mks on 7/24/2017.
 */

public class profileFragment extends Fragment implements View.OnClickListener {
    private Button btUpdate;
    private EditText nameEt,emailEt,passwordEt,genderEt,bloodgroupEt,cityEt,contactNOEt;
    private RadioGroup radioGender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        btUpdate=(Button) view.findViewById(R.id.btSignupScreenU);
        nameEt=(EditText) view.findViewById(R.id.etNameU);
        emailEt=(EditText) view.findViewById(R.id.etemailSignupU);
        bloodgroupEt=(EditText) view.findViewById(R.id.etBloodgroupU);
        cityEt=(EditText) view.findViewById(R.id.etCityU);
        passwordEt=(EditText) view.findViewById(R.id.etPasswordSignupU);
        contactNOEt=(EditText) view.findViewById(R.id.etContactnoU);
        radioGender=(RadioGroup)view.findViewById(R.id.radioGenderU);

        btUpdate.setOnClickListener(this);
        User user= SharedPrefManager.getInstance(getActivity()).getUser();
        nameEt.setText(user.getName());
        emailEt.setText(user.getEmail());
        passwordEt.setText("000");
        if (user.getGender().equalsIgnoreCase("male")) {
            radioGender.check(R.id.radioMale);
        } else {
            radioGender.check(R.id.radioFemale);
        }

    }
    private void updateUser(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        final RadioButton radioSex = (RadioButton) getActivity().findViewById(radioGender.getCheckedRadioButtonId());

        String name=nameEt.getText().toString().trim();
        String email=emailEt.getText().toString().trim();
        String password=passwordEt.getText().toString().trim();
        String gender=radioSex.getText().toString();
        String blodgroup=bloodgroupEt.getText().toString().trim();
        String city=cityEt.getText().toString().trim();
        String contactno=contactNOEt.getText().toString().trim();
        //String gender = radioSex.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        User user = new User(SharedPrefManager.getInstance(getActivity()).getUser().getId(), name, email, password, gender,blodgroup,city,contactno);

        Call<Result> call = service.updateUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getBloodGroup(),
                user.getCity(),
                user.getContactno()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getActivity()).userLogin(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
if(view==btUpdate){
    updateUser();
}
    }
}
