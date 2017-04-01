package parser;

public class BranchCommits {
	public String id = null;
	public String message = null;
	public String date = null;
	public String author = null;
	public String tag = null;
	
	public BranchCommits(String id, String message, String date, String author, String tag){
		this.id = id;
		this.message = message;
		this.date = date;
		this.author = author;
		this.tag = tag;
	}
}
