<%-- 
    Document   : adminDashboard
    Created on : Mar 27, 2022, 12:54:11 PM
    Author     : 27769
--%>
<%@page import="za.co.bigone.model.User"%>
<%@page import="java.util.List"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setHeader("Expire", "0");
    if(session.getAttribute("user") == null){
        response.sendRedirect("signin-singup.jsp");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" type="text/css" href="css/all.css" />
        <link rel="stylesheet" type="text/css" href="css/all.min.css" />
        <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script src="javascript/jquery.js"></script>
        <title>Dashboard</title>
    </head>
    <body>
        <section class="body-sel">
            <section id="side-menu">
                <div class="logo">

                    <h2>Issue Tracker</h2>
                </div>
                <div class="iterm-menu">
                    <li><i class="fas fa-chart-pie"></i><a href="adminDashboard.jsp">Dashboard</a></li>
                    <li><i class="fas fa-bug"></i><a href="issues.jsp">Issues</a></li>
                    <li><i class="fas fa-chart-line"></i><a href="#">Reports</a></li>
                    <li><i class="fas fa-procedures"></i><a href="supplier.jsp">Supplier</a></li>
                    <li><i class="fas fa-users"></i><a href="users.jsp">Users</a></li>
                    <li><i class="fas fa-user"></i><a href="Profile.jsp">Profile</a></li>
                    <li><i class="fas fa-sign-out-alt"></i><a href="http://localhost:8080/Issue_Tracter/RegisterLoginSerlet?action=logout">Sign Out</a></li>
                </div>
            </section>

            <section id="dash-interface">
                <div class="navigation">
                    <div class="top-nav">
                        <div>
                            <i id="menu-btn" class="fas fa-bars"></i>
                        </div>
                        <div class="search">
                            <i class="fas fa-search"></i>
                            <input type="text" placeholder="Search">
                        </div>
                    </div>

                </div>

                <h3 class="i-name">Dashboard</h3>
                <div class="values">
                    <div class="value-box">
                        <i class="fas fa-tasks"></i>
                        <div>
                            <h3>8,267</h3>
                            <p>Resolved Issues</p>
                        </div>
                    </div>

                    <div class="value-box">
                        <i class="fas fa-th-list"></i>
                        <div>
                            <h3>20,267</h3>
                            <p>Unassigned Issues</p>
                        </div>
                    </div>

                    <div class="value-box">
                        <i class="fas fa-tools"></i>
                        <div>
                            <h3>8,267</h3>
                            <p>Progress Issues</p>
                        </div>
                    </div>

                </div>

            </section>
        </section>
        <script>
            $('#menu-btn').click(function () {
                $('#side-menu').toggleClass("active");
            });
        </script>
    </body>
</html>
