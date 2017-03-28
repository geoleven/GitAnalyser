package base;

import java.io.File;
import java.io.IOException;

public class ArgumentParser {
	public File inputFile = null;
	public File outputFile = null;
	public String input = null;
	public String output = null;
	
	public ArgumentParser(){
	}
	
	boolean parseArgs(String[] args) {
		input = args[0];
		output = args[1];
		inputFile = new File(args[0]);
		outputFile = new File(args[1]);
		if (!inputFile.exists() || !inputFile.isDirectory()) {
			if (!inputFile.exists())
				System.out.println("Input path does not exist.");
			if (!inputFile.isDirectory())
				System.out.println("Input path is not actually a path but a directory.");
			return false;
		}
		if (!outputFile.exists()) {
			System.out.println("Output already exists, please choose something else.\nNow exiting.");
			return false;
		}
		try {
			outputFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output file could not be created");
			return false;
		}
		return true;
	}
}
