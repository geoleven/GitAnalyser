package base;

import java.io.File;

public class ArgumentParser {
	public File inputFile = null;
	public File outputFile = null;
	public String input = null;
	public String output = null;
	
	public ArgumentParser(){
	}
	
	boolean parseArgs(String[] args) {
		if (args.length != 2) {
			System.err.println("Not enough arguments given. Now exiting.");
			return false;
		}
		input = args[0];
		output = args[1];
		inputFile = new File(args[0]);
		outputFile = new File(args[1]);
		if (!inputFile.exists() || !inputFile.isDirectory()) {
			if (!inputFile.exists())
				System.err.println("Input path does not exist.");
			if (!inputFile.isDirectory())
				System.err.println("Input path is not actually a path but a directory.");
			return false;
		}
//		if (outputFile.exists()) {
//			System.err.println("Output already exists, please choose something else.\nNow exiting.");
//			return false;
//		}

			if (!outputFile.exists())
				outputFile.mkdir();
			else {
				if (outputFile.exists() && outputFile.isFile()) {
					System.err.println("Output already exists as a file!");
					return false;
				}
				
			}

		return true;
	}
}
