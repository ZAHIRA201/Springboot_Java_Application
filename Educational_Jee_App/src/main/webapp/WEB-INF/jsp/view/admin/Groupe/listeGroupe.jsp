<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des Groupes</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Liste des Groupes</h2>

        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Nom du Groupe</th>
                    <th>Type de Groupe</th>
                    <th>Description</th>
                    <th>Membres</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="groupe" items="${groupes}">
                    <tr>
                        <td>${groupe.nomGroupe}</td>
                        <td>${groupe.typeGroupe}</td>
                        <td>${groupe.description}</td>
                        <td>
                            <ul class="list-unstyled">
                                <c:forEach var="enseignant" items="${groupe.enseignant}">
                                    <li>${enseignant.nom} ${enseignant.prenom}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/Groupe/edit/${groupe.id}" class="btn btn-modifier btn-sm">Modifier</a>
                            <a href="${pageContext.request.contextPath}/admin/Groupe/delete/${groupe.id}" class="btn btn-supprimer btn-sm">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>
        <a href="<c:url value='/admin/Groupe/showForm' />" class="btn btn-success">Créer un nouveau Groupe</a>
    </div>

    <!-- Inclusion de jQuery et Bootstrap JS -->
    <script src="<c:url value='/resources/js/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>
