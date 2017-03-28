package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import base.ArgumentParser;

import org.apache.commons.io.IOUtils;

public class parse {
	private String input = null;
	private String output = null;
	private File inputFile = null;
	private File outputFile = null;
	private Process currentProcess = null;
	private ProcessBuilder pb = null; 
	private BufferedReader processOutpoutStream = null;
	private String processOutput = null;
	
	
	public parse(ArgumentParser ap) {	
		this.input = ap.input;
		this.output = ap.output;
		this.inputFile = ap.inputFile;
		this.outputFile = ap.outputFile;
	}
	
	public int fileCount() {
		pb = new ProcessBuilder("git ls-files");
		pb.directory(inputFile);
		try {
			processOutput = IOUtils.toString(pb.start().getInputStream(), (Charset)null);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not start or get stream!");
		}
		return 0;
	}
	
}
