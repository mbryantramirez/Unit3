package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.pursuit.unit_03_assessment.Model.PlanetName;
import org.pursuit.unit_03_assessment.R;
import org.pursuit.unit_03_assessment.controller.Adapter;
import org.pursuit.unit_03_assessment.network.PlanetService;
import org.pursuit.unit_03_assessment.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private EditText emailView;
    private EditText passwordView;
    private CheckBox usernameCheckbox;
    private SharedPreferences login;
    RecyclerView recyclerView;
    private static final String TAG = "planet_all";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView = findViewById(R.id.activity_recycler);
        Retrofit retrofit = RetrofitSingleton.getInstance();
        PlanetService planetservice = retrofit.create(PlanetService.class);
        Call<PlanetName> planet = planetservice.getPlanetname();
        planet.enqueue(new Callback<PlanetName>() {
            @Override
            public void onResponse(Call<PlanetName> call, Response<PlanetName> response) {
                Log.d(TAG, "onResponse: " + response.body().getMessage());
                recyclerView.setAdapter(new Adapter(response.body().getMessage()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<PlanetName> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.toString());
            }
        });

        emailView = (EditText) findViewById(R.id.email_edittext);
        passwordView = (EditText) findViewById(R.id.password_edittext);
        usernameCheckbox = (CheckBox) findViewById(R.id.remember_username_checkbox);


        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        /*
        * TODO: add logic to set values to views:
        * TODO: 1. if there is a username value AND checkbox value in shared preferences - set the username EditText's value
        * to the username value from shared preferences, and set the checkbox's value to the checkbox value from shared preferences

         */
        SharedPreferences sharedPrefs = getSharedPreferences("sp_name", MODE_PRIVATE);
        //SharedPreferences.Editor ed;
        SharedPreferences.Editor editor = login.edit();

        if(!sharedPrefs.contains("username") && sharedPrefs.contains("checkbox")){
            editor.putString("username", emailView.getText().toString());
            editor.putString("checkbox", passwordView.getText().toString());
            editor = sharedPrefs.edit();

            editor.commit();
        }


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        } else {
            /*
             * TODO: Add logic to confirm that:
             * TODO: 2. the username matches the username stored in strings.xml and the password matches the password stored in strings.xml
             * TODO: 3. the checkbox is ticked - if both email and password in EditTexts match strings.xml, add username value and checkbox value to shared preferences
             * TODO: 4. the checkbox is NOT ticked - if it is not ticked, clear username in shared preferences
             * TODO: 5. if both email and password in EditTexts match strings.xml, move to RecyclerActivity
             */
            SharedPreferences.Editor editor = login.edit();

            if(emailView.equals("dummy_email") && passwordView.equals("dummy_password")){
                    if (usernameCheckbox.isChecked()) {
                        editor.putString("username", emailView.getText().toString());
                        editor.putString("password", passwordView.getText().toString());
                        editor.putBoolean("isChecked", usernameCheckbox.isChecked());
                        editor.commit();
                    } else {
                        editor.putBoolean("isChecked", usernameCheckbox.isChecked());
                        editor.commit();
                    }

                String checkUser = "user" + emailView.getText().toString();
                String checkPassword = "password" + passwordView.getText().toString();

                if (emailView.getText().toString().equalsIgnoreCase(login.getString(checkUser, null))
                        && passwordView.getText().toString().equals(login.getString(checkPassword, null))) {
                    Intent intent = new Intent(LoginActivity.this, RecyclerActivity.class);
                    startActivity(intent);
                }
            }
        };



        if (cancel) {
            focusView.requestFocus();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

