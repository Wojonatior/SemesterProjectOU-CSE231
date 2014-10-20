package secs.cse231.project.user;

import secs.cse231.project.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Note: We do NOT allow duplicated objects in either users or groups list, i.e. each user should have unique user id
 * and each group object should have a unique name
 */
public class UserDatabase implements Serializable {
	ArraySortedList2<User> users;
	ArraySortedList2<Group> groups;
    int numUsers;
    int numGroups;
    String databasePassword = null;


    public UserDatabase(String password) {
        users = new ArraySortedList2<User>();
        groups = new ArraySortedList2<Group>();
        databasePassword = password;
    }

    /**
     * read the object from file, this operations overwrite the original object info
     * @param fileName
     * @return true if successful read, otherwise false
     */
    //TODO Test
    public boolean readFromFile(String fileName) throws IOException{
    	//Temp database for data import
    	UserDatabase tempUserDatabase = null;
    	try
        {
    		//imports file from the provided fileName if it exists
            FileInputStream fileIn = new FileInputStream(fileName +".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempUserDatabase = (UserDatabase) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return false;
        }catch(ClassNotFoundException c)
        {
            System.out.println("User Database class not found!");
            c.printStackTrace();
            return false;
        }
    	//Assigns all values from temp database to working database
    	this.users = tempUserDatabase.users;
    	this.groups = tempUserDatabase.groups;
    	this.numGroups = tempUserDatabase.numGroups;
    	this.numUsers = tempUserDatabase.numUsers;
    	this.databasePassword = tempUserDatabase.databasePassword;
      	return true;
    }

    /**
     * write the object to file
     * @param fileName
     * @return
     * @throws IOException
     */
    //TODO Test
    public boolean writeToFile(String fileName) throws IOException {
    	try {
    		FileOutputStream fileOut =
    			new FileOutputStream(fileName +".ser");
    		ObjectOutputStream out = new ObjectOutputStream(fileOut);
    		out.writeObject(this);
    		out.close();
    		fileOut.close();
    		System.out.printf("Serialized data is saved as "+ fileName +".ser");
    	}catch(IOException i)
    	{
    		i.printStackTrace();
    		return false;
    	}
    	return true;
    }

    /**
     * Add a user to the user list of the database
     * @param u
     * @return true if successfully added; false if user exists in database, name is null or name contains a space
     */
    //TODO Test
    public boolean addUser(User u) {
    	//checks if user exists in database and returns false if they already exist in the database
    	if(getUser(u.getId()) != null) {
    		System.out.println("User already exists in database! User not created!");
    		return false;
    	}
    	//checks if either name string is an empty string
    	if(u.getFirstName() == null || u.getLastName() == null) {
    		System.out.println("User's first or last name is null! User not created!");
    		return false;
    	}    
    	//checks if either name string contains whitespace
    	if(whitespaceCheck(u.getFirstName()) ||whitespaceCheck(u.getLastName())) {
    		System.out.println("User's first or last name contains a space! User not created!");
    		return false;
    	}
    	//adds user if they don't exist in the database
    	users.add(u);
    	if(u.getGroup() != null)
    		joinGroup(u, u.getGroup());
    	numUsers++;
        return true;
    }

    /**
     * Add a group to the group list of the database
     * @param g
     * @return true if successful added
     */
    //TODO Test
    public boolean addGroup(Group g) {
    	//checks if group exists in database and returns false if they already exist in the database
    	if(getGroup(g.getName())!= null) {
    		System.out.println("Group already exists in database! Group not created!");
    		return false;
    	}
    	//checks if group name string is an empty string
    	if(g.getName() == null) {
    		System.out.println("Group's name is null! Group not created!");
    		return false;
    	}    
    	//checks if group name string contains whitespace
    	if(whitespaceCheck(g.getName())) {
    		System.out.println("Group's name contains a space! Group not created!");
    		return false;
    	}
    	//adds group if they don't exist in the database
    	groups.add(g);
    	numGroups++;
        return true;
    }
    
    //Added
    //TODO Test
    //possibly user contain user/group
    public boolean removeUser(User u) {
    	if(u == null) {
    		System.out.println("Said user does not exist in database to be removed!");
    		return false;
    	}
    	//resets groups list to be iterated through
    	groups.reset();
    	for(int i = 0; i < groups.size(); i++){
    		//removes user from each group that contains him
    		groups.getNext().members.remove(u);
    	}
    	//removes user from user list
    	users.remove(u);
    	numUsers--;
		System.out.println("User succesfully removed!");
    	return true;    	
    }
    
    //Added
    //TODO Test
    //possibly user contain user/group
    public boolean removeGroup(Group g) {
    	//Checks if group exists
    	if(g == null) {
    		System.out.println("Said group does not exist in database to be removed!");
        	return false;
    	}
    	//Resets members array list to be iterated through
    	g.members.reset();
    	//Removes the group from each user's group list for every user that is in the group to be removed
    	for(int i = 0; i < g.members.size(); i++){
    		g.members.getNext().usersGroups.remove(g);
    	}
    	//Removes said group from group list and returns true
    	if(groups.remove(g)) {
    		numGroups--;
			System.out.println("Group succesfully removed!");
			return true;   
    	}
    	System.out.println("Group was not succesfully removed!");
    	return false;
    }

    /**
     * Have user join the group
     * Precondition: User u must be in the user lists, otherwise, return false
     * @param u
     * @param g
     * @return
     */
    //TODO Test
    public boolean joinGroup (User u, Group g) {
        if(getUser(u.getId()) != null && !isGroupMember(u,g)) {
        	g.addUser(u);
        	u.addGroup(g);
        	return true;
        }
    	return false;
    }

    /**
     * Add a user to the user list and have it join the group. If user is already in the user list, just add to the group
     * @param u
     * @param g
     * @return true if successful
     */
    //TODO Test
    public boolean addUserJoinGroup(User u, Group g) {
    	if(u.getId() == null){
    		g.addUser(u);
    		numUsers++;
    	}
    	if(!isGroupMember(u,g)) {
        	u.addGroup(g);
        	users.add(u);
        	return true;
        }
    	return false;

    }

    /**
     * check to see whether a user with the specified id exist in the list, if so return the User object, otherwise, return NULL
     * @param id
     * @return
     */
    User containUser(String id) {
    	//Same functionality as getUser()
        return getUser(id);
    }

    /**
     * check to see whether a group with the specified name exist or not in the group list, if so return the
     * group object, otherwise null
     * @param name
     * @return
     */
    Group containGroup(String name) {
    	//Same functionality as getGroup()
        return getGroup(name);
    }

    /**
     * is user with specified id part of the group that has the specified groupName
     * @param userID
     * @param groupName
     * @return
     */
    boolean isGroupMember(String userID, String groupName) {
    	//Checks if user and group that correspond to ID and Name exist, if they do passes the said user and group to the more robust function
        if(getUser(userID) != null && getGroup(groupName) != null)
        	return isGroupMember(getUser(userID),getGroup(groupName));
    	return false;
    }

    /**
     * is user a member of group
     * @param user
     * @param group
     * @return
     */
    //tested
    boolean isGroupMember(User u, Group g) {
        //if(users.contains(u) && groups.contains(g))
        	return g.getMembers().contains(u);
        /*System.out.println("Either group or user does not exist.");
    	return false; */
    }

    /**
     * return the User object that has the specified id, null if not exist
     * @param id
     * @return
     */
    //TODO Test    
    User getUser(String id) {
    	User tempUser = null;
    	for(int i = 0; i < users.size(); i++) {
    		//Assigns the next user from the list to the temp user
    		tempUser = users.getNext();
    		if(id.equals(tempUser.getId()))
    			//Compares the arg and the name of the current user in the list, returning the user if they match
    			return tempUser;
    	}
    	return null;
    }

    /**
     * return the Group object that has the specified group name, null if not exist
     * @param name
     * @return
     */
    //TODO Test
    Group getGroup(String name) {
    	Group tempGroup = new Group(null); 
    	for(int i = 0; i < groups.size(); i++) {
    		//Assigns the next group from the list to the temp group
    		tempGroup = groups.getNext();
    		 //Compares the arg and the name of the current group in the list, returning the group if they match
    		if(name.equals(tempGroup.getName()))
    			return tempGroup;
    	}
    	return null;
    }
    
    /**
     * Return a list of users that belong to the specified group
     * @param g
     * @return 
     */
    //TODO Test
    ListInterface<User> getUsers(Group g) {
    	return g.getMembers();
    }
    
    /**
     * return a list of groups that the user with specified userID belong to
     * @param userID
     * @return 
     */
    //TODO Test
    ListInterface<Group> getGroups(String userID) {
    	return getUser(userID).getUsersGroups();
    }

    /**
     * log in the user to the system if the user provided the correct id/password pair
     * @param userID
     * @param passwd
     * @return
     */
    //TODO Test
    boolean login(String userID, String password) {
    	//Creates a temporary user and assigns the user with the corresponding UserID
    	User tempUser = getUser(userID);
    	//Checks if the user exists
    	if(tempUser != null)
    		//Checks if the password matches
    		if(tempUser.getPassword().equals(password))
    			return true;
        return false;
      //TODO ???
    }
    
    //Added
    /**
     * Checks if a string contains whitespace
     * @param testString
     * @return true if a testString contains whitespace; false if a testString does not
     */
    //TODO Test
    boolean whitespaceCheck(String testString) {
    	//iterates through string by each character and checks for whitespace
    	for(int i = 0; i < testString.length(); i++) {
    		if(Character.isWhitespace(testString.charAt(i)))
    			return true;
    	}
    	return false;
    }

    //Added
    //Tested
    public boolean changePassword(String oldPass, String newPass) {
    	//Checks if the provided old password matches, and the changes the password if this condition is met
    	if(oldPass.equals(databasePassword)) {
    		databasePassword = newPass;
    		System.out.println("Password change successful!");
    		return true;
    	}
    	System.out.println("Old password did not match!");
    	return false;
    }
}







