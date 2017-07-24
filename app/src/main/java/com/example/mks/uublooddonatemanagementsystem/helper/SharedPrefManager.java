package com.example.mks.uublooddonatemanagementsystem.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mks.uublooddonatemanagementsystem.model.User;

/**
 * Created by mks on 7/24/2017.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private static final String SHARED_PREF_NAME="MyPrefer";
    private static final String KEY_USER_ID="keyuserid";
    private static final String KEY_USER_NAME="keyusername";
    private static final String KEY_USER_EMAIL="keyuseremail";
    private static final String KEY_USER_GENDER="keyusergender";
    private static final String KEY_USER_BLOODGROUP="keyuserbloodgroup";
    private static final String KEY_USER_CITY="keyuserbloodcity";
    private static final String KEY_USER_CONTACTNO="keyuserbloodcontactno";

    private SharedPrefManager(Context context){
        mCtx=context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_GENDER, user.getGender());
        editor.putString(KEY_USER_BLOODGROUP, user.getBloodGroup());
        editor.putString(KEY_USER_CITY, user.getCity());
        editor.putString(KEY_USER_CONTACTNO, user.getContactno());
        editor.apply();
        return true;
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_EMAIL, null) != null)
            return true;
        return false;
    }
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_USER_ID, 0),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null),
                sharedPreferences.getString(KEY_USER_GENDER, null),
                sharedPreferences.getString(KEY_USER_BLOODGROUP, null),
                sharedPreferences.getString(KEY_USER_CITY, null),
                sharedPreferences.getString(KEY_USER_CONTACTNO, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
