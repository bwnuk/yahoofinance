package com.github.wnuk.yahoofinance.utilities;

import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static android.content.ContentValues.TAG;

public class NetworkUtils {

    public static HttpResponse<JsonNode> responseFromApi(String link){
        try {
            HttpResponse<JsonNode> response = Unirest.get(link)
                    .header("X-RapidAPI-Key", "d3b5c0fcffmsh4c840e829cf22c2p14b3bejsn71dd5df02302")
                    .asJson();
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
