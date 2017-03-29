package parser;

import java.util.LinkedList;

public class BranchInfo {

	public String bName = null;
	public String bDate = null;
	public LinkedList<BranchCommits> bCommits = null;
	
	public BranchInfo(String bName, String bDate, LinkedList<BranchCommits> bCommits) {
		this.bName = bName;
		this.bDate = bDate;
		this.bCommits = bCommits;
	}
	
	public BranchInfo(String bName) {
		this.bName = bName;
		this.bCommits = new LinkedList<BranchCommits>();
	}
	
	public boolean isInitial() {
		return (bDate == null) ? true : false;
	}
}
