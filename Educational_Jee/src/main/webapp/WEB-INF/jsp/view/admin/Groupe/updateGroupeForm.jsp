<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier un groupe</title>
</head>
<body>
    <h1>Modifier un groupe</h1>
    <form action="${pageContext.request.contextPath}/admin/groupe/updateGroupe/${groupe.id}" method="post">
        Nom du groupe : <input type="text" name="nomGroupe" value="${groupe.nomGroupe}"/><br/><br/>
        <input type="submit" value="Modifier"/>
    </form>
</body>
</html>
