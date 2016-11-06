package com.example.shahh.pfrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shahh on 11/6/2016.
 */

public class RegisterComplete extends Activity {

    DatabaseHelper helper=new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercomplete);
        init();

    }
    public void init(){
        String username=getIntent().getStringExtra("Username");
        TextView userid=(TextView)findViewById(R.id.user_name);
        userid.setText(username);
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

            if (chkPassword.equals("Not Found")) {
                Toast.makeText(RegisterComplete.this, "Username Not Found", Toast.LENGTH_SHORT).show();
            } else if (chkPassword.equals(pass)) {
                Intent i = new Intent(RegisterComplete.this, Display.class);
                i.putExtra("Username", user_name);
                startActivity(i);
            } else {
                Toast.makeText(RegisterComplete.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
