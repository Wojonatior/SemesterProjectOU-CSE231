package secs.cse231.project.user;

import java.io.IOException;
import java.util.Scanner;

public class UserDatabaseImplementation {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String databaseName, ynQuery, tempFName, tempLName, tempID, tempPassword, tempGroupString, oldPassword, newPassword = null;
		int mainMenuOption, subMenuOption;
		UserDatabase myUserDatabase = new UserDatabase(null);
		User tempUser;
		Group tempGroup;
		
		//Asks user to if they would like to import a file and loops until an appropriate response is provided.
		do{
			System.out.println("Do you have a user database you would like to import?(y/n)");
			ynQuery = keyboard.nextLine();
		}while(ynQuery.compareToIgnoreCase("y") != 0 && ynQuery.compareToIgnoreCase("n")!= 0 );
		
		//Imports a previous database file or creates a new one for the user
		if(ynQuery.equalsIgnoreCase("y")) {
			System.out.println("What is the name of the database you would like to import? ");
			databaseName = keyboard.nextLine();
			//calling the import 
			try{
				myUserDatabase.readFromFile(databaseName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("What would you like to name this database? ");
			databaseName = keyboard.nextLine();
			System.out.println("Would you like to password protect this database?(y/n) ");
			if(keyboard.nextLine().equalsIgnoreCase("y")){
				System.out.println("Enter your password for the database: ");
				newPassword = keyboard.nextLine();
			}
			//Creates new database, with a password of null making a database w/out authentication
			myUserDatabase = new UserDatabase(newPassword);
			//addition of root user to database
			myUserDatabase.addUser(new User("root", "", "root", null, "admin"));
			System.out.println("'root' account has been created with the password of 'admin'\n");
		}
		
		
		
		//Looping switch statement to handle user input in the form of a menu
		do{
			System.out.println("Select a menu option:");
			System.out.println("1: Add user or group"); //tested
			System.out.println("2: Remove user or group"); //tested
			System.out.println("3: View user(s) or group(s)"); //tested w/out auth
			System.out.println("4: Add user to group"); //tested
			System.out.println("5: Add/Remove or change database/user password"); //tested
			System.out.println("6: Save database");
			System.out.println("7: Change database name"); //tested
			System.out.println("8: Exit"); //tested
			mainMenuOption = keyboard.nextInt();
			keyboard.nextLine();
			switch(mainMenuOption) {
				default:
					System.out.println("Invalid  menu option, try again!");
					break;
				case 1:
					//TODO Add user or group
					System.out.println("Would you like to add a (1)user or a (2)group?");
					subMenuOption = keyboard.nextInt();
					keyboard.nextLine();
					//Add user
					if(subMenuOption == 1){
						System.out.println("Enter user's first name: ");
						tempFName = keyboard.nextLine();
						System.out.println("Enter user's last name: ");
						tempLName = keyboard.nextLine();
						System.out.println("Enter user's ID: ");
						tempID = keyboard.nextLine();
						System.out.println("Enter user's default group: ");
						tempGroupString = keyboard.nextLine();
						System.out.println("Enter user's password(nothing if no password): ");
						tempPassword = keyboard.nextLine();
						tempGroup = myUserDatabase.getGroup(tempGroupString);
						if (tempGroup == null)
							System.out.println("Group with specified name does not exist, creating user with no default group.");
						myUserDatabase.addUser(new User(tempFName, tempLName, tempID, tempGroup, tempPassword));
					}
					//Add Group
					else if(subMenuOption == 2) {
						System.out.println("Enter group's name: ");
						tempFName = keyboard.nextLine();
						tempGroup = new Group(tempFName);
						myUserDatabase.addGroup(tempGroup);
					}
					//Invalid entry
					else {
						System.out.println("Invalid menu option, try again!");				
					}
					break;
				case 2:
					//TODO Remove user or group
					System.out.println("Would you like to remove a (1)user or a (2)group?");
					subMenuOption = keyboard.nextInt();
					keyboard.nextLine();
					//Remove user
					if(subMenuOption == 1) {
						System.out.println("Enter the user's ID: ");
						myUserDatabase.removeUser(myUserDatabase.getUser(keyboard.nextLine()));
					}
					//Remove group
					else if(subMenuOption == 2) {
						System.out.println("Enter the group's name: ");
						myUserDatabase.removeGroup(myUserDatabase.getGroup(keyboard.nextLine()));
					}
					//Invalid entry
					else {
						System.out.println("Invalid menu option, try again!");
					}
					break;
				case 3:
					//TODO View user(s) or group(s)
					//TODO Implement authorization
					System.out.println("1: List all users"); //tested w/out auth
					System.out.println("2: List all groups"); //tested w/out auth
					System.out.println("3: List user's information"); //tested w/out auth
					System.out.println("4: List group's information");//tested w/out auth
					subMenuOption = keyboard.nextInt();
					keyboard.nextLine();
					switch(subMenuOption) {
					default:
						System.out.println("Invalid  menu option, returning to main menu!");
						break;
					case 1:
						//List all users
						System.out.println("All users:/n");
						for(int i = 0; i < myUserDatabase.numUsers; i++) {
							System.out.println(myUserDatabase.users.getNext().toString());
						}
						break;
					case 2:
						//List all groups
						System.out.println("All groups: ");
						for(int i = 0; i < myUserDatabase.numGroups; i++) {
							System.out.println(myUserDatabase.groups.getNext().getName());
						}
						break;
					case 3:
						//List user's information
						System.out.println("Enter user's ID: ");
						tempUser = myUserDatabase.getUser(keyboard.nextLine());
						if (tempUser == null) {
							System.out.println("User does not exist in database!");
						}else {
							System.out.println(tempUser.toString());
						}
						break;
					case 4:
						//List group's information
						System.out.println("Enter group's name: ");
						tempGroup = myUserDatabase.getGroup(keyboard.nextLine());
						if (tempGroup == null) {
							System.out.println("Group does not exist in database!");
						}else {
							System.out.println(tempGroup.toString());
						}
						break;
					}
					break;
				case 4:
					//TODO Add user to group
					System.out.println("Enter user ID: ");
					tempUser = myUserDatabase.getUser(keyboard.nextLine());
					if(tempUser == null) {
						System.out.println("User with specified ID does not exist");
						break;
					}
					System.out.println("Enter group name: ");
					tempGroup = myUserDatabase.getGroup(keyboard.nextLine());
					if(tempGroup == null) {
						System.out.println("group with specified name does not exist");
						break;
					}
					if(myUserDatabase.joinGroup(tempUser, tempGroup))
						System.out.println(tempUser.getFullName() + " was added to " + tempGroup.getName());
					else
						System.out.println(tempUser.getFullName() + " was not added to " + tempGroup.getName());
					break;
				case 5:
					//TODO Add/Remove or change database/user password
					System.out.println("Would you like to change a (1)user or (2)database password?");
					subMenuOption = keyboard.nextInt();
					keyboard.nextLine();
					System.out.println("Enter old password(none if enabling password): ");
					oldPassword = keyboard.nextLine();
					System.out.println("Enter new password(none if disabling password): ");
					newPassword = keyboard.nextLine();
					//Change user password
					if(subMenuOption == 1) {
						System.out.println("Enter the ID of the user you would like to change the password for: ");
						tempID = keyboard.nextLine();
						tempUser = myUserDatabase.getUser(tempID);
						tempUser.changePassword(oldPassword, newPassword);
					}
					//Change database password
					else if(subMenuOption == 2) {
						myUserDatabase.changePassword(oldPassword, newPassword);
					}
					//Invalid entry
					else {
						System.out.println("Invalid menu option, try again!");
					}
					break;
				case 6:
					//TODO Save database
					System.out.println("Would you like to change the database name from " + databaseName + " before saving?(y/n) ");
					ynQuery = keyboard.nextLine();
					if(ynQuery.equalsIgnoreCase("y")){
						System.out.println("Enter the new database name: ");
						databaseName = keyboard.nextLine();
					}else if(!ynQuery.equalsIgnoreCase("n")) {
						System.out.println("Invalid selection, returning to main menu!");
						break;
					}
					try {
						myUserDatabase.writeToFile(databaseName);
					} catch (IOException e) {
						e.printStackTrace();
					}
						break;
				case 7:
					//TODO Change database name
					System.out.println("Enter new database name: ");
					databaseName = keyboard.nextLine();
					break;
				case 8:
					//TODO Exit
					break;
			}
			System.out.println();
		}while (mainMenuOption != 8);		
	}
}
	



