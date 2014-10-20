package secs.cse231.project.user;

import secs.cse231.project.util.*;

import java.io.Serializable;

public class Group implements Comparable, Serializable {
    private String name;
    int numUsers = 0; //added
    ArraySortedList2<User> members;

    public Group(String name) {
        this.name = name;
        members = new ArraySortedList2<User>();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
    	return name.compareToIgnoreCase(((Group) o).getName());
    }
    //added    
    public ArraySortedList2 getMembers() {
    	return (ArraySortedList2) members;
    }
    //added    
    public void addUser(User u) {
    	members.add(u);
    	numUsers++;
    }
    //added
    public void removeUsers(User u) {
    	members.remove(u);
    	numUsers--;
    }
    
    //added
    @Override
    public boolean equals(Object o) {
    	return name.equalsIgnoreCase(((Group)o).getName());
    }

    @Override
    public String toString() {
        String output = "Group Name: " + name + "\n" +
        		"Members: \n";
        if(numUsers != 0) {
        	for(int i = 0; i < numUsers; i++) {
        		output += members.getNext().getFullName() +" ";
        	}
        }
        return output;
    }
}