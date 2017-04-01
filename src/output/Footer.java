package output;

public class Footer {
	private static final String container = "\n" + 
			"        <!-- Sidebar -->\n" + 
			"        <div id=\"sidebar\">\n" + 
			"            <!-- project tags -->\n" + 
			"            <h6 class=\"side-title\">Options</h6>\n" + 
			"            <ul class=\"cat-list\">\n" + 
			"                <li><a href=\"index.html\"> General </a></li>\n" + 
			"                <li><a href=\"branches.html\"> Branches </a></li>\n" + 
			"                <li><a href=\"#\"> Commits' Statistics  </a></li>\n" + 
			"                <li><a href=\"#\"> Authors' Statistics  </a></li>\n" + 
			"            </ul>\n" + 
			"            <!-- ENDS project tags -->\n" + 
			"        </div>\n" + 
			"        <!-- ENDS Sidebar -->\n" + 
			"    </div>\n" + 
			"    <!-- ENDS Page wrap -->\n" + 
			"    </div>\n" + 
			"    <!-- ENDS Page wrap -->\n" + 
			"    <!-- JS -->\n" + 
			"    <!-- jQuery library - Please load it from Google API's -->\n" + 
			"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js\"></script>\n" + 
			"    <!-- Smoothscroller -->\n" + 
			"    <!-- jQuery UI Widget and Effects Core (custom download)\n" + 
			"		     You can make your own at: http://jqueryui.com/download -->\n" + 
			"    <script src=\"js/scroller/js/jquery-ui-1.8.23.custom.min.js\"></script>\n" + 
			"    <!-- Latest version (3.0.6) of jQuery Mouse Wheel by Brandon Aaron\n" + 
			"		     You will find it here: http://brandonaaron.net/code/mousewheel/demos -->\n" + 
			"    <script src=\"js/scroller/js/jquery.mousewheel.min.js\"></script>\n" + 
			"    <!-- jQuery Kinectic (1.5) used for touch scrolling -->\n" + 
			"    <script src=\"js/scroller/js/jquery.kinetic.js\"></script>\n" + 
			"    <!-- Smooth Div Scroll 1.3 minified-->\n" + 
			"    <script src=\"js/scroller/js/jquery.smoothdivscroll-1.3-min.js\"></script>\n" + 
			"    <!-- ENDS Smoothscroller -->\n" + 
			"    <script src=\"js/quicksand.js\"></script>\n" + 
			"    <!-- prettyPhoto -->\n" + 
			"    <script src=\"js/prettyPhoto/js/jquery.prettyPhoto.js\"></script>\n" + 
			"    <link rel=\"stylesheet\" href=\"js/prettyPhoto/css/prettyPhoto.css\" type=\"text/css\" media=\"screen\" />\n" + 
			"    <!-- ENDS prettyPhoto -->\n" + 
			"    <!-- superfish -->\n" + 
			"    <link rel=\"stylesheet\" media=\"screen\" href=\"css/superfish.css\" />\n" + 
			"    <script src=\"js/superfish-1.4.8/js/hoverIntent.js\"></script>\n" + 
			"    <script src=\"js/superfish-1.4.8/js/superfish.js\"></script>\n" + 
			"    <script src=\"js/superfish-1.4.8/js/supersubs.js\"></script>\n" + 
			"    <!-- ENDS superfish -->\n" + 
			"    <!-- poshytip -->\n" + 
			"    <link rel=\"stylesheet\" href=\"js/poshytip-1.0/src/tip-twitter/tip-twitter.css\" type=\"text/css\" />\n" + 
			"    <link rel=\"stylesheet\" href=\"js/poshytip-1.0/src/tip-yellowsimple/tip-yellowsimple.css\" type=\"text/css\" />\n" + 
			"    <script src=\"js/poshytip-1.0/src/jquery.poshytip.min.js\"></script>\n" + 
			"    <!-- ENDS poshytip -->\n" + 
			"    <script src=\"js/backstretch.js\"></script>\n" + 
			"    <script src=\"js/custom.js\"></script>\n" + 
			"    <!-- ENDS JS -->\n" + 
			"</body>\n" + 
			"\n" + 
			"</html>\n\n";
	
	public static final String getFooter() {
		return container;
	}
}
