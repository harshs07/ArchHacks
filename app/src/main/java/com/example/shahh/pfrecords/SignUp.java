package com.example.shahh.pfrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.shahh.pfrecords.R.id.textView;

/**
 * Created by shahh on 11/5/2016.
 */

public class SignUp extends Activity {

    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();

    }

    protected void init(){


        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //email.setText("Works");
                EditText first= (EditText)findViewById(R.id.Fname);
                final String firstName=first.getText().toString();
                EditText username= (EditText)findViewById(R.id.Uname);
                final String Username=username.getText().toString();
                EditText email= (EditText)findViewById(R.id.email_id);
                final String Email=email.getText().toString();
                EditText insurance= (EditText)findViewById(R.id.ins_id);
                final String Insurance=insurance.getText().toString();
                EditText password= (EditText)findViewById(R.id.pass1);
                final String pass=password.getText().toString();
                EditText confirmPass= (EditText)findViewById(R.id.con_pass);
                final String conPass=confirmPass.getText().toString();

                EditText newfirst= (EditText)findViewById(R.id.Fname);
                newfirst.setText("");
                EditText newusername= (EditText)findViewById(R.id.Uname);
                newusername.setText("");
                EditText newemail= (EditText)findViewById(R.id.email_id);
                newemail.setText("");
                EditText newinsurance= (EditText)findViewById(R.id.ins_id);
                newinsurance.setText("");
                EditText newpassword= (EditText)findViewById(R.id.pass1);
                newpassword.setText("");
                EditText newconfirmPass= (EditText)findViewById(R.id.con_pass);
                newconfirmPass.setText("");

                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if(firstName.equals("")||Username.equals("")||Email.equals("")||Insurance.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Field cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(Email.matches(emailPattern))) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(conPass) || pass.equals("")) {
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Contact c = new Contact();

                            c.setName(firstName);
                            c.setUname(Username);
                            c.setEmail(Email);
                            c.setInsurance(Insurance);
                            c.setPassword(pass);

                            helper.insertContact(c);

                            Intent i = new Intent(SignUp.this, RegisterComplete.class);
                            i.putExtra("Username", Username);
                            startActivity(i);
                        }


            }
        });

    }
}
