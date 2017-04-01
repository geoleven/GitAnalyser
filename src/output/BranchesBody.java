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
				"                    <tbody>\n";
		String mid = "";
		int counter = 0;
		for (String branch : branchInfo.keySet()) {
				mid += "                        <tr onclick=\"openNav('branch" + String.valueOf(counter) + "')\">\n" + 
				"                            <td>" + branch + "</td>\n" + 
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
					"                <div class=\"datagrid\">\n" + 
					"                    <table>\n" + 
					"                        <tbody>\n" + 
					"                            ";
	
			for (BranchCommits brcm : branchInfo.get(branch).bCommits) {
				down += "\n" + 
						"                            <tr>\n" + 
						"                                <td>" +  brcm.id + "</td>\n" + 
						"                                <td>\n" + 
						"                                    <div style=\" max-height: 60px;\">\n" + 
						"                                        " + brcm.message + "\n" + 
						"                                    </div>\n" + 
						"                                </td>\n" + 
						"                                <td>" + brcm.date + "</td>\n" + 
						"                                <td>" + brcm.author + "</td>\n" + 
						"                                <td>" + ((brcm.tag == null) ? "" : brcm.tag) + "</td>\n" + 
						"                            </tr>\n" + 
						"                        ";
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
