package base;

import parser.Parse;

public class GitAnalyser {
	static ArgumentParser arguments = null;
	static Parse p = null;
	public static void main(String[] args) {
		arguments = new ArgumentParser();
		
		if(!arguments.parseArgs(args))
			return;
		
		p = new Parse(arguments);
		p.run(true, true);
		
		p.html.render();
		
		System.out.println("\n\nNow exiting normally!");
		
	}

}
