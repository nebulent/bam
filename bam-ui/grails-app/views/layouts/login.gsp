<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8">
	<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
	<meta name="description" content="Business Activity Monitoring by Netflexity">
	<meta name="author" content="Netflexity Inc.">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Styles -->
	<!-- Logo Font - Molle -->
	<link href="${resource(dir: 'css', file: 'molle.css')}" rel="stylesheet" type="text/css">

	<link href="${resource(dir: 'css', file: 'bootstrap.css')}" rel="stylesheet">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'icon-style.css')}" />
	<!--[if lte IE 7]>
	<script src="../scripts/lte-ie7.js"></script>
	<![endif]-->
	<link href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}" rel="stylesheet">
	<link href="${resource(dir: 'css', file: 'radmin.css')}" rel="stylesheet" id="main-stylesheet">
	<link href="${resource(dir: 'css', file: 'radmin-responsive.css')}" rel="stylesheet">

	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="${resource(dir: 'scripts', file: 'bootstrap.min.js')}"></script>
	<script type="text/javascript" src="${resource(dir: 'scripts', file: 'jquery.cloneposition.js')}"></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
	<![endif]-->
	<!--[if lte IE 9]>
	<script src="${resource(dir: 'scripts', file: 'placeholder.js')}" type="text/javascript"></script>
	<![endif]-->
	<script type="text/javascript" src="${resource(dir: 'scripts', file: 'theme.js')}"></script>
	<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'radmin-plugins.css')}" />
	<g:layoutHead/>
	<r:layoutResources/>

</head>

<body id="body-login">
<g:layoutBody/>
</body>
</html>