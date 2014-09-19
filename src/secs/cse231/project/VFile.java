package secs.cse231.project;
import java.net.URI;

public class VFile {
	private String name;
	private String location; //Where the file is located
	private VFileType type;
	
	private String permission; // read/write/execute **Lower Priority**
	private Vfolder parent;
}
