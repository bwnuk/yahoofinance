package com.github.wnuk.yahoofinance;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.wnuk.yahoofinance.data.Market;
import com.github.wnuk.yahoofinance.utilities.JsonUtils;
import com.github.wnuk.yahoofinance.utilities.NetworkUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MarketsAdapter.MarketAdapterOnClickHandler {
    private static final String TAG = "MyActivity";

    private RecyclerView mRecyclerView;
    private MarketsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_markets);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MarketsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        loadData();
    }

    private void loadData(){
        new FetchFinancial().execute();
    }

    @Override
    public void onClick(Market market) {
        Context context = this;
        Class destinationClass = SpecificActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, market.getSymbol());
        startActivity(intentToStartDetailActivity);
    }

    /**
     * Class to download data from api
     */
    private class FetchFinancial extends AsyncTask<String, Void, ArrayList<Market>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Market> doInBackground(String... params) {
            HttpResponse<JsonNode> results = NetworkUtils
                    .responseFromApi("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en");
            if (results.equals(null)){
                return null;
            }else {
                return JsonUtils.getDataListMarkets(results);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Market> marketData) {
            if(marketData != null){
                mAdapter.setData(marketData);
                //Log.e(TAG, marketData.toString());
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
            mAdapter.setData(null);
            loadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
