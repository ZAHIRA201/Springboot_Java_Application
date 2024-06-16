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

    <h3>Create/Update Group</h3>

    <f:form action="${pageContext.request.contextPath}/admin/groupes/${empty groupe.idGroupe ? 'addGroupe' : 'updateGroupe'}" method="post" modelAttribute="groupe">
        <label for="nom">Nom du groupe :</label>
        <f:input path="nom" id="nom" required="true" />
        <br><br>
        <f:input type="hidden" path="idGroupe"/>
        <input type="submit" value="${empty groupe.idGroupe ? 'Create' : 'Update'}">
    </f:form>

    <jsp:include page="../fragments/adminfooter.jsp" />
</div>
