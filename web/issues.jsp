<%-- 
    Document   : users
    Created on : Mar 27, 2022, 1:05:33 PM
    Author     : 27769
--%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="za.co.bigone.model.Issue"%>
<%@page import="java.util.List"%>
<%@page import="za.co.bigone.model.User"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setHeader("Expire", "0");
    if(session.getAttribute("user") == null){
        response.sendRedirect("signin-singup.jsp");
    }
    
    List<Issue> listOfIssue = (List<Issue>) session.getAttribute("unassignedIssues");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Issues</title>
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

            <h3 class="i-name">Unassigned Ticket(Issue) </h3>
            <div class="add-btn"> 				
                <a href="issueForm.jsp" class="btn btn-success">Create Ticket</a>			
            </div>

            <div class="board">
                <table width="100%">
                    <thead>
                        <tr>
                            <td>IssueId</td>
                            <td>type of issue</td>
                            <td>Street Name</td>
                            <td>town</td>
                            <td>status</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (listOfIssue != null) {
                                for (Issue issue : listOfIssue) {

                        %>
                        <tr>
                            <td class="role">
                                <p><%= issue.getIssueID()%></p>
                            </td> 
                            <td class="role">
                                <p><%= issue.getTypeofissue()%></p>
                            </td>

                            <td class="role">
                                <p><%= issue.getStreetName()%></p>
                            </td>

                            <td class="role">
                                <p><%= issue.getTown()%></p>
                            </td>

                            <td class="role">
                                <p><%= issue.getStatus()%></p>
                            </td>

                            <td class="role">
                                <a href="http://localhost:8080/Issue_Tracter/IssueServlet?action=getIssue&issueId=<%= issue.getIssueID()%>" class="btn btn-info">Details</a>                                
                            </td>
                        </tr>

                        <% }
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