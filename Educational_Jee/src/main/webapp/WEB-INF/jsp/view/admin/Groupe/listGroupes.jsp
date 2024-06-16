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
        <h3>List of Groups</h3>
    </div>

    <div>
        <p style="text-align:right">
            <a href="${pageContext.request.contextPath}/admin/groupes/showForm">
                <button type="button" class="btn btn-primary">Add Group</button>
            </a>
        </p>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nom</th>
                <th>Actions</th>
            </tr>
        </thead>

        <c:forEach items="${groupes}" var="g">
            <tr>
                <td><c:out value="${g.idGroupe}" /></td>
                <td><c:out value="${g.nom}" /></td>
                <td>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/groupe/deleteGroupe/${g.idGroupe}">Delete</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/groupe/updateGroupeForm/${g.idGroupe}">Update</a>
                        </li>
                    </ul>
                </td>
            </tr>
        </c:forEach>
    </table>

    <jsp:include page="../fragments/adminfooter.jsp" />
</div>
