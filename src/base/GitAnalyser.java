package base;

public class GitAnalyser {
	static ArgumentParser arguments = null;
	public static void main(String[] args) {
		arguments = new ArgumentParser();
		arguments.parseArgs(args);
	}

}
