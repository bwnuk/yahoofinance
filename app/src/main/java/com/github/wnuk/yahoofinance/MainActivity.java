package com.github.wnuk.yahoofinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.wnuk.yahoofinance.data.Market;
import com.github.wnuk.yahoofinance.utilities.JsonUtils;
import com.github.wnuk.yahoofinance.utilities.NetworkUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

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
        //TODO
    }

    private class FetchFinancial extends AsyncTask<String, Void, ArrayList<Market>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected ArrayList<Market> doInBackground(String... params) {
            HttpResponse<JsonNode> results = NetworkUtils.responseFromApi();
            if (results.equals(null)){
                return null;
            }else {
                return JsonUtils.getDataList(results);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Market> marketData) {
            if(marketData != null){
                mAdapter.setData(marketData);
            }else {

            }
        }
    }

}
