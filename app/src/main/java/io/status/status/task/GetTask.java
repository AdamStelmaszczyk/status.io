package io.status.status.task;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import io.status.status.UserListAdapter;
import io.status.status.model.UserModel;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends BaseTask<String, Void, String> {
    UserListAdapter userListAdapter;

    public GetTask(UserListAdapter userListAdapter) {
        this.userListAdapter = userListAdapter;
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
        return responseToString(response);
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }

        Log.d("test", "GET response: " + response);
        Gson gson = new Gson();
        UserModel user = gson.fromJson(response, UserModel.class);

        userListAdapter.clear();
        userListAdapter.addAll(user.friends);
        userListAdapter.notifyDataSetChanged();
    }
}