package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vasam.androiddisplayjokes.DisplayJoke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements GetJokeAsyncTask.AsyncResponse {

    private InterstitialAd mInterstitialAd;
    private String result;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.adUnitId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("MainActivity.class", "interstitial didn't load yet.");
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("MainActivity.class", "interstitial didn't load yet.");
//        }
//    }

    @Override
    public void returnJoke(String output) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        result = output;
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("MainActivity.class", "interstitial didn't load yet.");
        }
        mLoadingIndicator.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask(MainActivity.this,this).execute();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Intent jokeIntent = new Intent(MainActivity.this,DisplayJoke.class);
                jokeIntent.putExtra(Intent.EXTRA_TEXT, result);
                startActivity(jokeIntent);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

}
