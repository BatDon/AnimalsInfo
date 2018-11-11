package com.example.david.animalsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class FrogsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frogs);
        //dataIntoTable();
        //Log.i("something", "ok");

        // Get the Intent that started this activity and extract the string
        Intent intent3 = getIntent();
        String message = intent3.getStringExtra(MainActivity.EXTRA_MESSAGE_SIXTEEN);
        String message2 = intent3.getStringExtra(MainActivity.EXTRA_MESSAGE_SEVENTEEN);
        String message3 = intent3.getStringExtra(MainActivity.EXTRA_MESSAGE_EIGHTEEN);
        String message4 = intent3.getStringExtra(MainActivity.EXTRA_MESSAGE_NINETEEN);
        //change this message5 into table
        //String table[] = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);
        //TableLayout tableLayout = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.frogTitle);
        textView.setText(message);
        Log.i("textView", "= " + textView);
        TextView textView2 = findViewById(R.id.frogsTextBox);
        textView2.setText(message2);
        TextView textView3 = findViewById(R.id.frogsTextBox2);
        textView3.setText(message3);
        TextView textView4 = findViewById(R.id.frogsTextBox3);
        textView4.setText(message4);
    }
}

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_frogs);
//
//        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_TWO);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.frogTitle);
//        textView.setText(message);
//        Log.i("textView","= "+textView);
//        TextView textView2 = findViewById(R.id.frogsTextBox);
//        textView2.setText(message2);
//
//    }
