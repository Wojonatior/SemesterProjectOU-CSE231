package secs.cse231.project.user;

import java.io.Serializable;
import java.net.URI;

import secs.cse231.project.util.*;

public class User implements Comparable, Serializable{
    private String firstName;
    private String lastName;
    private String id; /* sorting key */
    private String password; //added
    private int numGroups = 0; //added
    ArraySortedList2<Group> usersGroups; /* list of groups that user belongs to */

    private Group pGroup; /* the major group */
    private URI homeFolder;

    public User(String firstName, String lastName, String id, Group pGroup, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.pGroup = pGroup;
        this.password = password;
        usersGroups = new ArraySortedList2<Group>();
        if (pGroup != null) {
        	usersGroups.add(pGroup);
        	numGroups++;
        }
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    //Added
    //Returns a concatenated form of the first and last name with a space
    public String getFullName() {
    	return firstName+ " " +lastName;
    }
 
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return pGroup;
    }
    
    //Added
    public ArraySortedList2 getUsersGroups() {
    	return (ArraySortedList2) usersGroups;
    }

    public URI getHomeFolder() {
        return homeFolder;
    }

    public void addGroup(Group group) {
    	this.usersGroups.add(group);
    	numGroups++;
    }
    
    public void removeGroup(Group group) {
    	this.usersGroups.remove(group);
    	numGroups--;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    //Added
    //Tested
    public boolean changePassword(String oldPass, String newPass) {
    	//Checks if the provided old password matches, and the changes the password if this condition is met
    	if(oldPass.equals(password)) {
    		password = newPass;
    		System.out.println("Password change successful!");
    		return true;
    	}
    	System.out.println("Old password did not match!");
    	return false;
    }

    public void setHomeFolder(URI homeFolder) {
        this.homeFolder = homeFolder;
    }

    //added
    @Override
    public int compareTo(Object o) {
    	return id.compareToIgnoreCase(((User) o).getId());
    }
    

    @Override
    public boolean equals(Object o) {
    	return id.equalsIgnoreCase(((User) o).getId());
    }
    
    //added
    @Override
    public String toString() {
        String output ="Name: " + firstName + " " + lastName + "\n" +
        		"ID: "+ id + "\n" +
        		"Groups with membership: \n";
        if(numGroups != 0) {
        	for(int i = 0; i < numGroups; i++) {
        		output += usersGroups.getNext().getName() +" ";
        	}
        }
        return output;
    }
}