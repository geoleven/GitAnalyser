package parser;

import java.util.LinkedList;

public class BranchInfo {

	public String bName = null;
	public String bDate = null;
	public LinkedList<BranchCommits> bCommits = null;
	public String bLastEdit = null;
	
	public BranchInfo(String bName, String bDate, LinkedList<BranchCommits> bCommits, String bLastEdit) {
		this.bName = bName;
		this.bDate = bDate;
		this.bCommits = bCommits;
		this.bLastEdit = bLastEdit;
	}
	
	public BranchInfo(String bName, String bLastEdit) {
		this.bName = bName;
		this.bLastEdit = bLastEdit;
		this.bCommits = new LinkedList<BranchCommits>();
	}
	
	public boolean isInitial() {
		return (bDate == null) ? true : false;
	}
}
