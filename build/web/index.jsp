<%-- 
    Document   : index
    Created on : Mar 15, 2022, 3:14:31 PM
    Author     : 27769
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" type="text/css" href="css/all.css" />
        <link rel="stylesheet" type="text/css" href="css/all.min.css" />
        <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css" />
        <title>Issue Tracker</title>
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

        <div class="text-box">
            <h1>Manqoba's Biggest University</h1>
            <p>orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been<br> the industry's standard dummy text ever since the 1500s</p>
            <a href="" class="hero-btn">Viist Us To know More</a>
        </div>
    </section>
        <!--JavaScrip -->
    <script src="js.js"></script>
    </body>
</html>
