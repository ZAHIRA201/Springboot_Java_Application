<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="en">
<head>
    <title>Login page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu|Rubik:400,500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <style>
        * { 
            box-sizing: border-box; 
        }

        body {
            font-family: 'Rubik', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: #25075e;
        }

        .container {
            display: flex;
            height: 100vh;
        }

        .left {
            overflow: hidden;
            display: flex;
            flex-wrap: wrap;
            flex-direction: column;
            justify-content: center;
            animation-name: left;
            animation-duration: 1s;
            animation-fill-mode: both;
            animation-delay: 1s;
            padding: 20px 40px;
            width: 440px;
            background: #fff;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .right {
            flex: 1;
            background-color: black;
            transition: 1s;
            background-image: url('/resources/img/khouna.jpg'); /* Chemin vers l'image */
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }

        .header > h1 {
            margin: 0;
            color: #4f46a5;
        }

        .header > h4 {
            margin-top: 10px;
            font-weight: normal;
            font-size: 15px;
            color: rgba(0,0,0,.4);
        }

        .form {
            max-width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .form > p {
            text-align: right;
            width: 100%; /* Ensuring it aligns correctly */
        }

        .form > p > a {
            color: #000;
            font-size: 14px;
        }

        .form-field {
            height: 46px;
            padding: 0 16px;
            border: 2px solid #ddd;
            border-radius: 4px;
            font-family: 'Rubik', sans-serif;
            outline: 0;
            transition: .2s;
            margin-top: 20px;
            width: 100%; /* Make the input fields take up 100% of the container's width */
            max-width: 400px; /* Set a max-width to control the width */
        }

        .form-field:focus {
            border-color: #0f7ef1;
        }

        form > button {
            padding: 15px 30px; /* Increase padding for a larger button */
            border: 0;
            background: linear-gradient(to right, #800080, #b200b2); /* Gradient color for the button */
            border-radius: 5px;
            margin-top: 20px;
            color: #fff;
            letter-spacing: 1px;
            font-family: 'Rubik', sans-serif;
            width: 100%; /* Make button take up full width of the form */
            max-width: 500px; /* Set max width to control button size */
            font-size: 18px; /* Increase font size */
            transition: background 0.3s ease, transform 0.3s ease; /* Smooth transition for the hover effect and size */
            cursor: pointer;
        }

        .form > button:hover {
            background: linear-gradient(to right, #b200b2, #800080); /* Reverse gradient on hover */
            transform: scale(1.05); /* Slightly enlarge the button on hover */
        }

        .animation {
            animation-name: move;
            animation-duration: .4s;
            animation-fill-mode: both;
            animation-delay: 2s;
        }

        .a1 {
            animation-delay: 2s;
        }

        .a2 {
            animation-delay: 2.1s;
        }

        .a3 {
            animation-delay: 2.2s;
        }

        .a4 {
            animation-delay: 2.3s;
        }

        .a5 {
            animation-delay: 2.4s;
        }

        .a6 {
            animation-delay: 2.5s;
        }

        @keyframes move {
            0% {
                opacity: 0;
                visibility: hidden;
                transform: translateY(-40px);
            }
            100% {
                opacity: 1;
                visibility: visible;
                transform: translateY(0);
            }
        }

        @keyframes left {
            0% {
                opacity: 0;
                width: 0;
            }
            100% {
                opacity: 1;
                width: 440px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="left">
            <div class="header">
                <h1 class="animation a1">Welcome Back !</h1>
                <h4 class="animation a2">Log in to your account</h4>
            </div>
            <div class="form">
                <form class="form1 animation a3" action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <!-- CSRF token -->

                    <c:if test="${param.error!=null}">
                        <c:choose>
                            <c:when test="${param.error=='disabled'}">
                                <div class="alert alert-danger col-12">Account Disabled</div>
                            </c:when>
                            <c:when test="${param.error=='locked'}">
                                <div class="alert alert-danger col-12">Account Locked</div>
                            </c:when>
                            <c:when test="${param.error=='expired'}">
                                <div class="alert alert-danger col-12">Account Expired</div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger col-12">Please verify your login or password</div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success col-12">You are logged out of the system</div>
                    </c:if>

                    <input class="form-field animation a3" name="username" type="text" placeholder="Username" required>
                    <input class="form-field animation a4" name="password" type="password" placeholder="Password" required>
                    <p class="animation a5"><a href="#">Forgot Password?</a></p>
                    <button type="submit" class="animation a6">LOGIN</button>
                </form>
            </div>
        </div>
        <div class="right"></div>
    </div>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
