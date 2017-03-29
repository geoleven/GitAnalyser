package parser;

import java.util.HashMap;

public class PackageReturn {
	public int commits = 0;
	public HashMap<String, Double> commitsPerAuthor = null;
	public HashMap<String, Double> commitsPerBranch = null;
	public HashMap<String, AuthorAndBranch> commitsPerBrPerAu = null;
	
	public PackageReturn(int commits, HashMap<String, Double> commitsPerAuthor, HashMap<String, Double> commitsPerBranch, HashMap<String, AuthorAndBranch> commitsPerBrPerAu) {
		this.commits = commits;
		this.commitsPerAuthor = commitsPerAuthor;
		this.commitsPerBranch = commitsPerBranch;
		this.commitsPerBrPerAu = commitsPerBrPerAu;
	}
	
	public PackageReturn(int commits) {
		this.commits = commits;
		this.commitsPerAuthor = new HashMap<String, Double>();
		this.commitsPerBranch = new HashMap<String, Double>();
		this.commitsPerBrPerAu = new HashMap<String, AuthorAndBranch>();
	}
	
	public PackageReturn(){
		this.commits = 0;
		this.commitsPerAuthor = new HashMap<String, Double>();
		this.commitsPerBranch = new HashMap<String, Double>();
		this.commitsPerBrPerAu = new HashMap<String, AuthorAndBranch>();
	}
}
