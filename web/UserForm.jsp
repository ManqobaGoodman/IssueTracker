<%-- 
    Document   : UserForm
    Created on : Mar 27, 2022, 1:08:00 PM
    Author     : 27769
--%>

<%@page import="za.co.bigone.model.User"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setHeader("Expire", "0");
    if(session.getAttribute("user") == null){
        response.sendRedirect("signin-singup.jsp");
    }
    String message = (String) request.getAttribute("message");
    if (message == null) {
        message = "";
    }
    User user = (User) request.getAttribute("user");
    User userLogin = (User) session.getAttribute("user");
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
        <title>User-Form</title>
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

                <h3 class="i-name">User Form</h3>
                <%
                    if (user != null) {
                %>
                <div class="form-div">
                    <div class="form-form">
                        <form action="RegisterLoginSerlet" method="POST" >
                            <input type="text" name="firstname" value="<%= user.getFirstName()%>" required />
                            <input type="text" name="lastname" value="<%= user.getLasName()%>" required />
                            <input type="text" name="email" value="<%= user.getEmail()%>" required />
                            <input type="text" name="teleponeNum" value="<%= user.getTelephone()%>" required />
                            <br><br>
                            <button type="submit" class="btn btn-success">Save</button>

                            <input type="hidden" class="inpuut-field" name="action" value="update">
                            <input type="hidden" class="inpuut-field" name="userId" value="<%= user.getUserID()%>" >
                        </form>
                    </div>
                </div>

                <%
                } else {
                %> 
                <div class="form-div">
                    <div class="form-form">
                        <form action="RegisterLoginSerlet" method="POST" >
                            <input type="text" name="firstname" placeholder="First Name" required />
                            <input type="text" name="lastname" placeholder="Enter Town" required />
                            <input type="text" name="email" placeholder="Enter Zip Code" required />
                            <input type="text" name="teleponeNum" placeholder="Enter Zip Code" required />
                            <input type="password" name="password" placeholder="Enter Zip Code" required />
                            <br>
                            <select  name="role">
                                <option>Please Select a role</option>
                                <option value="admin">Admin</option>
                                <option value="client">Client</option>
                                <option value="supplier">supplier</option>
                            </select>
                            <br><br>
                            <button type="submit" class="btn btn-success">Save</button>

                            <input type="hidden"  name="action" value="addAdmin" >
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
