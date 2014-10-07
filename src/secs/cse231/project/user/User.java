package secs.cse231.project.user;

import java.io.Serializable;
import java.net.URI;


public class User implements Comparable, Serializable{
    private String firstName;
    private String lastName;
    private String id; /* sorting key */
    private String password;

    private Group group; /* the major group */
    private URI homeFolder;

    public User(String firstName, String lastName, String id, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return group;
    }

    public URI getHomeFolder() {
        return homeFolder;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHomeFolder(URI homeFolder) {
        this.homeFolder = homeFolder;
    }

    @Override
    public int compareTo(Object o) {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}