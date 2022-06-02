<%-- 
    Document   : signin-singup
    Created on : Mar 16, 2022, 4:20:32 PM
    Author     : 27769
--%>
<% 
    String message = (String)request.getAttribute("message");
    if(message == null){
        message="";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=" main.css">
        <link rel="stylesheet" type="text/css" href="css/all.css" />
        <link rel="stylesheet" type="text/css" href="css/all.min.css" />
        <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css" />
        <title>Signin-Singup</title>
    </head>
    <body>
        <section class="header">
            <nav>
                <label class="logo">Issue Tracker</label>
                <div class="nav-links" id="navLinks">
                    <i class="fa fa-times" onclick="hideMenu()"></i>
                    <ul>
                        <li><a href="index.jsp">HOME</a></li>
                        <li><a href="aboutUs.html">news</a></li>
                        <li><a href="course.html">contact</a></li>
                        <li><a href="blog.html">About</a></li>
                        <li><a href="signin-singup.jsp">sign in</a></li>
                    </ul>
                </div>
                <i class="fa fa-bars" onclick="showMenu()"></i>
            </nav>
        </section>

        <section class="form-container">
           <div class="log-register-box">
            <div class="botton-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" onclick="login()">Log In</button>
                <button type="button" class="toggle-btn" onclick="Register()">Register</button>
            </div>
               <p><%= message %></p>
            <form id="loginForm" class="input-group" action="RegisterLoginSerlet" method="POST">
                <input type="text" name="email" class="inpuut-field" placeholder="Email" required>
                <input type="password" name="password" class="inpuut-field" placeholder="Password" required>
                <input type="checkbox" class="check-box"><span>Remember Password</span>
                <button type="submit" class="submit-btn">Log In</button>
                
                <input type="hidden" class="inpuut-field" name="action" value="login" required>
            </form>

            <form id="registerForm" class="input-group" method="POST" action="RegisterLoginSerlet">
                <input type="text" class="inpuut-field" name="firstname" placeholder="First Name" required>
                <input type="text" class="inpuut-field" name="lastname"placeholder="Last Name" required>
                <input type="email" class="inpuut-field" name="email"placeholder="Email" required>
                <input type="text" class="inpuut-field" name="teleponeNum"placeholder="Tellphone Numer" required>
                <input type="password" class="inpuut-field" name="password" placeholder="Password" required>
                <input type="checkbox" class="check-box"><span>I agree to the terms & condition </span>
                <button type="submit" class="submit-btn">Register</button>
                
                <input type="hidden" class="inpuut-field" name="action" value="client" required>
            </form>

        </div>
        </section>

        <script src="js.js"></script>
  
    </body>
</html>
