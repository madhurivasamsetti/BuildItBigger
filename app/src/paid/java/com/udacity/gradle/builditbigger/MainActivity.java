package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vasam.androiddisplayjokes.DisplayJoke;


public class MainActivity extends AppCompatActivity implements GetJokeAsyncTask.AsyncResponse {
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loader);
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

    @Override
    public void returnJoke(String output) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        Intent jokeIntent = new Intent(MainActivity.this, DisplayJoke.class);
        jokeIntent.putExtra(Intent.EXTRA_TEXT, output);
        startActivity(jokeIntent);
    }

    public void tellJoke(View view) {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask(MainActivity.this, this).execute();
    }
}
