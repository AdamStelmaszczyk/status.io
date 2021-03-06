package io.status.status.task;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import io.status.status.model.InputModel;

/**
 * @author Adam Stelmaszczyk
 */
public class PostTask extends BaseTask<String, Void, String> {

    private InputModel inputModel;

    public PostTask(InputModel inputModel) {
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
        return responseToString(response);
    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }
        Log.d("test", "POST response: " + response);
    }
}
