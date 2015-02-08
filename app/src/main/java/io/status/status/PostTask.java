package io.status.status;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Stelmaszczyk
 */
public class PostTask extends AsyncTask<String, Void, String> {

    private InputModel inputModel;

    PostTask(InputModel inputModel) {
        this.inputModel = inputModel;
    }

    @Override
    protected String doInBackground(String... urls) {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(urls[0]);
        HttpResponse response;
        try {
            request.setEntity(new UrlEncodedFormEntity(inputModel.getNameValuePairs()));

            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Utils.responseToString(response);
    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }
        Log.d("test", "POST response: " + response);
    }
}
