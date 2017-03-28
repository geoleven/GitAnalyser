package base;

import parser.Parse;

public class GitAnalyser {
	static ArgumentParser arguments = null;
	static Parse p = null;
	public static void main(String[] args) {
		arguments = new ArgumentParser();
//		for(int counter = 0; counter < args.length; ++counter) {
//			System.out.println(counter);
//			System.out.println(args[counter]);
//		}
		
		if(!arguments.parseArgs(args))
			return;
		
		p = new Parse(arguments);
		p.fileCount();
		
		System.out.println("Now exiting normally!");
		
	}

}
