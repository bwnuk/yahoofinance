package com.github.wnuk.yahoofinance.utilities;

import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static android.content.ContentValues.TAG;

public class NetworkUtils {

    public static HttpResponse<JsonNode> responseFromApi(){
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-summary?region=US&lang=en")
                    .header("X-RapidAPI-Key", "d3b5c0fcffmsh4c840e829cf22c2p14b3bejsn71dd5df02302")
                    .asJson();;
            Log.d(TAG, "KOD: "+response.getCode());

            if (response.getCode() != 200){
               return null;
            } else {
                return response;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }
}
