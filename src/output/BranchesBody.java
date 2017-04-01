package output;

import java.util.HashMap;

import parser.BranchCommits;
import parser.BranchInfo;

public class BranchesBody {
	public static String getBody(HashMap <String, BranchInfo> branchInfo) {			
		
		String up = "		\r\n" + 
//				"		<!-- Branch buttons -->\r\n" + 
//				"		<ul id=\"image-buttons\">\r\n" + 
//				"			<li id=\"close-image\" class=\"poshytip\" title=\"Close Branch\" ></li>\r\n" + 
//				"			<li id=\"info-button\" class=\"poshytip\" title=\"Branch info\"></li>\r\n" + 
//				"		</ul>\r\n" + 
//				"		<!-- ENDS Branch buttons -->\r\n" + 
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
//				"                            <td> <button class=\"open-popup\" data-popup=\"#branch" + String.valueOf(counter) + "\">More info...</button></td>\n" + 
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
			down += "        <div id=\"branch" + String.valueOf(counter) + "\" class=\"overlay\">\r\n" + 
					"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('branch" + String.valueOf(counter) + "')\">&times;</a>\n" + 
					"            <div class=\"overlay-content\">\r\n" + 
					"                <div class=\"datagrid\">\r\n" + 
					"                    <table>\r\n" + 
					"                        <tbody>\r\n" + 
					"                            ";
	
			for (BranchCommits brcm : branchInfo.get(branch).bCommits) {
				down += "\r\n" + 
						"                            <tr>\r\n" + 
						"                                <td>" +  brcm.id + "</td>\r\n" + 
						"                                <td>\r\n" + 
						"                                    <div style=\" max-height: 60px;\">\r\n" + 
						"                                        " + brcm.message + "\r\n" + 
						"                                    </div>\r\n" + 
						"                                </td>\r\n" + 
						"                                <td>" + brcm.date + "</td>\r\n" + 
						"                                <td>" + brcm.author + "</td>\r\n" + 
						"                                <td>" + ((brcm.tag == null) ? "" : brcm.tag) + "</td>\r\n" + 
						"                            </tr>\r\n" + 
						"                        ";
			}
			down += "\r\n" + 
					"                        </tbody>\r\n" + 
					"                    </table>\r\n" + 
					"                </div>\r\n" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"        ";
			++counter;
		}
		
		down += "\r\n" + 
				"        <script>\r\n" + 
				"        function openNav(curID) {\r\n" + 
				"            document.getElementById(curID).style.width = \"100%\";\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"        function closeNav(curID) {\r\n" + 
				"            document.getElementById(curID).style.width = \"0%\";\r\n" + 
				"        }\r\n" + 
				"        </script>\r\n" + 
				"        ";
				
		return up + mid + down;
	}
}
