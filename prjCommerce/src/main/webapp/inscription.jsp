<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">

<title>Inscription</title>
</head>
<body>

	<header class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="accueil.jsp">PjCommerce</a>
			<div class="collapse navbar-collapse" id="navbarColor01">
				<a class="navbar-nav nav-link active" href="connection.jsp">Se connecter</a>
			</div>
		</div>
	</header>

	<br>
	<br>

	<div class="container">
		<div class="card">

			<form action="ServletA?flag=inscri" method="POST">
				<fieldset style="margin: 35px;">
					<div style="text-align: center;">
						<h4>Inscription</h4>
					</div>
					<div class="form-group">
						<label class="form-label mt-4">Nom :</label> <input type="text"
							class="form-control" autofocus="autofocus" id="nom" name="nom"
							placeholder="Enter nom">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Prenom :</label> <input type="text"
							class="form-control" id="prenom" name="prenom"
							placeholder="Enter prenom">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Adresse :</label> <input
							type="text" class="form-control" id="adr" name="adr"
							placeholder="Enter adresse">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Telephone :</label> <input
							type="text" class="form-control" id="tel" name="tel"
							placeholder="Enter telephone">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Age :</label> <input type="number"
							class="form-control" id="age" name="age" placeholder="Enter age">
					</div>

					<div class="form-group">
						<label for="sexe" class="form-label mt-4">Sexe select</label> <select
							class="form-select" id="sexe" name="sexe">
							<option value="h">h</option>
							<option value="f">f</option>
						</select>
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Pseudo :</label> <input
							type="text" class="form-control" id="login" name="login"
							placeholder="Enter password">
					</div>
					
					<div class="form-group">
						<label class="form-label mt-4">Mot de passe :</label> <input
							type="text" class="form-control" id="pwd" name="pwd"
							placeholder="Enter password">
					</div>
					
					<div class="form-group">
						<label class="form-label mt-4">Mot de passe (confirmation) :</label> <input
							type="text" class="form-control" id="pwd2" name="pwd"
							placeholder="Enter password">
					</div>

					<br> <br> <input type="submit" class="btn btn-primary"
						value="submit"> <input type="reset"
						class="btn btn-primary" value="reset">

					<div class="container aqua">
						<!-- Structure if tertiaire -->
						<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
					</div>
				</fieldset>
			</form>

		</div>
	</div>

</body>
</html>