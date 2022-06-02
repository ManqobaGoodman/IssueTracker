<%-- 
    Document   : supplier
    Created on : Apr 5, 2022, 3:08:45 PM
    Author     : 27769
--%>
<%@page import="za.co.bigone.model.Supplier"%>
<%@page import="za.co.bigone.model.Assign"%>
<%@page import="java.util.List"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setHeader("Expire", "0");
    if(session.getAttribute("user") == null){
        response.sendRedirect("signin-singup.jsp");
    }
    List<Supplier> supplier = (List<Supplier>) session.getAttribute("suppliers");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suppliers</title>
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
                    <li><i class="fas fa-chart-pie"></i><a href="index.jsp">Dashboard</a></li>
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

                <h3 class="i-name">Service Provider(Suppliers) </h3>
                <div class="add-btn"> 				
                    <a href="suppllierForm.jsp" class="btn btn-success">Create Supplier</a>			
                </div>

                <div class="board">
                    <table width="100%">
                        <thead>
                            <tr>
                                <td>UserID</td>
                                <td>First Name</td>
                                <td>Last Name</td>
                                <td>Email</td>
                                <td>Telephone</td>
                                <td>Service</td>
                                <td>Action</td>

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (supplier != null) {
                                    for (Supplier supplier1 : supplier) {
                            %>
                            <tr>
                                <td class="role">
                                    <p><%= supplier1.getUser().getUserID() %></p>
                                </td> 
                                <td class="role">
                                    <p><%= supplier1.getUser().getFirstName() %></p>
                                </td>

                                <td class="role">
                                    <p><%= supplier1.getUser().getLasName() %></p>
                                </td>
                                <td class="role">
                                    <p><%= supplier1.getUser().getEmail() %></p>
                                </td>
                                <td class="role">
                                    <p><%= supplier1.getUser().getTelephone()%></p>
                                </td>

                                <td class="role">
                                    <p><%= supplier1.getService() %></p>
                                </td>

                                <td class="role">
                                    <a href="http://localhost:8080/Issue_Tracter/RegisterLoginSerlet?action=detele&userId=<%= supplier1.getUser().getUserID() %>" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                            <%          }
                                }
                            %>


                        </tbody>
                    </table>
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
