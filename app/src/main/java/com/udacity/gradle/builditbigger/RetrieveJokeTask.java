package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.androidjoker.JokerActivity;
import com.example.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

public class RetrieveJokeTask extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private ProgressDialog progressDialog;

    public RetrieveJokeTask(Context context) {
        if (context != null) {
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.setMessage("Trying not to piss off Chuck Norris while retrieving a cool joke...");
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://massive-build.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Intent jokeIntent = new Intent(context, JokerActivity.class);
        jokeIntent.putExtra("joke", result);
        context.startActivity(jokeIntent);
    }
}