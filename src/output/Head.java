package output;

public final class Head {
	private static final String container = "\n<!DOCTYPE  html>\n" + 
			"<html>\n" + 
			"\n" + 
			"<head>\n" + 
			"    <meta charset=\"utf-8\">\n" + 
			"    <title>Git Analyser</title>\n" + 
			"    <!-- CSS -->\n" + 
			"    <link rel=\"stylesheet\" href=\"css/social-icons.css\" type=\"text/css\" media=\"screen\" />\n" + 
			"    <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\" />\n" + 
			"    <!--[if IE 7]>\n" + 
			"            <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"<?php bloginfo('template_url') ?>/css/ie7-hacks.css\" />\n" + 
			"        <![endif]-->\n" + 
			"    <!--[if IE 8]>\n" + 
			"            <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"<?php bloginfo('template_url') ?>/css/ie8-hacks.css\" />\n" + 
			"        <![endif]-->\n" + 
			"    <!-- ENDS CSS -->\n" + 
			"    <link rel=\"Stylesheet\" type=\"text/css\" href=\"js/scroller/css/smoothDivScroll.css\" />\n" + 
			"    <!--[if IE]>\n" + 
			"        <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n" + 
			"        <![endif]-->\n" + 
			"    <!-- ENDS JS -->\n" + 
			"    <!-- GOOGLE FONTS -->\n" + 
			"    <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>\n" + 
			"    <!-- Skin -->\n" + 
			"    <link rel=\"stylesheet\" href=\"skins/light.css\" type=\"text/css\" media=\"screen\" id=\"css-skins\" />\n" + 
			"</head>\n";
	
	public static final String getHead() {
		return container;
	}
}
