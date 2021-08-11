package com.example.external_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //creating variables
    EditText et1, et2;
    Button b1, b2;
    LinearLayout ll;
    String result;
    SaveData sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating objects
        et1 = findViewById(R.id.ed1);
        et2 = findViewById(R.id.ed2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        ll = findViewById(R.id.ll1);
        sd = new SaveData();
        //setting listener for save button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting text from edit text
                String rno = et1.getText().toString();
                String name = et2.getText().toString();
                //saving the record in server
                result = sd.save(rno, name);
                //creating a toast to inform user of the result
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
        //setting on click listener for show record button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //removing previous record
                ll.removeAllViews();
                //getting result from the server
                result = sd.getData();
                //creating the array of data rows
                String rows[] = result.split(";");
                //iterating through data row by row
                for(String row:rows) {
                    //creating a new text view
                    TextView tv = new TextView(MainActivity.this);
                    //setting data on text view
                    tv.setText(row);
                    //adding text view to the linear layout
                    ll.addView(tv);
                }
            }
        });
    }
}