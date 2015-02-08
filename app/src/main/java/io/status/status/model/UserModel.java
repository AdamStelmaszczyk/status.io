package io.status.status.model;

import java.util.List;

/**
 * Created by hirish on 2/7/15.
 */
public class UserModel {

    public int id;
    public int status;
    public String name;
    public List<UserModel> friends;

    private UserModel(int id, String name, List<UserModel> friends) {
        this.id = id;
        this.name = name;
        this.friends = friends;
    }

    UserModel(int id, int status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public String toString() {
        return "<User " + name + " " + status + ">";
    }
}
