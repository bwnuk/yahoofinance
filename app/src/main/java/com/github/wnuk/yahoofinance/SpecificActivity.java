package com.github.wnuk.yahoofinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.wnuk.yahoofinance.utilities.JsonUtils;
import com.github.wnuk.yahoofinance.utilities.NetworkUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.ArrayList;

public class SpecificActivity extends AppCompatActivity {
    private static final String TAG = "SpecificActivity";

    private String symbol;
    private TextView mSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        mSymbol = (TextView) findViewById(R.id.tv_test);
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                symbol = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                mSymbol.setText(symbol);

                loadData();
            }
        }
    }

    private void loadData(){
        new FetchSpecific().execute();
    }

    private class FetchSpecific extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String[] doInBackground(String... params) {
            HttpResponse<JsonNode> results = NetworkUtils
                    .responseFromApi("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-charts?comparisons=%5EGDAXI%2C%5EFCHI&region=US&lang=en&symbol="
                            +symbol+"&interval=1d&range=5d");
            if (results.equals(null)){
                return null;
            }else {
                return JsonUtils.getDataListChart(results);
            }
        }

        @Override
        protected void onPostExecute(String[] data) {
            if(data != null){
                Log.e(TAG, data.toString());
            }else {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            //loadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
