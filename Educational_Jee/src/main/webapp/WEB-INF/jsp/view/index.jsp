<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <title>Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">

    <style>
        body {
        
            background-image: url('/resources/img/wel.jpg'); /* Chemin vers l'image */
            background-size: contain; 
            background-repeat: no-repeat;/* Ajuster la taille de l'image pour couvrir l'ensemble de l'écran */
            background-position: center  ; /* Centrer l'image */
        }

        .container {
            font-family: 'Ubuntu', sans-serif;
            font-weight: bold;
            text-align: center;
            padding-top: 320px; /* Espacement du haut de la page */
        }

        .btn-primary {
            margin-top: 60px; 
            font-size: 23px;
			padding: 10px 30px;
			line-height: 24px;
			width: 200px;
			height: 50px;
			background-color: #550a5e;
			color: #fff;
            /* Espacement entre le paragraphe et le bouton */
        }
    </style>
</head>
<body>
<div class="container">
    <div>
        <h3>Spring Web Java Application</h3>
        <p>This application was developed in an educational context at ENSAH Al Hoceima</p>
        
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/showMyLoginPage" class="btn btn-primary">Access Here!</a>
    </div>
</div>
</body>
</html>
