package com.development.daycare.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.development.daycare.views.activity.login.LoginScreen;

import java.util.HashMap;

public class SessionManager {
    //Shared Preferences
    SharedPreferences sharedPreferences;
    //Editor for shared preference
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "DayCare";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERID = "userId";
    public static final String KEY_USERTYPE="userType";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_LOGIN_TYPE = "loginType";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email,String userId,String userType,String phone,String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_USERID,userId);
        editor.putString(KEY_USERTYPE,userType);
        editor.putString(KEY_PHONE,phone);
        editor.putString(KEY_TOKEN,token);
       /* editor.putString(KEY_IMAGE,image);
        editor.putString(KEY_LOGIN_TYPE,login_type);*/

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public boolean checkLogin(){
        // Check login status

        return isLoggedIn();
      /*  if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            _context.startActivity(i);
        }*/

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        user.put(KEY_USERID, sharedPreferences.getString(KEY_USERID, null));
        user.put(KEY_USERTYPE, sharedPreferences.getString(KEY_USERTYPE, null));
        user.put(KEY_PHONE, sharedPreferences.getString(KEY_PHONE, null));
        user.put(KEY_TOKEN, sharedPreferences.getString(KEY_TOKEN, null));
       /* user.put(KEY_IMAGE, sharedPreferences.getString(KEY_IMAGE, null));
        user.put(KEY_LOGIN_TYPE,sharedPreferences.getString(KEY_LOGIN_TYPE,null));*/

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginScreen.class);
        // Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }


    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


}
