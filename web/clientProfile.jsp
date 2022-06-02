<%-- 
    Document   : client
    Created on : Apr 7, 2022, 11:57:14 AM
    Author     : 27769
--%>

<%@page import="za.co.bigone.model.Supplier"%>
<%@page import="java.util.List"%>
<%@page import="za.co.bigone.model.User"%>
<%@page import="za.co.bigone.model.Issue"%>
<%
    
    
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setHeader("Expire", "0");
    if(session.getAttribute("user") == null){
        response.sendRedirect("signin-singup.jsp");
    }

    User userLogin = (User) session.getAttribute("user");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" type="text/css" href="css/all.css" />
        <link rel="stylesheet" type="text/css" href="css/all.min.css" />
        <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script src="javascript/jquery.js"></script>
    </head>
    <body>
        <section class="body-sel">
            <section id="side-menu">
                <div class="logo">

                    <h2>Issue Tracker</h2>
                </div>
                <div class="iterm-menu">
                    <li><i class="fas fa-chart-pie"></i><a href="client.jsp">Dashboard</a></li>
                    <li><i class="fas fa-bug"></i><a href="issues.jsp">Issues</a></li>
                    <li><i class="fas fa-user"></i><a href="clientProfile.jsp">Profile</a></li>
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

                <h3 class="i-name"> Profile</h3>


                <%
                    if (userLogin != null) {
                %> 

                <div class="form-div">
                    <div class="form-form">
                        <form action="RegisterLoginSerlet" method="POST" >
                            <input type="text" name="firstname" value="<%= userLogin.getFirstName()%>" required />
                            <input type="text" name="lastname" value="<%= userLogin.getLasName()%>" required />
                            <input type="text" name="email" value="<%= userLogin.getEmail()%>" required />
                            <input type="text" name="teleponeNum" value="<%= userLogin.getTelephone()%>" required />
                            <br><br>
                            <button type="submit" class="btn btn-success">Save</button>

                            <input type="hidden"  name="action" value="update">
                            <input type="hidden"  name="userId" value="<%= userLogin.getUserID()%>" >
                        </form>
                    </div>
                </div>



                <%
                    }
                %> 
            </section>
        </section>
        <script>
            $('#menu-btn').click(function () {
                $('#side-menu').toggleClass("active");
            });
        </script>

    </body>
</html>
