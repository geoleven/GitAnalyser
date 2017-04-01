package output;

import java.util.HashMap;

import parser.BranchCommits;
import parser.BranchInfo;

public class BranchesBody {
	public static String getBody(HashMap <String, BranchInfo> branchInfo) {			
		
		String up = "		\n" +  
				"		" + 
				"\n<!-- Page wrap -->\n" + 
				"    <div id=\"page-wrap\">\n" + 
				"        <!-- content -->\n" + 
				"        <div id=\"content\">\n" + 
				"            <h2 class=\"title-divider\">Branches' statistics</h2>\n" + 
				"            <div class=\"side-title\">\n" + 
				"            <div class=\"datagrid\">\n" + 
				"\n" + 
				"                <table>\n" + 
				"                    <thead>\n" + 
				"                        <tr>\n" + 
				"                            <th>Branch Name</th>\n" + 
				"                            <th>Branch Creation Date</th>\n" +
				"                            <th>Branch Last Comit</th>\n" + 
				"                        </tr>\n" + 
				"                    </thead>\n" +
				"                    <tbody>\n";
		String mid = "";
		int counter = 0;
		for (String branch : branchInfo.keySet()) {
			if (counter%2 == 0)
				mid += "                        <tr onclick=\"openNav('branch" + String.valueOf(counter) + "')\">\n";
			else
				mid += "                        <tr class=\"alt\" onclick=\"openNav('branch" + String.valueOf(counter) + "')\">\n";
				mid += "                            <td>" + branch + "</td>\n" + 
				"                            <td>" + branchInfo.get(branch).bDate + "</td>\n" + 
				"                            <td>" + branchInfo.get(branch).bLastEdit + "</td>\n" + 
//				"                            <td> <button onclick=\"openNav('" + String.valueOf(counter) + "')\">More info...</button></td>\n" + 
				"                        </tr>\n";
		++counter;
		}
				
		String down = "                    </tbody>\n" + 
				"                </table>\n" + 
				"            </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <!-- ENDS content -->\n" + 
				"        ";
		
		counter = 0;
		for (String branch : branchInfo.keySet()) {
			down += "        <div id=\"branch" + String.valueOf(counter) + "\" class=\"overlay\" onclick=\"closeNav('branch" + String.valueOf(counter) + "')\">\n" + 
					"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('branch" + String.valueOf(counter) + "')\">&times;</a>\n" + 
					"            <div class=\"overlay-content\">\n" + 
					"        		 <h2 class=\"title-divider\">" + branch + "</h2>\n" +
					"                <div class=\"datagrid\">\n" + 
					"                    <table>\n" + 
					"                        <thead>\n" +
					"                            " + 
					"\n" + 
					"                            <tr>\n" + 
					"                                <td>Commit's SHA-1</td>\n" + 
					"                                <td>Commit's Message</td>\n" + 
					"                                <td>Commit's Date</td>\n" + 
					"                                <td>Commit's Author</td>\n" + 
					"                                <td>Commit's Tag</td>\n" + 
					"                            </tr>\n" +  
					"                        </thead>\n" +
					"                        " + 
					"                        <tbody>\n";
	
			int counter2 = 0;
			for (BranchCommits brcm : branchInfo.get(branch).bCommits) {
				down += "\n";
				if (counter2 % 2 == 0)	
					down += "                            <tr>\n";
				else
					down += "                            <tr class=\"alt\">\n";
				down +=	"                                <td>" +  brcm.id + "</td>\n" + 
						"                                <td>\n" + 
						"                                    <div style=\" max-height: 60px;\">\n" + 
						"                                        " + brcm.message + "\n" + 
						"                                    </div>\n" + 
						"                                </td>\n" + 
						"                                <td>" + brcm.date.substring(0, brcm.date.length()-6) + "</td>\n" + 
						"                                <td>" + brcm.author + "</td>\n" + 
						"                                <td>" + ((brcm.tag == null) ? "" : brcm.tag) + "</td>\n" + 
						"                            </tr>\n" + 
						"                        ";
				++counter2;
			}
			down += "\n" + 
					"                        </tbody>\n" + 
					"                    </table>\n" + 
					"                </div>\n" + 
					"            </div>\n" + 
					"        </div>\n" + 
					"        ";
			++counter;
		}
		
		down += "\n" + 
				"        <script>\n" + 
				"        function openNav(curID) {\n" + 
				"            document.getElementById(curID).style.height = \"100%\";\n" + 
				"        }\n" + 
				"\n" + 
				"\n" + 
				"        function closeNav(curID) {\n" + 
				"            document.getElementById(curID).style.height = \"0%\";\n" + 
				"        }\n" + 
				"        </script>\n" + 
				"        ";
				
		return up + mid + down;
	}
}
