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
import java.util.LinkedList;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends AsyncTask<String, Void, String> {
    MainActivity caller;

    GetTask(MainActivity caller){
        this.caller = caller;
    }

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
        InputStream inputStream;
        try {
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return convertInputStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
    }


    protected void onPostExecute(String result) {
        if (result == null) {
            return;
        }

        Gson gson = new Gson();
        User user = gson.fromJson(result, User.class);

        LinkedList<String> users = new LinkedList<String>();
        for (User u : user.friends) {
            users.push(u.name);
        }

        this.caller.setupList(users);
    }
}