package secs.cse231.project;
/*
 * The Vfolder is a linked list for all of the files (VFile) in the folder
 */
public class VFolder {
	private VFile files; //Head of the list
	private String permission; //Folder permission **Higher Priority**
	private int numFiles;
	public int fileID;
	/**
	  * Add a file to a folder
	  * @param file: the file object
	  * @return true: successful; false: not successful
	  */
	public boolean addFile (VFile file) {
		
		return false;
	}
	/**
	  * Remove a file from a folder
	  * @param file: the file object
	  * @return true: successful; false: not successful
	  */
	public boolean deleteFile (VFile file) {
		
		return false;
	}
	
	public int getNumFiles() {
		return numFiles;
	}
	
	public boolean contains(VFile file) {
		return false;
	}
	
	public boolean contains(String name) {
		return false;
	}
	
	public int getFileID(VFile file) {
		return -1;
	}
	
	public int getFileID(String name) {
		return -1;
	}
}
