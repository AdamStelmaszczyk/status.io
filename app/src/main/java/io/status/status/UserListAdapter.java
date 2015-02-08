package io.status.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.status.status.model.UserModel;

/**
 * Created by hirish on 2/8/15.
 */
public class UserListAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<UserModel> users;

    public UserListAdapter(Context context, ArrayList<UserModel> users) {
        super(context, R.layout.row_layout, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        ImageView avatarView = (ImageView) rowView.findViewById(R.id.avatar);

        UserModel user = users.get(position);

        textView.setText(user.name);

        if (user.name.equals("Larry Page")){
            avatarView.setImageResource(R.drawable.larry);
        } else if (user.name.equals("Elon Musk")) {
            avatarView.setImageResource(R.drawable.elon);
        } else {
            avatarView.setImageResource(R.drawable.mayer);
        }

        // Change the icon for Windows and iPhone
        if (user.status == 1) {
            imageView.setImageResource(R.drawable.ic_action_emo_cool);
        } else if (user.status == 3) {
            imageView.setImageResource(R.drawable.ic_action_emo_shame);
        } else {
            imageView.setImageResource(R.drawable.ic_action_emo_err);
            // Unknown User Status
        }

        return rowView;
    }
}
