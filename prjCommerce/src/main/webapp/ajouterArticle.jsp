<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Ajouter Article</title>
</head>
<body>
	
	<%
	
	if(session.isNew()) {
		request.getRequestDispatcher("/connection.jsp").forward(request, response);
	}
	
	%>
	
	<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="articleAdmin.jsp">PjCommerce</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarColor01"
				aria-controls="navbarColor01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarColor01">
				<div class="collapse navbar-collapse" id="navbarColor01">

					<ul class="navbar-nav me-auto">
						<li class="nav-item">
							<form action="ServletA?flag=deconnect" method="POST">
								<button type="submit" class="navbar-nav nav-link">Se
									deconnecter</button>
							</form>
						</li>

						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-haspopup="true" aria-expanded="false">
								Compte de <c:out value="${ login.getLogin() }" default="null"></c:out>
						</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Action</a> <a
									class="dropdown-item" href="#">Another action</a> <a
									class="dropdown-item" href="#">Something else here</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Separated link</a>
							</div></li>
					</ul>
				</div>

				<form class="d-flex">
					<input class="form-control me-sm-2" type="search"
						placeholder="Search">
					<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	
	<br>
	<br>
	<br>
	
	<div class="container">
		<div class="card">

			<form action="ServletA?flag=creationArticle" method="POST">
				<fieldset style="margin: 35px;">
					<h4>Creation d'un article</h4>

					<div class="form-group">
						<label class="form-label mt-4">Nom de l'article:</label> <input
							type="text" class="form-control" autofocus="autofocus" id="nom"
							name="nom" placeholder="Enter designation">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Prix unitaire : (&lsaquo;
							40 000)</label> <input type="number" class="form-control" min=0 value=0
							required="required" id="pU" name="pU" placeholder="Enter prix">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Quantité : (&lsaquo; 40
							000)</label> <input type="number" class="form-control" min=0 value=0
							required="required" id="qte" name="qte"
							placeholder="Enter quantité">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Catégories</label> <select
							class="form-select" id="cat" name="cat">
							
							<c:forEach var="row" items="${BaseDonnée.afficherCategorie()}">
								<option value="${ row.getIdCategorie() }">
								<c:out value="${ row.getDesignationCategorie() }"/>
								</option>
							</c:forEach>
							
							
						</select>
					</div>

					<br>

					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value="Accepter">
						<input type="reset" id="btn" class="btn btn-info" value="Retour">
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>