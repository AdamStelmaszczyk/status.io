package io.status.status;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... urls) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(urls[0]);
        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Utils.responseToString(response);
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }
        Log.d("test", "response: " + response);
        Gson gson = new Gson();
        ResponseModel responseModel = gson.fromJson(response, ResponseModel.class);
        System.out.println("responseModel: " + responseModel);
    }
}