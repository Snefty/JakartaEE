<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">

<title>PjCommerce</title>
</head>
<body>

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
								<button type="submit" class="navbar-nav nav-link">
									Se deconnecter</button>
							</form>
						</li>
						<li class="nav-item">
							<form action="ServletA?flag=deconnect" method="POST">
								<button type="submit" class="navbar-nav nav-link">
									Ajouter Admin</button>
							</form>
						</li>
						<li class="nav-item">
							<form action="ServletA?flag=deconnect" method="POST">
								<button type="submit" class="navbar-nav nav-link">
									Supprimer Admin</button>
							</form>
						</li>
						<li class="nav-item dropdown">
						<a
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
							</div>
						</li>
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

	<div style="position: static; margin: 5px;">
		<button type="button" class="btn btn-warning">Modifier</button>
		<button type="button" class="btn btn-danger">Supprimer</button>
	</div>

	<div style="width: 100%; height: 100%; overflow-y: scroll;">

		<table class="table table-striped table-hover">
			<tr>
				<th>#</th>
				<th>Designation</th>
				<th>Prix unitaire</th>
				<th>Quantité</th>
				<th>Categorie</th>
				<th>Action</th>
			</tr>
			<c:forEach var="row" items="${BaseDonnéeArticle}">
				<tr>
					<td><c:out value="${row.getIdArticle()}" /></td>
					<td><c:out value="${row.getDesignation()}" /></td>
					<td><c:out value="${row.getpU()}" /></td>
					<td><c:out value="${row.getQte()}" /></td>
					<td><c:out value="${row.getNameCategorie()}" /></td>
					<td>
					<button type="button" class="btn btn-warning">Modifier</button>
					<button type="button" class="btn btn-danger">Supprimer</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>