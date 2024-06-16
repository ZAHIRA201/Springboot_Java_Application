<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Création de Groupe</title>
</head>
<body>
    <h2>Formulaire de Création de Groupe</h2>
    <f:form action="addGroupe" method="POST" modelAttribute="groupeModel">
        <div>
            <label for="nom">Nom du Groupe:</label>
            <f:input path="nom" id="nom" />
        </div>
        <!-- Autres champs du formulaire selon vos besoins -->

        <div>
            <button type="submit">Créer Groupe</button>
        </div>
    </f:form>
</body>
</html>
