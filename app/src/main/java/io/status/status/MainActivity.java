package io.status.status;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

import io.status.status.model.UserModel;

/**
 * @author Adam Stelmaszczyk
 */
public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);

        ArrayList<UserModel> users = new ArrayList<UserModel>();
        this.userListAdapter = new UserListAdapter(this, users);
        this.listView.setAdapter(this.userListAdapter);
        Repeater.userListAdapter = this.userListAdapter;

    }

    @Override
    protected void onResume() {
        super.onResume();

        Repeater.doAfterDelay(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Repeater.stop(this);
    }


    public void setupList(ArrayList<String> users) {
    }
}
