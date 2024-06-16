<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/jsp/view/fragments/adminHeader.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Modifier le groupe</h2>

    <f:form action="${pageContext.request.contextPath}/admin/Groupe/edit/${groupe.id}" method="post" modelAttribute="groupe">
        <div class="form-group mb-3">
            <label for="nomGroupe" class="form-label">Nom du groupe :</label>
            <f:input path="nomGroupe" id="nomGroupe" class="form-control" required="true"/>
            <f:errors path="nomGroupe" class="text-danger mt-2" />
        </div>
        
        <div class="form-group mb-3">
            <label class="form-label">Type de groupe :</label>
            <div>
                <f:radiobutton path="typeGroupe" id="filiere" value="filiere" />
                <label for="filiere" class="form-check-label">Filière</label>
            </div>
            <div>
                <f:radiobutton path="typeGroupe" id="departement" value="departement" />
                <label for="departement" class="form-check-label">Département</label>
            </div>
            <div>
                <f:radiobutton path="typeGroupe" id="aleatoire" value="aleatoire" />
                <label for="aleatoire" class="form-check-label">Aléatoire</label>
            </div>
            <f:errors path="typeGroupe" class="text-danger mt-2" />
        </div>
        
        <div class="form-group mb-3">
            <label for="description" class="form-label">Description :</label>
            <f:textarea path="description" id="description" class="form-control"/>
            <f:errors path="description" class="text-danger mt-2" />
        </div>
        
        <div class="form-group mb-4">
            <label for="enseignantIds" class="form-label">Enseignants :</label>
            <f:select path="enseignantIds" multiple="true" items="${enseignants}" itemValue="idPersonne" itemLabel="nomComplet" class="form-control"/>
            <f:errors path="enseignantIds" class="text-danger mt-2" />
        </div>

        <button type="submit" class="btn btn-primary">Modifier</button>
    </f:form>
</div>

<jsp:include page="/WEB-INF/jsp/view/fragments/adminfooter.jsp" />
