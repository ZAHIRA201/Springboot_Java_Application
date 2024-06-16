<%@page import="com.ensah.core.web.models.PersonModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />


<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<jsp:include page="../fragments/menu.jsp" />

		</div>
	</nav>



	<div>
		<h3>
			Ajout d'un
			<c:if test="${personModel.typePerson== PersonModel.TYPE_PROF}"
				var="variable">
					Professeur
				</c:if>
			<c:if test="${personModel.typePerson== PersonModel.TYPE_STUDENT}"
				var="variable">
					Etudiant
				</c:if>
			<c:if test="${personModel.typePerson== PersonModel.TYPE_CADRE_ADMIN}"
				var="variable">
					Cadre Administratif
				</c:if>

		</h3>
	</div>
	<div>


		<f:form action="addPerson" method="POST" modelAttribute="personModel">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">

			<div class="row">
				<div class="col">
					<label>CIN</label>
					<f:input path="cin" type="text" class="form-control"
						placeholder="cin" />
					<f:errors path="cin" class="text-danger" />
				</div>

				<div class="col">
					<label>Nom</label>
					<f:input path="nom" type="text" class="form-control"
						placeholder="nom" />
					<f:errors path="nom" class="text-danger" />
				</div>
				<f:hidden path="typePerson" />
				<c:if test="${personModel.typePerson== PersonModel.TYPE_PROF}"
					var="variable">
					<div class="col">
						<label>Spécialité</label>
						<f:input path="specialite" type="text" class="form-control"
							placeholder="Spécialité" />
						<f:errors path="specialite" class="text-danger" />
					</div>
				</c:if>
				<c:if test="${personModel.typePerson== PersonModel.TYPE_STUDENT}"
					var="variable">
					<div class="col">
						<label>CNE</label>
						<f:input path="cne" type="text" class="form-control"
							placeholder="cne" />
						<f:errors path="cne" class="text-danger" />
					</div>
				</c:if>
				<c:if
					test="${personModel.typePerson== PersonModel.TYPE_CADRE_ADMIN}"
					var="variable">
					<div class="col">
						<label>Grade</label>
						<f:input path="grade" type="text" class="form-control"
							placeholder="grade" />
						<f:errors path="grade" class="text-danger" />
					</div>
				</c:if>
			</div>


			<div class="row">
				<div class="col">
					<label>Prénom</label>
					<f:input path="prenom" type="text" class="form-control"
						placeholder="prenom" />
					<f:errors path="prenom" class="text-danger" />
				</div>
			</div>
			

				<div class="row">
					<div class="col">
						<label>Email</label>
						<f:input path="email" class="form-control" placeholder="Email" />
						<f:errors path="email" class="text-danger" />
					</div>

				</div>




				<div style="text-align: right">
					<button type="submit" class="btn btn-primary">Enregistrer</button>
					<button type="reset" class="btn btn-secondary">Annuler</button>
				</div>
		</f:form>
	</div>

	<div>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Type</th>
					<th scope="col">CIN</th>
					<th scope="col">Nom</th>
					<th scope="col">Prénom</th>
					<th scope="col">Email</th>
					<th scope="col">Télé</th>
					<th>Actions</th>
				</tr>
			</thead>

			<c:forEach items="${personList}" var="p">
				<tr>

					<td><c:if test="${p.typePerson== 1}" var="variable">
							<span class="badge bg-primary">Professeur</span>
						</c:if> <c:if test="${p.typePerson==2}" var="variable">
							<span class="badge bg-success">Etudiant</span>
						</c:if> <c:if test="${p.typePerson==3}" var="variable">
							<span class="badge bg-secondary">Cadre Administratif</span>
						</c:if></td>
					<td><c:out value="${p.cin}" /></td>
					<td><c:out value="${p.nom}" /></td>
					<td><c:out value="${p.prenom}" /></td>
					<td><c:out value="${p.email}" /></td>
					<td><c:out value="${p.telephone}" /></td>

					<td>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/admin/deletePerson/${p.idPersonne}" class="btn btn-danger">Delete</a></li>

							<li><a
								href="${pageContext.request.contextPath}/admin/updatePersonForm/${p.idPersonne}" class="btn btn-warning">Update</a></li>


						</ul>
					</td>

				</tr>

			</c:forEach>

		</table>
	</div>

	<jsp:include page="../fragments/adminfooter.jsp" />