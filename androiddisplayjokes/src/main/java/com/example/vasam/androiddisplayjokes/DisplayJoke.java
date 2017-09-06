package com.example.vasam.androiddisplayjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        TextView jokeTextView = (TextView) findViewById(R.id.joke_textView);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(Intent.EXTRA_TEXT);
        jokeTextView.setText(joke);
    }
}
