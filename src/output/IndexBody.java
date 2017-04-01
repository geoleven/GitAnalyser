package output;

public class IndexBody {
	public static String getBody(int totalFiles, long totalLines, int totalBranches, int totalTags, int totalAuthors) {			
		
		return  "\n<!-- Page wrap -->\n" + 
				"    <div id=\"page-wrap\">\n" + 
				"        <!-- content -->\n" + 
				"        <div id=\"content\">\n" + 
				"            <h2 class=\"title-divider\">General git statistics</h2>\n" + 
				"            <div class=\"side-title\">\n" + 
				"            <div class=\"datagrid\">\n" + 
				"\n" + 
				"                <table>\n" + 
				"                    <tbody>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Files: </td>\n" + 
				"                            <td>" + totalFiles + "</td>\n" + 
				"                        </tr>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Lines: </td>\n" + 
				"                            <td>" + totalLines + "</td>\n" + 
				"                        </tr>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Branches: </td>\n" + 
				"                            <td>" + totalBranches + "</td>\n" + 
				"                        </tr>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Tags: </td>\n" + 
				"                            <td>" + totalTags + "</td>\n" + 
				"                        </tr>\n" + 
				"                        <tr>\n" + 
				"                            <td>Total Authors: </td>\n" + 
				"                            <td>" + totalAuthors + "</td>\n" + 
				"                        </tr>\n" + 
				"                    </tbody>\n" + 
				"                </table>\n" + 
				"            </div>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"        <!-- ENDS content -->\n" + 
				"        ";
	}
}
