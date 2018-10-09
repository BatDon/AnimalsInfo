package com.example.david.animalsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class CatsActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_TWO);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.catsTitle);
        textView.setText(message);
        Log.i("textView","= "+textView);
        TextView textView2 = findViewById(R.id.catsTextBox);
        textView2.setText(message2);

    }
}
