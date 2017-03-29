package parser;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
//	private Process currentProcess = null;
	private ProcessBuilder pb = null; 
//	private BufferedReader processOutpoutStream = null;
	private String filels = null;
	//private String[] commandArray = null;
	
	public Parse(ArgumentParser ap) {	
		this.input = ap.input;
		this.output = ap.output;
		this.inputFile = ap.inputFile;
		this.outputFile = ap.outputFile;
	}
	
	private static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	            	if (ALLDELIMETERS) {
		                if (c[i] == '\n' || c[i] == '\r') {	           
		                    ++count;
		                    if (c[i] == '\r' && (i+1) < readChars && c[i+1] == '\n')
		                    	++i;
		                }
	            	} else {
		                if (c[i] == '\n') {	           
		                    ++count;
		                }
	            	}
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
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
		for (String singleFile : filelsSplit){
			try {
				linesSum += countLines(input + singleFile);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("TotalLinesCount could not open some file!");
				return -1;
			}
		}
		System.out.println("The total number of lines is: " + linesSum);
		return linesSum;
	}
	
	public int branchCount() {
		String args = new String("branch");
		String[] commandArray = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			String branches = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String[] branchesSplit = branches.split("\r\n|\r|\n");
			System.out.println("The number of branches is: " + branchesSplit.length);
			return branchesSplit.length;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("branchCount could not start or get stream!");
			return -1;
		}
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
	
	
	
	
	
	
	public HashMap<String, BranchInfo> getInfoBranches() {
		HashMap<String, BranchInfo> ret = new HashMap<String, BranchInfo>();
			
		
		
		
		
		String args = new String("branch");
		String[] commandArray1 = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray1);
		pb.directory(inputFile);
		try {
			String branches = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String[] branchesSplit = branches.split("\r\n|\r|\n");
			for (String br : branchesSplit) {
				ret.put(br, new BranchInfo(br));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getInfoBranches could not start or get stream for branches!");
		}
		
		
		
		
		
		
		String arg1 = new String("log");
		String arg2 = new String("--all");
		String arg3 = new String("--format=\"%H\"");
		String arg4 = new String("--reverse");
		String arg5 = "show";
		String arg6 = "-s";
		String arg7 = "describe";
		String arg8 = "--exact-match";
		
		String[] commandArray2 = {GIT_CMD, arg1, arg2, arg3, arg4};
		pb = new ProcessBuilder(commandArray2);
		pb.directory(inputFile);
		try {
			// Pairnoume OLA ta commits gia na ta valoume ena ena
			String commits = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
//			System.out.println(commits);
			BufferedReader reader = new BufferedReader(new StringReader(commits));
			arg1 = "branch";
			arg2 = "--contains";
			String contOut = null;
			BranchInfo curBrIn = null;
			//Gia kathe commit koitamai oles tis alusides stis opoies anoikei kai to topothetoume
			while ((arg3 = reader.readLine()) != null) {
				
				//Pairnoume oles tis alusides
				String[] commandArray3 = {GIT_CMD, arg1, arg2, arg3};
				ProcessBuilder pb2 = new ProcessBuilder(commandArray3);
				pb2.directory(inputFile);
				contOut = IOUtils.toString(pb2.start().getInputStream(), (Charset)null);
				String[] contOutsplit = contOut.split("\r\n|\r|\n");
				
				//Pairnoume ta info tou commit gia na ta exoume se ola ta for
				arg4 = "--format=%B";
				String[] commandArray4 = {GIT_CMD, arg5, arg6, arg4, arg3};
				ProcessBuilder pb3 = new ProcessBuilder(commandArray4);
				pb3.directory(inputFile);
				String commitFullMessage = IOUtils.toString(pb3.start().getInputStream(), (Charset)null);
				
				//Kai ta exact tags tou commit
				String[] commandArray6 = {GIT_CMD, arg7, arg8, arg3};
				ProcessBuilder pb5 = new ProcessBuilder(commandArray6);
				pb5.directory(inputFile);
				String curtag = IOUtils.toString(pb5.start().getInputStream(), (Charset)null);
				if (curtag.startsWith("fatal: no tag exactly matches '" + arg3 + "'"))
					curtag = null;
				
				
				for (String sbr : contOutsplit) {
					//Tha eprepe na kanw elegxo ab yparxei to branch alla einai poly kako gia tin taxutita
					curBrIn = ret.get(sbr);
					// Ean einai to prwto commit tis alusidas orizoume to creation date tou san creation kai tis alusidas
					if (curBrIn.isInitial()) {
						arg4 = "--format=%ci";
						String[] commandArray5 = {GIT_CMD, arg5, arg6, arg4, arg3};
						ProcessBuilder pb4 = new ProcessBuilder(commandArray5);
						pb4.directory(inputFile);
						curBrIn.bDate = IOUtils.toString(pb4.start().getInputStream(), (Charset)null);
					}
					curBrIn.bCommits.add(new BranchCommits(arg3, commitFullMessage, curtag));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("getInfoBranches could not start or get stream or something!");
			return null;
		}
		
		return ret;		
	}
	
	
	
	
	
	
	
	public PackageReturn percentages() {
		
		PackageReturn ret = new PackageReturn();
		String arg1 = new String("log");
		String arg2 = new String("--all");
		String arg3 = new String("--format=\"%H\"");
		String arg4 = new String("--reverse");
		
		String[] commandArray1 = {GIT_CMD, arg1, arg2, arg3, arg4};
		pb = new ProcessBuilder(commandArray1);
		pb.directory(inputFile);
		
			;
			try {
				String commits = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
				String[] commitsSplit = commits.split("\r\n|\r|\n");
				int commitsSum = commitsSplit.length;
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	
}
