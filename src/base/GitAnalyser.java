package base;

import parser.BranchCommits;
import parser.BranchInfo;
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
//		p.filesCount();
		System.out.println("Main files count: " + p.filesCount());
//		p.totalLinesCount();
		System.out.println("Main total lines count: " + p.totalLinesCount());
//		p.branchCount();
		System.out.println("Main branches count: " + p.branchCount());
		System.out.println("Main tags count: " + p.tagsCount());
		System.out.println("Main commiters count: " + p.commitersCount());
		for (BranchInfo bi : p.getInfoBranches().values()) {
			System.out.println("\n\n" + bi.bName + " " + bi.bDate);
			for (BranchCommits bc : bi.bCommits){
				System.out.print(bc.id + " " + bc.message);
				System.out.println(bc.tag == null ? "" : bc.tag);
			}
		}
			
		
		System.out.println("\n\nNow exiting normally!");
		
	}

}
