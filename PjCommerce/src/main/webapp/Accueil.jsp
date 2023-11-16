<%@page import="fp.*" import="java.util.*"%>
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
			<a class="navbar-brand" href="Accueil.jsp">PjCommerce</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarColor01"
				aria-controls="navbarColor01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link"
						href="inscription.jsp">S'inscrire <span
							class="visually-hidden">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="connection.jsp">Se
							connecter </a></li>

					<!--<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">Action</a> <a
								class="dropdown-item" href="#">Another action</a> <a
								class="dropdown-item" href="#">Something else here</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Separated link</a>
						</div></li>-->
				</ul>
				<form class="d-flex">
					<input class="form-control me-sm-2" type="search"
						placeholder="Search">
					<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	
	<c:choose>
		<c:when test="${empty sessionScope.login}">
			<div style="width: 100%; height: 100%; overflow-y: scroll;">

				<!-- A REVOIR POUR SECURITE -->
				<%
				ConnexionBDArticle bdi = new ConnexionBDArticle();
				%>

				<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://localhost:3306/prjCommerce" user="root"
					password="" />

				<sql:query var="Articles" dataSource="${db}">
  		  	SELECT a.*, c.designationCategorie
			FROM article AS a JOIN categorie AS c 
			ON a.idCategorie = c.idCategorie 
			ORDER BY a.idArticle ASC
		</sql:query>

				<table class="table table-striped table-hover">
					<tr>
						<th>#</th>
						<th>Designation</th>
						<th>Prix unitaire</th>
						<th>Quantité</th>
						<th>Categorie</th>
					</tr>
					<c:forEach var="row" items="${Articles.rows}">
						<tr>
							<td><c:out value="${row.idArticle}" /></td>
							<td><c:out value="${row.designation}" /></td>
							<td><c:out value="${row.pu}" /></td>
							<td><c:out value="${row.qty}" /></td>
							<td><c:out value="${row.designationCategorie}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:when>
		<c:when test="${!empty login}">
			<c:if test="${ login.getAction() == 's' }">
				<%
					request.getRequestDispatcher("/articleSimple.jsp").forward(request, response);
				%>
			</c:if>
			<c:if test="${ login.getAction() == 'a' }">
				<%
					request.getRequestDispatcher("/articleAdmin.jsp").forward(request, response);
				%>
			</c:if>			
		</c:when>
	</c:choose>

</body>
</html>