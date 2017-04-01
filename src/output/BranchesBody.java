package output;

import java.util.HashMap;

import parser.BranchInfo;

public class BranchesBody {
	public static String getBody(HashMap <String, BranchInfo> branchInfo) {			
		
		String up =  "\n<!-- Page wrap -->\n" + 
				"    <div id=\"page-wrap\">\n" + 
				"        <!-- content -->\n" + 
				"        <div id=\"content\">\n" + 
				"            <h2 class=\"title-divider\">General git statistics</h2>\n" + 
				"            <div class=\"side-title\">\n" + 
				"            <div class=\"datagrid\">\n" + 
				"\n" + 
				"                <table>\n" + 
				"                    <tbody>\n";
		String mid = "";
//		int counter = 0;
		for (String branch : branchInfo.keySet()) {
				mid += "                        <tr>\n" + 
				"                            <td>" + branch + "</td>\n" + 
				"                            <td> <button type=\"button\">More info...</button> </td>\n" + 
				"                        </tr>\n";
		}
 
				
		String down = "                    </tbody>\n" + 
				"                </table>\n" + 
				"            </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <!-- ENDS content -->\n" + 
				"        ";
				
		return up + mid + down;
	}
}
