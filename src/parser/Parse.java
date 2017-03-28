package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import base.ArgumentParser;

import org.apache.commons.io.IOUtils;

public class Parse {
	
	private static final String GIT_DIR = "C:\\Program Files\\Git\\bin\\";
	private static final String GIT_CMD = "git";

	
	private String input = null;
	private String output = null;
	private File inputFile = null;
	private File outputFile = null;
	private Process currentProcess = null;
	private ProcessBuilder pb = null; 
	private BufferedReader processOutpoutStream = null;
	private String processOutput = null;
	//private String[] commandArray = null;
	
	public Parse(ArgumentParser ap) {	
		this.input = ap.input;
		this.output = ap.output;
		this.inputFile = ap.inputFile;
		this.outputFile = ap.outputFile;
	}
	
	public int fileCount() {
		
		String args = new String("ls-files");
		String[] commandArray = {GIT_CMD, args};
		pb = new ProcessBuilder(commandArray);
		pb.directory(inputFile);
		try {
			processOutput = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
			String[] split = processOutput.split("\r\n|\r|\n");
			//System.out.println(processOutput);
			System.out.println("The number of files is " + split.length);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not start or get stream!");
		}
		return 0;
	}
	
}
