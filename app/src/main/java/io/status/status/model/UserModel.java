package io.status.status.model;

import java.util.List;

/**
 * Created by hirish on 2/7/15.
 */
public class UserModel {

    public int id;
    public int status;
    public int status_time;
    public String name;
    public List<UserModel> friends;

    private UserModel(int id, String name, List<UserModel> friends) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.status_time = 5;
    }

    UserModel(int id, int status, String name, int status_time) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.status_time = status_time;
    }

    UserModel(int id, int status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.status_time = 10;
    }

    public String toString() {
        return "<User " + name + " " + status + ">";
    }
}
