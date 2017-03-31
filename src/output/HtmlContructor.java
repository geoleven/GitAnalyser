package output;

import java.util.HashMap;

import com.google.common.collect.Table;

import parser.BranchInfo;
import parser.PackageReturn;

public class HtmlContructor {
	
	private int totalFiles;
	private long totalLines;
	private int totalBranches;
	private int totalTags;
	private int totalAuthors;
	private HashMap <String, BranchInfo> brancInfo;
	private PackageReturn commitsPrecent;
	private Table<String, String, Double> commitsPerBranchPerAuthorPercent;
	private HashMap <String, Double> comPerDayPerAuth;
	private HashMap <String, Double> comPerWeekPerAuth;
	private HashMap <String, Double> comPerMonthPerAuth;
	private HashMap <String, Double> linesAddPerAuthPercent;
	private HashMap <String, Double> linesRemPerAuthPercent;
	private HashMap <String, Double> linesEdtPerAuthPercent;
	
	private static final String HEAD = "<!DOCTYPE html>\n<html>\n<head>\n<title>Git Analyser</title>\n<head>";
	private String body1 = new String("<body>\n");
	private String body2 = new String("</body>\n</html>");
	
	public String index = null;
	public String branches = null;
	public String percentages = null;
	
	
	public HtmlContructor(int totalFiles, long totalLines, int totalBranches, int totalTags, int totalAuthors, 
			HashMap<String, BranchInfo> brancInfo, PackageReturn commitsPrecent, Table<String, String, Double> commitsPerBranchPerAuthorPercent, 
			HashMap<String, Double> comPerDayPerAuth, HashMap<String, Double> comPerWeekPerAuth, HashMap<String, Double> comPerMonthPerAuth, 
			HashMap <String, Double> linesAddPerAuthPercent, HashMap <String, Double> linesRemPerAuthPercent, HashMap <String, Double> linesEdtPerAuthPercent) {
		this.totalFiles = totalFiles;
		this.totalLines = totalLines;
		this.totalBranches = totalBranches;
		this.totalTags = totalTags;
		this.totalAuthors = totalAuthors;
		this.brancInfo = brancInfo;
		this.commitsPrecent = commitsPrecent;
		this.commitsPerBranchPerAuthorPercent = commitsPerBranchPerAuthorPercent;
		this.comPerDayPerAuth = comPerDayPerAuth;
		this.comPerWeekPerAuth = comPerWeekPerAuth;
		this.comPerMonthPerAuth = comPerMonthPerAuth;
		this.linesAddPerAuthPercent = linesAddPerAuthPercent;
		this.linesRemPerAuthPercent = linesRemPerAuthPercent;
		this.linesEdtPerAuthPercent = linesEdtPerAuthPercent;
	}
	
	private void createIndex() {
		
	}
	
	public String render() {
		String result = HEAD.concat(body1).concat(body2);
		System.out.println(result);
		return result;
	}
	
	
}
