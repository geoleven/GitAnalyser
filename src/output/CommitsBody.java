package output;

import java.util.Map;

import com.google.common.collect.Table;

import parser.PackageReturn;

public class CommitsBody {
	public static String getBody(PackageReturn commitsPercent, Table<String, String, Double> commitsPerBranchPerAuthorPercent) {
		String up = "\n<!-- Page wrap -->\n" + 
				"    <div id=\"page-wrap\">\n" + 
				"        <!-- content -->\n" + 
				"        <div id=\"content\">\n" + 
				"            <h2 class=\"title-divider\">Commits' statistics</h2>\n" + 
				"            <div class=\"side-title\">\n" + 
				"            <div class=\"datagrid\">\n" + 
				"\n" + 
				"                <table>\n" + 
				"                    <tbody>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Commits:<bold> " + String.valueOf(commitsPercent.commits) + "</bold></td>\n" + 
				"                        </tr>\n" + 
				"                        <tr onclick=\"openNav('cpa')\">\n" + 
				"                            <td>Percent of commits per author... </td>\n" + 
				"                        </tr>\n" + 
				"                        <tr onclick=\"openNav('cpb')\">\n" + 
				"                            <td>Percent of commits per branch... </td>\n" + 
				"                        </tr>\n" +  
				"                        <tr onclick=\"openNav('cpbpa')\">\n" + 
				"                            <td>Percent of commits per branch per author... </td>\n" + 
				"                        </tr>\n" + 
				"                    </tbody>\n" + 
				"                </table>\n" + 
				"            </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <!-- ENDS content -->\n" + 
				"        ";
		
		String mid = "        <div id=\"cpa\" class=\"overlay\" onclick=\"closeNav('cpa')\">\n" + 
				"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('cpa')\">&times;</a>\n" + 
				"            <div class=\"overlay-content\">\n" + 
				"                <div class=\"datagrid\">\n" + 
				"                    <table>\n" + 
				"                        <tbody>\n" + 
				"                            " +
				"                            <tr>\n" + 
				"                                <td><bold>AUTHOR</bold></td>\n" + 
				"                                <td><bold>PERCENTAGE %</bold></td>\n" + 
				"                            </tr>\n";
		
		for (String author : commitsPercent.commitsPerAuthor.keySet()){
			mid += "\n" + 
					"                            <tr>\n" + 
					"                                <td>" +  author + "</td>\n" + 
					"                                <td>" + String.format("%f", commitsPercent.commitsPerAuthor.get(author)*100) + "%</td>\n" + 
					"                            </tr>\n" + 
					"                        ";
		}
		
		mid += "\n" + 
				"                        </tbody>\n" + 
				"                    </table>\n" + 
				"                </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        " +
				"        <div id=\"cpb\" class=\"overlay\" onclick=\"closeNav('cpb')\">\n" + 
				"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('cpb')\">&times;</a>\n" + 
				"            <div class=\"overlay-content\">\n" + 
				"                <div class=\"datagrid\">\n" + 
				"                    <table>\n" + 
				"                        <tbody>\n" + 
				"                            " +
				"                            <tr>\n" + 
				"                                <td><bold>BRANCH</bold></td>\n" + 
				"                                <td><bold>PERCENTAGE %</bold></td>\n" + 
				"                            </tr>\n";
		
		for (String branch : commitsPercent.commitsPerBranch.keySet()){
			mid += "\n" + 
					"                            <tr>\n" + 
					"                                <td>" +  branch + "</td>\n" + 
					"                                <td>" + String.format("%f", commitsPercent.commitsPerBranch.get(branch)*100) + "%</td>\n" + 
					"                            </tr>\n" + 
					"                        ";
		}
		
		mid += "\n" + 
				"                        </tbody>\n" + 
				"                    </table>\n" + 
				"                </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        " +
				"        <div id=\"cpbpa\" class=\"overlay\" onclick=\"closeNav('cpbpa')\">\n" + 
				"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('cpbpa')\">&times;</a>\n" + 
				"            <div class=\"overlay-content\">\n";
		
		for (Map.Entry<String, Map<String, Double>> entry : commitsPerBranchPerAuthorPercent.rowMap().entrySet()){
			mid += "\n" + 
					"                            <div id=\"content\">\n" + 
					"                                <h2 class=\"title-divider\">" + entry.getKey() + "</h2>\n" +
					"                                <div class=\"datagrid\">\n" + 
					"                                    <table>\n" + 
					"                                        <tbody>\n" + 
					"                                            ";
			for (String author : entry.getValue().keySet()) {
				mid += "\n" + 
						"                                    <tr>\n" + 
						"                                        <td>" +  author + "</td>\n" + 
						"                                        <td>" + String.format("%f", entry.getValue().get(author)*100) + "%</td>\n" + 
						"                                     </tr>\n" + 
						"                                     ";				
			}
			mid +=	"                                        </tbody>\n" + 
					"                                    </table>\n" + 
					"                                </div>" +
					"                            </div>" +
					"                        ";
		}
		
		mid += "            </div>\n" + 
				"        </div>\n" + 
				"        ";
		
		String down = "\n" + 
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
