package parser;

public class BranchCommits {
	public String id = null;
	public String message = null;
	public String tag = null;
	
	public BranchCommits(String id, String message, String tag){
		this.id = id;
		this.message = message;
		this.tag = tag;
	}
}
