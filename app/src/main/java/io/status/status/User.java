package io.status.status;

import java.util.List;

/**
 * Created by hirish on 2/7/15.
 */
public class User {
    public int id;
    public String name;
    public List<User> friends;

    private User(int id, String name, List<User> friends){
        this.id = id;
        this.name = name;
        this.friends = friends;
    }

    User(int id, String name){
        this.id = id;
        this.name = name;
        this.friends = null;
    }

    public String toString() {
        return "<User " + this.name + ">";
    }
}
