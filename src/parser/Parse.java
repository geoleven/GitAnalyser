package parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

import base.ArgumentParser;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Parse {	
	private static final String GIT_CMD = "git";
	
	private String input = null;
//	private String output = null;
	private File inputFile = null;
//	private File outputFile = null;
	private ProcessBuilder pb = null; 
	private String filels = null;
	private HashMap<String, Integer> commitsPerAuthCount = null;
	private Table<String, String, Integer> commitsPerBranchPerAuthor = HashBasedTable.create();
	private Table<String, String, Double> commitsPerBranchPerAuthorPercent = HashBasedTable.create();
	public HashMap <String, Double> comPerDayPerAuth = new HashMap <String, Double>();
	public HashMap <String, Double> comPerWeekPerAuth = new HashMap <String, Double>();
	public HashMap <String, Double> comPerMonthPerAuth = new HashMap <String, Double>();
	public HashMap <String, Double> linesAddPerAuth = new HashMap <String, Double>();
	public HashMap <String, Double> linesRemPerAuth = new HashMap <String, Double>();
	public HashMap <String, Double> linesEdtPerAuth = new HashMap <String, Double>();
	
	public Parse(ArgumentParser ap) {	
		this.input = ap.input;
//		this.output = ap.output;
		this.inputFile = ap.inputFile;
//		this.outputFile = ap.outputFile;
	}

//	3rd try XD
	private static long countLines(String filename) {
		long lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while (reader.readLine() != null) 
				lines++;
			reader.close();
		} catch (IOException e) {
			System.err.println("Could not close file in countLines!");
			e.printStackTrace();
		}
		return lines;
	}
	
	
	public int filesCount() {
		String args = new String("ls-files");
		String[] commandArray = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			filels = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String[] filelsSplit = filels.split("\r\n|\r|\n");
			return filelsSplit.length;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("filesCount could not start or get stream!");
			return -1;
		}
	}
	
	public long totalLinesCount() {
		if (filels == null)
			if (filesCount() == -1)
				return -1;
		String[] filelsSplit = filels.split("\r\n|\r|\n");
		long linesSum = 0;
		for (String singleFile : filelsSplit)
			linesSum += countLines(input + singleFile);
		return linesSum;
	}
	
	private String getAllBranchesPlusLastCommit() {
		String arg1 = "for-each-ref";
		String arg2 = "refs/heads/";
		String arg3 = "--format=%(refname:short)%09%(objectname)";
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String branches = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return branches;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getAllBranches could not start or get stream!");
			return "";
		}
	}
	
	
	public int branchCount() {
		String branches = getAllBranchesPlusLastCommit();
		String[] branchesSplit = branches.split("\r\n|\r|\n");
		return branchesSplit.length;
	}
	
	private String getAllTags(){
		String args = new String("tag");
		String[] commandArray = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String tags = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return tags;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("tagsCount could not start or get stream!");
			return null;
		}
	}
	
	public int tagsCount() {
		String tags = getAllTags();
		String[] tagsSplit = tags.split("\r\n|\r|\n");
		return tagsSplit.length;
	}
	
	public int commitsPerAuthorCount() {
		String arg1 = "log";
		String arg2 = "--all";
		String arg3 = "--pretty=s";
		String arg4 = "--format=%aN";		//Ή %an για να μην κάνει rescept το .mailmap
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, arg4};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			pb.start();
			InputStream is = pb.start().getInputStream();
			String commiters = IOUtils.toString(is, (Charset)null);
			String[] commitersSplit = commiters.split("\r\n|\r|\n");
			commitsPerAuthCount = new HashMap<String, Integer>();
			for (String commiter : commitersSplit) {
				Integer temp = commitsPerAuthCount.get(commiter);
				if(temp != null)
					commitsPerAuthCount.put(commiter, commitsPerAuthCount.get(commiter) + 1);
				else
					commitsPerAuthCount.put(commiter, 1);
			}
			return commitsPerAuthCount.size();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("commitersCount could not start or get stream!");
			return -1;
		}
	}
	
	
	private String getAllCommitsOlderToNewer() {
		String arg1 = new String("log");
		String arg2 = new String("--all");
		String arg3 = new String("--format=%H");
		String arg4 = new String("--reverse");
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, arg4};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getAllCommitsOlderToNewer could not start or get stream or something!");
			return "";
		}
	}
	
	private String getAllBranchesContainingCommit(String commit) {
		String arg1 = new String ("branch");
		String arg2 = new String("--contains");
		String[] commandArray = {GIT_CMD, arg1, arg2, commit};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getAllBranchesContainingCommit could not start or get stream or something!");
			return "";
		}
	}
	
	private String getCommitMessageOrDateOrAuthor(String commit, int type) {
		String arg1 = "show";
		String arg2 = "-s";
		String arg3;
		if (type == 0)
			arg3 = "--format=%B";
		else if (type == 1)
			arg3 = "--format=%ci";
		else if (type == 2)
			arg3 = "--format=%aN";
		else 
			arg3 = "--format=%ct";
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, commit};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			if (ret.endsWith("\n"))
				ret = ret.substring(0, ret.lastIndexOf("\n"));
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getCommitMessage could not start or get stream or something!");
			return "";
		}
	}
	
	private String getPointedCommitOfTag(String tag) {
		String arg1 = "rev-list";
		String arg2 = "-n";
		String arg3 = "1";
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, tag};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			//return IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
//			return ret.split("\n")[0];
			if (ret.endsWith("\n"))
				ret = ret.substring(0, ret.lastIndexOf("\n"));
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getCommitMessage could not start or get stream or something!");
			return "";
		}
	}
	
	
	
	public HashMap<String, BranchInfo> getInfoBranches() {
		HashMap<String, BranchInfo> branchInfoMap = new HashMap<String, BranchInfo>();
			
		String branches = getAllBranchesPlusLastCommit();
		String[] branchesSplit = branches.split("\r\n|\r|\n");
		for (String br : branchesSplit) {
			branchInfoMap.put(br.substring(0, br.indexOf('\t')), new BranchInfo(br.substring(0, br.indexOf('\t')), br.substring(br.indexOf('\t')+1)));
		}
	

		try {
//			Παίρνουμε όλα τα commits ώστε να τα τοποθετήσουμε μετά ένα ένα
			String allCommits = getAllCommitsOlderToNewer();
			BufferedReader reader = new BufferedReader(new StringReader(allCommits));
			String contOut = null;
			BranchInfo curBrIn = null;
			String commit = null;
			
			HashMap<String, String> tagToCommitMap = new HashMap<String, String>();
			for (String tag : getAllTags().split("\r\n|\r|\n")) {
				if (tag == null || tag == "" || tag == " " || tag == "\n")
					continue;
//				System.out.println("'"+tag+"'");
//				System.out.println("'"+getPointedCommitOfTag(tag)+"'");
				tagToCommitMap.put(getPointedCommitOfTag(tag), tag);
			}
			
//			Gia kathe commit koitamai oles tis alusides stis opoies anoikei kai to topothetoume
			while ((commit = reader.readLine()) != null) {
				
//				Pairnoume oles tis alusides
				contOut = getAllBranchesContainingCommit(commit);
				String[] contOutsplit = contOut.split("\r\n|\r|\n");
				
//				Pairnoume ta info tou commit gia na ta exoume se ola ta for
				String commitFullMessage = getCommitMessageOrDateOrAuthor(commit, 0);
								
				for (String sbr : contOutsplit) {
					if (sbr == null || sbr == "" || sbr == "\n" || sbr == " ")
						continue;
					sbr = sbr.substring(2);
					curBrIn = branchInfoMap.get(sbr);
					if (curBrIn == null)
						continue;
					
					// Ean einai to prwto commit tis alusidas orizoume to creation date tou san creation kai tis alusidas
						if (curBrIn.isInitial()) {																					
							curBrIn.bDate = getCommitMessageOrDateOrAuthor(commit, 1);
						}
						curBrIn.bCommits.add(new BranchCommits(commit, commitFullMessage, tagToCommitMap.get(commit)));
						
						String curAuth = getCommitMessageOrDateOrAuthor(commit, 2);						
						if (commitsPerBranchPerAuthor.get(sbr, curAuth) == null) {
							commitsPerBranchPerAuthor.put(sbr, curAuth, 1);
						} else {
							commitsPerBranchPerAuthor.put(sbr, curAuth, commitsPerBranchPerAuthor.get(sbr, curAuth) + 1);
						}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getInfoBranches could not start or get stream or something!");
			return null;
		}
		
		return branchInfoMap;		
	}

	private int countCommitsOfBranch(String branch) {
		String arg1 = new String("rev-list");
		String arg2 = new String("--count"); 
		String[] commandArray = {GIT_CMD, arg1, arg2, branch};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			if (ret.endsWith("\n"))
				ret = ret.substring(0, ret.lastIndexOf("\n"));
			return Integer.decode(ret).intValue();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getAllCommitsOlderToNewer could not start or get stream or something!");
			return -1;
		}
	}

	
	public PackageReturn percentages() {
		PackageReturn ret = new PackageReturn();
		String commits = getAllCommitsOlderToNewer();
		String[] commitsSplit = commits.split("\r\n|\r|\n");
		ret.commits = commitsSplit.length;
		if (commitsPerAuthCount == null)
			commitsPerAuthorCount();
		commitsPerAuthCount.forEach((author, commitsNum)->ret.commitsPerAuthor.put(author, (double)commitsNum/(double)ret.commits));
		
		int sumCommitsBr = 0;
		String branches = getAllBranchesPlusLastCommit();
		String[] branchesSplit = branches.split("\r\n|\r|\n");
		for (String br : branchesSplit)
			sumCommitsBr += countCommitsOfBranch(br.substring(0, br.indexOf('\t')));
		for (String br : branchesSplit) {
			String brn = br.substring(0, br.indexOf('\t'));
			ret.commitsPerBranch.put(brn, (double)countCommitsOfBranch(brn)/(double)sumCommitsBr);
		}
		
		for (String br : branchesSplit) {
			String brn = br.substring(0, br.indexOf('\t'));
			double comsOfBr = (double)countCommitsOfBranch(brn);
			for (String auth : commitsPerBranchPerAuthor.row(brn).keySet()) {
				commitsPerBranchPerAuthorPercent.put(brn,  auth,  (double)commitsPerBranchPerAuthor.get(brn, auth)/comsOfBr);
			}	
		}		
		return ret;
	}
	
	private long getRepoActiveTime() {
		String commits = getAllCommitsOlderToNewer();
		String oldest = (commits.split("\r\n|\r|\n"))[0];
		long now = (long)(System.currentTimeMillis() / 1000L);
		return now - Long.parseLong(getCommitMessageOrDateOrAuthor(oldest, 3));
	}
	
	public void comsOfAthPerTimeUnitPopulate() {
		long repAcT = getRepoActiveTime();
		double days = (double)repAcT / (double)86400;
		double weeks = (double)repAcT / (double)604800;
		double months = (double)repAcT / (double)2592000;
		
		for (String ath : commitsPerAuthCount.keySet()) {
			int totalComs = commitsPerAuthCount.get(ath);
			comPerDayPerAuth.put(ath, totalComs/days);
			comPerWeekPerAuth.put(ath, totalComs/weeks );
			comPerMonthPerAuth.put(ath, totalComs/months);
		}
	}
	
	public void linesPerAuthorPercentPopulate() {
		
	}
	
	
	public void run() {
		System.out.println("Now running, please wait . . .");
		boolean verbose = true;
		
		if (verbose)
			System.out.println("Counting files . . .");
		int totalFiles = filesCount();
		if (verbose)
			System.out.println("Done.\nCounting lines . . .");
		long totalLines = totalLinesCount();
		if (verbose)
			System.out.println("Done.\nCounting branches . . .");
		int totalBranches = branchCount();
		if (verbose)
			System.out.println("Done.\nCounting tags . . .");
		int totalTags = tagsCount();
		if (verbose)
			System.out.println("Done.\nCounting commiters . . .");
		int totalAuthors = commitsPerAuthorCount();
		if (verbose)
			System.out.println("Done.\nCollecting branches' info . . .");
		HashMap<String, BranchInfo> brancInfo = getInfoBranches();
		if (verbose)
			System.out.println("Done.\nCollecting commits' info . . .");
		PackageReturn commitsPrecent = percentages();
		if (verbose)
			System.out.println("Done.\nCollecting commiters' info . . .");
		comsOfAthPerTimeUnitPopulate();
		if (verbose)
			System.out.println("Done.");
		
		if (verbose) {
			System.out.println("");
			System.out.println("Main files count: " + totalFiles);
			System.out.println("Main total lines count: " + totalLines);
			System.out.println("Main branches count: " + totalBranches);
			System.out.println("Main tags count: " + totalTags);
			System.out.println("Main commiters count: " + totalAuthors);
		
		
			for (BranchInfo bi : brancInfo.values()) {
				System.out.println("\n\n\nBranch info: '" + bi.bName + "' Date: '" + bi.bDate +
						"'\n_______________________________________________________________________");
				for (BranchCommits bc : bi.bCommits){
					System.out.print("Commit: " + bc.id + "\nMessage:" + bc.message + "\nTag: ");
					System.out.println(bc.tag == null ? "None" : bc.tag);
				}
			}
			
			System.out.println("Main commits count: " + commitsPrecent.commits);
			commitsPrecent.commitsPerAuthor.forEach((author, percent)->
			System.out.println("Commiter " + author + " made " + percent + "% of total commits."));
			commitsPrecent.commitsPerBranch.forEach((branch, percent)->
			System.out.println("Branch " + branch + " had " + percent + "% of total commits."));
			for (Map.Entry<String, Map<String, Double>> entry : commitsPerBranchPerAuthorPercent.rowMap().entrySet() ) {
				for (String author : entry.getValue().keySet()) {
					System.out.println("Commiter " + author + " had " + entry.getValue().get(author) + "% of total commits for branch " + entry.getKey() + ".");
				}
			}
			for (String author : comPerDayPerAuth.keySet()) {
				System.out.println("Commiter " + author + " wrote " + comPerDayPerAuth.get(author) + "% of his commits each day.");
				System.out.println("Commiter " + author + " wrote " + comPerWeekPerAuth.get(author) + "% of his commits each week.");
				System.out.println("Commiter " + author + " wrote " + comPerMonthPerAuth.get(author) + "% of his commits each month.");
			}
		}
		
		
	}
	
}
