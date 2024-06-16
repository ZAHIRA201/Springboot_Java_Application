<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Liste des groupes</title>
</head>
<body>

<h2>Liste des groupes</h2>
<table border="1">
    <tr>
        <th>Identifiant</th>
        <th>Nom du groupe</th>
        <th>Type du groupe</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${groupes}" var="groupe">
        <tr>
            <td>${groupe.id}</td>
            <td>${groupe.nomGroupe}</td>
            <td>
                <c:choose>
                    <c:when test="${groupe.typeGroupe == 1}">Filière</c:when>
                    <c:when test="${groupe.typeGroupe == 2}">Département</c:when>
                    <c:when test="${groupe.typeGroupe == 3}">Aléatoire</c:when>
                </c:choose>
            </td>
            <td>${groupe.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/Groupe/updateGroupeForm/${groupe.id}">Modifier</a>
                <a href="${pageContext.request.contextPath}/admin/Groupe/deleteGroupe/${groupe.id}">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
