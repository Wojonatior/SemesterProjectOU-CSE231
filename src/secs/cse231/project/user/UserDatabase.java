package secs.cse231.project.user;

import secs.cse231.project.util.ArraySortedList2;
import secs.cse231.project.util.ListInterface;

import java.io.IOException;
import java.io.Serializable;

/**
 * Note: We do NOT allow duplicated objects in either users or groups list, i.e. each user should have unique user id
 * and each group object should have a unique name
 */
public class UserDatabase implements Serializable {
    ListInterface<User> users;
    ListInterface<Group> groups;
    int numUsers;
    int numGroups;

    /**
     * The main function that takes input from users and perform requested operations
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Hello World!\n");

    }

    public UserDatabase() {
        users = new ArraySortedList2<User>();
        groups = new ArraySortedList2<Group>();
    }

    /**
     * read the object from file, this operations overwrite the original object info
     * @param fileName
     * @return true if successful read, otherwise false
     */
    public boolean readFromFile(String fileName) throws IOException{

        return false;
    }

    /**
     * write the object to file
     * @param fileName
     * @return
     * @throws IOException
     */
    public boolean writeToFile(String fileName) throws IOException {
        return false;
    }

    /**
     * Add a user to the user list of the database
     * @param u
     * @return true if successful added
     */
    public boolean addUsers(User u) {

        return false;
    }

    /**
     * Add a group to the group list of the database
     * @param g
     * @return true if successful added
     */
    public boolean addGroup(Group g) {
        return false;
    }

    /**
     * Have user join the group
     * Precondition: User u must be in the user lists, otherwise, return false
     * @param u
     * @param g
     * @return
     */
    public boolean joinGroup (User u, Group g) {
        return false;
    }

    /**
     * Add a user to the user list and have it join the group. If user is already in the user list, just add to the group
     * @param u
     * @param g
     * @return true if successful
     */
    public boolean addUserJoinGroup(User u, Group g) {
        return false;

    }

    /**
     * check to see whether a user with the specified id exist in the list, if so return the User object, otherwise, return NULL
     * @param id
     * @return
     */
    User containUser(String id) {
        return null;
    }

    /**
     * check to see whether a group with the specified name exist or not in the group list, if so return the
     * group object, otherwise null
     * @param name
     * @return
     */
    Group containGroup(String name) {
        return null;
    }

    /**
     * is user with specified id part of the group that has the specified groupName
     * @param userID
     * @param groupName
     * @return
     */
    boolean isGroupMember(String userID, String groupName) {
        return false;
    }

    /**
     * is user a member of group
     * @param user
     * @param group
     * @return
     */
    boolean isGroupMember(User user, Group group) {
        return false;
    }

    /**
     * return the User object that has the specified id, null if not exist
     * @param id
     * @return
     */
    User getUser(String id) {
        return null;
    }

    /**
     * return the Group object that has the specified group, null if not exist
     * @param name
     * @return
     */
    Group getGroup(String name) {
        return null;
    }
    
    /**
     * Return a list of users that belong to the specified group
     * @param g
     * @return 
     */
    ListInterface<User> getMembers(Group g) {
        return null;
    }
    
    /**
     * return a list of groups that the user with specified userID belong to
     * @param userID
     * @return 
     */
    ListInterface<Group> getGroups(String userID) {
        return null;
    }

    /**
     * log in the user to the system if the user provided the correct id/passwd pair
     * @param userID
     * @param passwd
     * @return
     */
    boolean login(String userID, String passwd) {
        return false;
    }
}