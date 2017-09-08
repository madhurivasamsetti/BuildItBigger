package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vasam.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


/**
 * Created by vasam on 9/6/2017.
 */

public class GetJokeAsyncTask extends AsyncTask<String, Void, String> {

    private static MyApi myApiService = null;
    private Context context=null;
    private AsyncResponse mCallback=null;
    private ProgressBar mLoadingIndicator=null;

    public interface AsyncResponse {
        void returnJoke(String output);
    }

    public GetJokeAsyncTask(Context context, AsyncResponse mCallback, ProgressBar mLoadingIndicator) {
        this.context = context;
        this.mCallback = mCallback;
        this.mLoadingIndicator= mLoadingIndicator;
    }

    @Override
    protected String doInBackground(String... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }
        try {
            return myApiService.fetchJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String response) {
        mCallback.returnJoke(response);
        mLoadingIndicator.setVisibility(View.INVISIBLE);

    }
}
