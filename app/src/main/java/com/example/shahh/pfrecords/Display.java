package com.example.shahh.pfrecords;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class Display extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        init();

    }
     public void init(){
         String username=getIntent().getStringExtra("Username");
         TextView userid=(TextView)findViewById(R.id.user_name);
         userid.setText(username);
     }
}
