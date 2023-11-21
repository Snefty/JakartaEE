<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">

<title>Connection</title>
</head>
<body>

	<header class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="accueil.jsp">PjCommerce</a>
			<div class="collapse navbar-collapse" id="navbarColor01">
					<a class="navbar-nav nav-link active" href="inscription.jsp">S'inscrire
							<span class="visually-hidden">(current)</span>
					</a>
			</div>
		</div>
		
	</header>
	
	<br><br><br><br><br>
	
	<div class="container"style="width: 600px;">
		<div class="card">
	
			<form action="ServletA?flag=connect" method="POST" >
				<fieldset style="margin: 35px;">
					<div style="text-align: center;"><h2>Connection</h2></div>
					
					<div class="form-group">
						
						<label class="form-label mt-4">Login</label> 
						<input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
						
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Password</label> 
						<input type="password" class="form-control" id="mdp" name="mdp" placeholder="Enter password">
					</div>
					
					<br>
					<br> 
					<input type="submit" class="btn btn-primary" value="submit"> 
					<input type="reset" class="btn btn-primary" value="reset">
					
					<div class="container aqua">
					 <!-- Structure if tertiaire -->
						<p class="${!empty LogImpo ? 'succes' : 'erreur'}">${LogImpo}</p>
					</div>
				</fieldset>
			</form>
		
		</div>
	</div>
	
</body>
</html>