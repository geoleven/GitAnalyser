package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

import base.ArgumentParser;

import org.apache.commons.io.IOUtils;

public class Parse {
	
	private static final boolean ALLDELIMETERS = true;
	
	private static final String GIT_DIR = "C:\\Program Files\\Git\\bin\\";
	private static final String GIT_CMD = "git";

	
	private String input = null;
	private String output = null;
	private File inputFile = null;
	private File outputFile = null;
	private ProcessBuilder pb = null; 
	private String filels = null;
	
	public Parse(ArgumentParser ap) {	
		this.input = ap.input;
		this.output = ap.output;
		this.inputFile = ap.inputFile;
		this.outputFile = ap.outputFile;
	}
	
//	private static int countLines(String filename) {
//	    try {
//	    	InputStream is = new BufferedInputStream(new FileInputStream(filename));
//	        byte[] c = new byte[1024];
//	        int count = 0;
//	        int readChars = 0;
//	        boolean empty = true;
//	        while ((readChars = is.read(c)) != -1) {
//	            empty = false;
//	            for (int i = 0; i < readChars; ++i) {
//	            	if (ALLDELIMETERS) {
//		                if (c[i] == '\n' || c[i] == '\r') {	           
//		                    ++count;
//		                    if (c[i] == '\r' && (i+1) < readChars && c[i+1] == '\n')
//		                    	++i;
//		                }
//	            	} else {
//		                if (c[i] == '\n') {	           
//		                    ++count;
//		                }
//	            	}
//	            }
//	        }
//	        is.close();
//	        return (count == 0 && !empty) ? 1 : count;
//	    } catch(IOException e) {
//	       e.printStackTrace();
//	       System.err.println("TotalLinesCount could not open some file!");
//	       return 0;
//	    }
//	}
	
	
////	Toooooooooo slow
//	private static int countLines(String filename) {
//	    LineNumberReader reader = null;
//	    try {
//	        reader = new LineNumberReader(new FileReader(filename));
//	        while ((reader.readLine()) != null);
//	        return reader.getLineNumber();
//	    } catch (Exception ex) {
//	        return -1;
//	    } finally { 
//	        if(reader != null)
//				try {
//					reader.close();
//				} catch (IOException e) {
//					System.err.println("Could not close file in countLines!");
//					e.printStackTrace();
//				}
//	    }
//	}
	
	

//	3rd try XD
	private static int countLines(String filename) {
		int lines = 0;
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
			//System.out.println(filels);
			System.out.println("The number of files is: " + filelsSplit.length);
			return filelsSplit.length;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("filesCount could not start or get stream!");
			return -1;
		}
	}
	
	public int totalLinesCount() {
		if (filels == null)
			if (filesCount() == -1)
				return -1;
		String[] filelsSplit = filels.split("\r\n|\r|\n");
		int linesSum = 0;
		for (String singleFile : filelsSplit)
			linesSum += countLines(input + singleFile);
		System.out.println("The total number of lines is: " + linesSum);
		return linesSum;
	}
	
	private String getAllBranches() {
		String args = new String("branch");
		String[] commandArray = {GIT_CMD, args};
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
		String branches = getAllBranches();
		String[] branchesSplit = branches.split("\r\n|\r|\n");
		System.out.println("The number of branches is: " + branchesSplit.length);
		return branchesSplit.length;
	}
	
	public int tagsCount() {
		String args = new String("tag");
		String[] commandArray = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String tags = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String[] tagsSplit = tags.split("\r\n|\r|\n");
			System.out.println("The number of tags is: " + tagsSplit.length);
			return tagsSplit.length;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("tagsCount could not start or get stream!");
			return -1;
		}
	}
	
	public int commitersCount() {
		String arg1 = new String("log");
		String arg2 = new String("--pretty=s --format=\"%an\"");
		String[] commandArray = {GIT_CMD, arg1, arg2};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			pb.start();
			InputStream is = pb.start().getInputStream();
			String commiters = IOUtils.toString(is, (Charset)null);
			String[] commitersSplit = commiters.split("\r\n|\r|\n");
			HashMap<String, Integer> commitesCount = new HashMap<String, Integer>();
			for (String commiter : commitersSplit) {
				Integer temp = commitesCount.get(commiter);
				if(temp != null)
					commitesCount.put(commiter, commitesCount.get(commiter) + 1);
				else
					commitesCount.put(commiter, 1);
			}
			System.out.println("The number of commiters is: " + commitesCount.size());
			return commitesCount.size();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("commitersCount could not start or get stream!");
			return -1;
		}
	}
	
	
	private String getAllCommitsOlderToNewer() {
		String arg1 = new String("log");
		String arg2 = new String("--all");
		String arg3 = new String("--format=\"%H\"");
		String arg4 = new String("--reverse");
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, arg4};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			//return IOUtils.toString(pb.start().getInputStream(), (Charset)null);
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
			//return IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getAllBranchesContainingCommit could not start or get stream or something!");
			return "";
		}
	}
	
	private String getCommitMessage(String commit) {
		String arg1 = "show";
		String arg2 = "-s";
		String arg3 = "--format=%B";
		String[] commandArray = {GIT_CMD, arg1, arg2, arg3, commit};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			//return IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String ret = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getCommitMessage could not start or get stream or something!");
			return "";
		}
	}
	
	public HashMap<String, BranchInfo> getInfoBranches() {
		HashMap<String, BranchInfo> branchInfoMap = new HashMap<String, BranchInfo>();
			
		String branches = getAllBranches();
		String[] branchesSplit = branches.split("\r\n|\r|\n");
		for (String br : branchesSplit) {
			branchInfoMap.put(br, new BranchInfo(br));
		}
		
		
		String arg4 = new String("--reverse");
		String arg5 = "show";
		String arg6 = "-s";
		String arg7 = "describe";
		String arg8 = "--exact-match";
		

		try {
//			Παίρνουμε όλα τα commits ώστε να τα τοποθετήσουμε μετά ένα ένα
			String allCommits = getAllCommitsOlderToNewer();
//			System.out.println(allCommits);
			BufferedReader reader = new BufferedReader(new StringReader(allCommits));
			String contOut = null;
			BranchInfo curBrIn = null;
			String commit = null;
//			Gia kathe commit koitamai oles tis alusides stis opoies anoikei kai to topothetoume
			while ((commit = reader.readLine()) != null) {
				
//				Pairnoume oles tis alusides
				contOut = getAllBranchesContainingCommit(commit);
				String[] contOutsplit = contOut.split("\r\n|\r|\n");
				
//				Pairnoume ta info tou commit gia na ta exoume se ola ta for
				String commitFullMessage = getCommitMessage(commit);
				
//				Kai ta exact tags tou commit
				String[] commandArray6 = {GIT_CMD, arg7, arg8, commit};
				ProcessBuilder pb5 = new ProcessBuilder(commandArray6);
				pb5.directory(inputFile);
				String curtag = IOUtils.toString(pb5.start().getInputStream(), (Charset)null);
				if (curtag.startsWith("fatal: no tag exactly matches '" + commit + "'"))
					curtag = null;
				
				
				for (String sbr : contOutsplit) {
					//Tha eprepe na kanw elegxo ab yparxei to branch alla einai poly kako gia tin taxutita
					curBrIn = branchInfoMap.get(sbr);
					// Ean einai to prwto commit tis alusidas orizoume to creation date tou san creation kai tis alusidas
					if (curBrIn.isInitial()) {
						arg4 = "--format=%ci";
						String[] commandArray5 = {GIT_CMD, arg5, arg6, arg4, commit};
						ProcessBuilder pb4 = new ProcessBuilder(commandArray5);
						pb4.directory(inputFile);
						curBrIn.bDate = IOUtils.toString(pb4.start().getInputStream(), (Charset)null);
					}
					curBrIn.bCommits.add(new BranchCommits(commit, commitFullMessage, curtag));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getInfoBranches could not start or get stream or something!");
			return null;
		}
		
		return branchInfoMap;		
	}
	
	
	
	
	
	
	
	public PackageReturn percentages() {
		
		PackageReturn ret = new PackageReturn();
//		String arg1 = new String("log");
//		String arg2 = new String("--all");
//		String arg3 = new String("--format=\"%H\"");
//		String arg4 = new String("--reverse");
		
//		String[] commandArray1 = {GIT_CMD, arg1, arg2, arg3, arg4};
//		pb = new ProcessBuilder(commandArray1);
//		pb.directory(inputFile);
		

//			try {
				String commits = getAllCommitsOlderToNewer();
				String[] commitsSplit = commits.split("\r\n|\r|\n");
				int commitsSum = commitsSplit.length;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		
		return ret;
	}
	
	
}
