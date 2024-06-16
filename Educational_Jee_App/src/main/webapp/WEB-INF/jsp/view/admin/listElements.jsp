<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
        <h3>Liste des éléments pédagogiques</h3>
    </div>

    <div>
        <p style="text-align:right">
            <a href="${pageContext.request.contextPath}/admin/exportElements">
                <img src="${pageContext.request.contextPath}/resources/img/excel.png" width="30"> Exporter Excel
            </a>
        </p>
        
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Niveau</th>
                    <th scope="col">Type Element</th>
                    <th scope="col">Titre</th>
                     <th scope="col">Enseignant Identifiant</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <c:forEach items="${elementList}" var="p">
                <tr>
                    <td><c:out value="${p.niveau}" /></td>
                    <td><c:out value="${p.typeElement}" /></td>
                    <td><c:out value="${p.titre}" /></td>
                    <td><c:out value="${p.idPersonne}" /></td>
                    <td>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/admin/deleteElement/${p.idElement}" class="btn btn-danger">Delete</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/updateEleForm/${p.idElement}" class="btn btn-warning">Update</a></li>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="../fragments/adminfooter.jsp" />
</div>
