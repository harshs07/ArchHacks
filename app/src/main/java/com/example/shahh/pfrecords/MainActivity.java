package com.example.shahh.pfrecords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();

    }

    protected void init() {

    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.btn1) {
            EditText username = (EditText) findViewById(R.id.username1);
            String user_name = username.getText().toString();
            EditText password = (EditText) findViewById(R.id.pass1);
            String pass = password.getText().toString();

            EditText newusername = (EditText) findViewById(R.id.username1);
            username.setText("");
            EditText newpassword = (EditText) findViewById(R.id.pass1);
            password.setText("");

            String chkPassword = helper.searchPass(user_name);

            if(chkPassword.equals("Not Found")){
                Toast.makeText(MainActivity.this,"Username Not Found",Toast.LENGTH_SHORT).show();
            }

            else if (chkPassword.equals(pass)) {
                Intent i = new Intent(MainActivity.this, Display.class);
                i.putExtra("Username", user_name);
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this,"Password Incorrect",Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.sign_up) {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
    }

    }

