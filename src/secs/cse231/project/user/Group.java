package secs.cse231.project.user;

import secs.cse231.project.util.ListInterface;

import java.io.Serializable;

public class Group implements Comparable, Serializable {
    private String name;
    ListInterface<User> members;

    public Group(String name) {
        this.name = name;
        /* TODO */
    }

    public String getName() {
        return name;
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
        return "Group{" +
                "name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}