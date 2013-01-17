<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="layout" content="login">
	<title>Authentication</title>
</head>
<body id="body-login">

	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span4"></div>

			<div class="span4 login-span">
				<div class="login-radmin align-center">
					<h1 class="brand">
						<span class="rad">Rad</span>min
					</h1>
				</div>
				<div class="login-wrapper">
					<div class="login-inner">
						<h2 class="sign-in">Sign in</h2>
						<small class="muted">Please sign in using your registered account details</small>
						<div class="squiggly-border"></div>

						<div class="login-inner">
							<form action="${postUrl}" method="POST" id="loginForm" class="form-horizontal" autocomplete="off">
								<div class="input-prepend">
									<span class="add-on"> <i class="radmin-icon radmin-user"></i>
									</span>
									<input class="input-large" id="input-username" size="16" type="text" placeholder="Username" name="j_username"></div>
								<br />
								<br />
								<div class="input-prepend">
									<span class="add-on"> <i class="radmin-icon radmin-locked"></i>
									</span>
									<input class="input-large" id="input-password" size="16" type="password" placeholder="Password" name="j_password"></div>

								<div class="form-actions">
									<a class="btn-link pull-left" href="#" id="lost-password">Lost your password?</a>
									<%--<a class="btn btn-large btn-inverse pull-right" href="index.html" id="login">Login</a>
								--%><input class="btn btn-large btn-inverse pull-right" href="index.html" type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>

			<div class="span4"></div>
		</div>

	</div>
	<script type='text/javascript'>
		<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
		// -->
	</script>
</body>