package output;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Table;

import parser.BranchInfo;
import parser.PackageReturn;

public class HtmlContructor {
	
	private String output;
	private File outputFile;
	private int totalFiles;
	private long totalLines;
	private int totalBranches;
	private int totalTags;
	private int totalAuthors;
	private HashMap <String, BranchInfo> branchInfo;
	private PackageReturn commitsPrecent;
	private Table<String, String, Double> commitsPerBranchPerAuthorPercent;
	private HashMap <String, Double> comPerDayPerAuth;
	private HashMap <String, Double> comPerWeekPerAuth;
	private HashMap <String, Double> comPerMonthPerAuth;
	private HashMap <String, Double> linesAddPerAuthPercent;
	private HashMap <String, Double> linesRemPerAuthPercent;
	private HashMap <String, Double> linesEdtPerAuthPercent;
	
	private static final String HEAD = Head.getHead();
	private static final String MENU = UpperMenu.getMenu();
	private static final String FOOTER = Footer.getFooter();
	
	public String index = null;
	public String branches = null;
	public String percentages = null;
	
	
	public HtmlContructor(String output, File outputFile, int totalFiles, long totalLines, int totalBranches, int totalTags, int totalAuthors, 
			HashMap<String, BranchInfo> branchInfo, PackageReturn commitsPrecent, Table<String, String, Double> commitsPerBranchPerAuthorPercent, 
			HashMap<String, Double> comPerDayPerAuth, HashMap<String, Double> comPerWeekPerAuth, HashMap<String, Double> comPerMonthPerAuth, 
			HashMap <String, Double> linesAddPerAuthPercent, HashMap <String, Double> linesRemPerAuthPercent, HashMap <String, Double> linesEdtPerAuthPercent) {
		this.output = output;
		this.outputFile = outputFile;
		this.totalFiles = totalFiles;
		this.totalLines = totalLines;
		this.totalBranches = totalBranches;
		this.totalTags = totalTags;
		this.totalAuthors = totalAuthors;
		this.branchInfo = branchInfo;
		this.commitsPrecent = commitsPrecent;
		this.commitsPerBranchPerAuthorPercent = commitsPerBranchPerAuthorPercent;
		this.comPerDayPerAuth = comPerDayPerAuth;
		this.comPerWeekPerAuth = comPerWeekPerAuth;
		this.comPerMonthPerAuth = comPerMonthPerAuth;
		this.linesAddPerAuthPercent = linesAddPerAuthPercent;
		this.linesRemPerAuthPercent = linesRemPerAuthPercent;
		this.linesEdtPerAuthPercent = linesEdtPerAuthPercent;
	}
	
	private String getIndexBody() {
		return IndexBody.getBody(totalFiles, totalLines, totalBranches, totalTags, totalAuthors);
	}
	
	private String getBranchesBody() {
		return BranchesBody.getBody(branchInfo);
	}
	
	private void copyRescources() {
		File sourceFiles = new File("./webfiles/");
		try {
			FileUtils.copyDirectory(sourceFiles, outputFile);
		} catch (IOException e) {
//			e.printStackTrace();
			System.err.println("Could not properly copy html rescource files!");
		}
	}
	
	public void render() {
		copyRescources();
		String index = HEAD + MENU + getIndexBody() + FOOTER;
		String branches = HEAD + MENU + getBranchesBody() + FOOTER;
			try {
				FileUtils.writeStringToFile(new File(output + ((output.endsWith("/") || output.endsWith("\\")) ? "index.html" : "/index.html")) , index, (Charset)null);
				FileUtils.writeStringToFile(new File(output + ((output.endsWith("/") || output.endsWith("\\")) ? "branches.html" : "/branches.html")) , branches, (Charset)null);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Could not write index.html!");
			}
	}
	
	
}
