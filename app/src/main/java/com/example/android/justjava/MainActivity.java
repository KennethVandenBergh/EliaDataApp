package com.example.android.justjava;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This app displays the status of the Belgian power system
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void update(View view) throws IOException{

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            Log.v("PlaceholderFragment", "Network connection available");
        } else {
            // display error
            Log.v("PlaceholderFragment","No network connection available");
        }

        // These two need to be declared outside the try/catch so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;

        try {
            // Construct the URL for the Elia Imbalance Price query: http://publications.elia.be/Publications/Publications/ImbalanceNrvPrice.v1.svc/GetImbalanceNrvPrices?day=2016-03-29
            URL url = new URL("http://publications.elia.be/Publications/Publications/ImbalanceNrvPrice.v1.svc/GetImbalanceNrvPrices?day=2016-03-29");

            // Create the request to Elia Web Service, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the imbalance data

            // close inputStream after using the app
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
    }

}