<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Examen</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style1.css' />">
    
</head>
<body>
    <div class="container">
        <h2>Ajouter un Examen</h2>
        <form:form modelAttribute="examen" method="post" action="${pageContext.request.contextPath}/admin/Examen/addExamen" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="dateExam">Date:</label>
                <form:input path="dateExam" id="dateExam" class="form-control" placeholder="Date"/>
                <form:errors path="dateExam" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="heureDebut">Heure de Début:</label>
                <form:input path="heureDebut" id="heureDebut" class="form-control" placeholder="Heure de Début"/>
                <form:errors path="heureDebut" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="semestre">Semestre:</label>
                <form:input path="semestre" id="semestre" class="form-control" placeholder="Semestre"/>
                <form:errors path="semestre" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="session">Session:</label>
                <form:input path="session" id="session" class="form-control" placeholder="Session"/>
                <form:errors path="session" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="typeExamen">Type:</label>
                <form:input path="typeExamen" id="typeExamen" class="form-control" placeholder="Type"/>
                <form:errors path="typeExamen" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="epreuve">Epreuve:</label>
                <form:input path="epreuve" id="epreuve" class="form-control" placeholder="Epreuve"/>
                <form:errors path="epreuve" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="pv">PV:</label>
                <form:input path="pv" id="pv" class="form-control" placeholder="PV"/>
                <form:errors path="pv" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="rapport">Rapport Textuel:</label>
                <form:input path="rapport" id="rapport" class="form-control" placeholder="Rapport Textuel"/>
                <form:errors path="rapport" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="dureePrevue">Durée Prévue:</label>
                <form:input path="dureePrevue" id="dureePrevue" class="form-control" placeholder="Durée Prévue"/>
                <form:errors path="dureePrevue" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="enseignantIds">Enseignants:</label>
                <form:select path="enseignantIds" id="enseignantIds" class="form-control" multiple="true">
                    <c:forEach var="enseignant" items="${enseignants}">
                        <option value="${enseignant.idPersonne}">${enseignant.nom} ${enseignant.prenom}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="enseignantIds" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="salleIds">Salles:</label>
                <form:select path="salleIds" id="salleIds" class="form-control" multiple="true">
                    <c:forEach var="salle" items="${salles}">
                        <option value="${salle.id_salle}">${salle.nom}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="salleIds" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="adminId">Admin:</label>
                <form:select path="adminId" id="adminId" class="form-control">
                    <c:forEach var="admin" items="${admins}">
                        <option value="${admin.idPersonne}">${admin.nom} ${admin.prenom}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="adminId" cssClass="text-danger"/>
            </div>
            <button type="submit" class="btn btn-primary">Ajouter Examen</button>
        </form:form>
    </div>
</body>
</html>
