package output;

import java.util.HashMap;

public class AuthorsBody {
	public static String getBody(HashMap <String, Double> comPerDayPerAuth, HashMap <String, Double> comPerWeekPerAuth, 
			HashMap <String, Double> comPerMonthPerAuth, HashMap <String, Double> linesAddPerAuthPercent, 
			HashMap <String, Double> linesRemPerAuthPercent, HashMap <String, Double> linesEdtPerAuthPercent) {
		String up = "		\n" +  
				"		" + 
				"\n<!-- Page wrap -->\n" + 
				"    <div id=\"page-wrap\">\n" + 
				"        <!-- content -->\n" + 
				"        <div id=\"content\">\n" + 
				"            <h2 class=\"title-divider\">Authors' statistics</h2>\n" + 
				"            <div class=\"side-title\">\n" + 
				"            <div class=\"datagrid\">\n" + 
				"\n" + 
				"                <table>\n" + 
				"                    <tbody>\n" +
				"                        <tr>\n" +
				"                            <td>Press on any author to see more stats:</td>\n" +
				"                        </tr>\n";
		int counter = 0;
		for (String author : comPerDayPerAuth.keySet()) {
				up += "                        <tr onclick=\"openNav('author" + String.valueOf(counter) + "')\">\n" + 
				"                            <td>" + author + "</td>\n" + 
				"                        </tr>\n";
		++counter;
		}
		
		String mid = "                    </tbody>\n" + 
				"                </table>\n" + 
				"            </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <!-- ENDS content -->\n" + 
				"        ";
		
		counter = 0;
		for (String author : comPerDayPerAuth.keySet()) {
			mid += "        <div id=\"author" + String.valueOf(counter) + "\" class=\"overlay\" onclick=\"closeNav('author" + String.valueOf(counter) + "')\">\n" + 
					"        <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav('author" + String.valueOf(counter) + "')\">&times;</a>\n" + 
					"            <div class=\"overlay-content\">\n" + 
					"                <div class=\"datagrid\">\n" + 
					"                    <table>\n" + 
					"                        <thead>\n" + 
					"                            " +
					"\n" + 
					"                            <tr>\n" + 
					"                                <td>Commits/Day</td>\n" + 
					"                                <td>Commits/Week</td>\n" + 
					"                                <td>Commits/Month</td>\n" + 
					"                                <td>Lines added %</td>\n" + 
					"                                <td>Lines removed %</td>\n" + 
					"                                <td>Lines edited %</td>\n" + 
					"                            </tr>\n" + 
					"                        </thead>\n" + 
					"                        <tbody>\n" + 
					"                        " +
					"\n" + 
					"                            <tr>\n" + 
					"                                <td>" + comPerDayPerAuth.get(author) + "</td>\n" + 
					"                                <td>" + comPerWeekPerAuth.get(author) + "</td>\n" + 
					"                                <td>" + comPerMonthPerAuth.get(author) + "</td>\n" + 
					"                                <td>" + String.format("%f", linesAddPerAuthPercent.get(author)*100) + "%</td>\n" + 
					"                                <td>" + String.format("%f", linesRemPerAuthPercent.get(author)*100) + "%</td>\n" + 
					"                                <td>" + String.format("%f", linesEdtPerAuthPercent.get(author)*100) + "%</td>\n" +
					"                            </tr>\n" + 
					"                        " +
					"\n" + 
					"                        </tbody>\n" + 
					"                    </table>\n" + 
					"                </div>\n" + 
					"            </div>\n" + 
					"        </div>\n" + 
					"        ";
			++counter;
		}
		
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
