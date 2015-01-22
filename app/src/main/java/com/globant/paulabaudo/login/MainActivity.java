package com.globant.paulabaudo.login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    EditText mUsernameEt;
    EditText mPasswordEt;
    Button mLoginBtn;
    Boolean mValidUser;
    Boolean mValidPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        mUsernameEt = (EditText) findViewById(R.id.edit_text_user);
        mPasswordEt = (EditText) findViewById(R.id.edit_text_password);
        mLoginBtn = (Button) findViewById(R.id.button_login);
        mValidUser = false;
        mValidPass = false;

        addWatchers();
        addButtonListener();
    }

    private void addButtonListener(){
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsernameEt.getText().toString().equals("myTest+fake@account.com") &&
                        mPasswordEt.getText().toString().equals("password")) {
                    Intent successIntent = new Intent(MainActivity.this, SuccessActivity.class);
                    startActivity(successIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addWatchers() {

        final Pattern emailPattern = Patterns.EMAIL_ADDRESS;

        mUsernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && emailPattern.matcher(s).matches()){
                    mValidUser = true;
                } else {
                    mValidUser = false;
                }
                readyToLogin();
            }
        });

        mPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)){
                    mValidPass = true;
                } else {
                    mValidPass = false;
                }
                readyToLogin();
            }
        });
    }

    private void readyToLogin(){
        if (mValidUser && mValidPass){
            mLoginBtn.setEnabled(true);
        } else {
            mLoginBtn.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
