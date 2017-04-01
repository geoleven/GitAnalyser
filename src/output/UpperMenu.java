package output;

public class UpperMenu {
	private static String container = "<body>\n" + 
			"    <!-- Navigation -->\n" + 
			"    <div id=\"nav-wrapper\">\n" + 
			"        <ul id=\"nav\" class=\"sf-menu\">\n" + 
			"            <li class=\"logo\">\n" + 
			"                <a href=\"index.html\"><img src=\"img/logo.png\" alt=\"Git Analyser\" id=\"logo\"></a>\n" + 
			"            </li>\n" + 
			"            <li class=\"current-menu-item\"><a>Options</a>\n" + 
			"                <ul>\n" + 
			"                    <li><a href=\"index.html\">General</a></li>\n" + 
			"                    <li><a href=\"branches.html\">Branches</a></li>\n" + 
			"                    <li><a href=\"page-wide.html\">Commits' Stats</a></li>\n" + 
			"                    <li><a href=\"page-fixed-footer.html\">Authors' Stats</a></li>\n" + 
			"                </ul>\n" + 
			"            </li>\n" + 
			"            <li><a href=\"javascript:changeSkin('dark')\">Dark skin</a></li>\n" + 
			"            <li><a href=\"javascript:changeSkin('light')\">Light skin</a></li>\n" + 
			"            <li class=\"social\">\n" + 
			"                <!-- Social -->\n" + 
			"                <a href=\"https://www.facebook.com/Git-46435632617/\" class=\"poshytip  facebook\" title=\"Become a fan\"></a>\n" + 
			"                <!--a href=\"https://twitter.com/ansimuz\" class=\"poshytip  twitter\" title=\"Follow my tweets\"></a-->\n" + 
			"                <!-- ENDS Social -->\n" + 
			"            </li>\n" + 
			"            <li class=\"search-box\">\n" + 
			"                <form method=\"get\" id=\"searchform\" action=\"#\">\n" + 
			"                    <input type=\"text\" value=\"Search...\" name=\"s\" id=\"s\" onfocus=\"defaultInput(this,'Search...')\" onblur=\"clearInput(this,'Search...')\" />\n" + 
			"                </form>\n" + 
			"            </li>\n" + 
			"        </ul>\n" + 
			"    </div>\n" + 
			"    <!-- Navigation -->\n";
	
	public static final String getMenu() {
		return container;
	}
}
