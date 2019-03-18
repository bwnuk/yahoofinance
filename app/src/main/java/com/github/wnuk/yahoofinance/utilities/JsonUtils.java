package com.github.wnuk.yahoofinance.utilities;

import android.util.Log;

import com.github.wnuk.yahoofinance.data.Market;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    public static ArrayList<Market> getDataList(HttpResponse<JsonNode> results){
        final String J_SYM = "exchange";
        final String J_fEN = "fullExchangeName";

        ArrayList<Market> resultsMarkets = new ArrayList();
        try {
            JSONArray array =  results.getBody().getObject().getJSONObject("marketSummaryResponse").getJSONArray("result");


            for (int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                resultsMarkets.add(new Market(jsonObject.getString(J_SYM), jsonObject.getString(J_fEN)));
                Log.e(TAG, jsonObject.getString(J_SYM) + " ------ " + jsonObject.getString(J_fEN));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultsMarkets;
    }
}
