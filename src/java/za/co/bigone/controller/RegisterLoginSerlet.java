/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Assign;
import za.co.bigone.model.Issue;
import za.co.bigone.model.Supplier;
import za.co.bigone.model.User;
import za.co.bigone.service.AssignTicketService;
import za.co.bigone.service.AssignTicketServiceImple;
import za.co.bigone.service.IssueService;
import za.co.bigone.service.IssueServiceImple;
import za.co.bigone.service.SupplierService;
import za.co.bigone.service.SupplierServiceImple;
import za.co.bigone.service.UserService;
import za.co.bigone.service.UserServiceImple;

/**
 *
 * @author 27769
 */
@WebServlet(name = "RegisterLoginSerlet", urlPatterns = {"/RegisterLoginSerlet"})
public class RegisterLoginSerlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        UserService userService = new UserServiceImple(dbpool);

        HttpSession session = request.getSession();

        String stringId = request.getParameter("userId");
        int userId = 0;
        if (stringId != null) {
            try {
                userId = Integer.parseInt(stringId);
            } catch (NumberFormatException ex) {
                System.out.println("Error!! " + ex.getMessage());
            }
        }

        String fistName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("teleponeNum");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String action = request.getParameter("action");

        if ("detele".equalsIgnoreCase(action)) {

            boolean isDelete = userService.isDelete(userId);
            if (isDelete) {
                List<User> listOfUser = userService.getUsers();
                session.setAttribute("listOfUser", listOfUser);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("users.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("edith".equalsIgnoreCase(action)) {
            User user = userService.getUser(userId);
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
            requestDispatcher.forward(request, response);
        }
        else if ("logout".equalsIgnoreCase(action)) {

            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("signin-singup.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        UserService userService = new UserServiceImple(dbpool);
        IssueService issueService = new IssueServiceImple(dbpool);
        AssignTicketService assignTicketService = new AssignTicketServiceImple(dbpool);

        SupplierService supplierService = new SupplierServiceImple(dbpool);
        HttpSession session = request.getSession();

        String message;
        String stringId = request.getParameter("userId");
        int userId = 0;

        if (stringId != null) {
            try {
                userId = Integer.parseInt(stringId);
            } catch (NumberFormatException ex) {
                System.out.println("Error!! " + ex.getMessage());
            }
        }
        String service = request.getParameter("service");
        String fistName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("teleponeNum");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String action = request.getParameter("action");

        List<Supplier> suppliers = supplierService.getSuppliers();

        if ("client".equalsIgnoreCase(action)) {

            if (email != null && password != null && fistName != null && lastName != null && telephone != null) {
                if (userService.isRegistered(email)) {
                    message = "The email is registered is the system. Please sign in";
                    request.setAttribute("message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    String roleC = "client";
                    if (userService.resigisterUser(fistName, lastName, email, telephone, password, roleC)) {
                        message = "You are succesfull!! Registered. Please login.";
                        request.setAttribute("message", message);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        message = "Please fill the form again. An error had occurred";
                        request.setAttribute("message", message);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            } else {
                message = "Please fill form";
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("addAdmin".equalsIgnoreCase(action)) {
            if (email != null && password != null && fistName != null && lastName != null && telephone != null && role != null) {
                if (userService.isRegistered(email)) {
                    message = "The email is registered is the system. Please sign in";
                    request.setAttribute("message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    if (userService.resigisterUser(fistName, lastName, email, telephone, password, role)) {
                        List<User> listOfUser = userService.getUsers();
                        session.setAttribute("listOfUser", listOfUser);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("users.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        message = "Please fill the form again. An error had occurred";
                        request.setAttribute("message", message);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            } else {
                message = "Please fill form";
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("update".equalsIgnoreCase(action)) {
            if (email != null && fistName != null && lastName != null && telephone != null) {

                if (userService.isUpdated(userId, fistName, lastName, email, telephone)) {
                    List<User> listOfUser = userService.getUsers();
                    session.setAttribute("listOfUser", listOfUser);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("users.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    message = "Unable to update";
                    request.setAttribute("message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                message = "Please fill form";
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserForm.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("login".equalsIgnoreCase(action)) {
            if (email != null && password != null) {
                User user = userService.logIn(email, password);
                if (user != null) {

                    if (user.getRole().equalsIgnoreCase("client")) {

                        List<Issue> listIssue = issueService.getTickets();
                        List<Issue> unsolveIssue = null;
                        List<Issue> issuesUser = null;
                        if (listIssue != null) {
                            issuesUser = listIssue.stream().filter(issue -> issue.getUserid() == user.getUserID()).collect(Collectors.toList());
                            unsolveIssue = issuesUser.stream().filter(ticket -> ticket.getUserid() == user.getUserID() && (!(ticket.getStatus().equalsIgnoreCase("resolved"))) && (!(ticket.getStatus().equalsIgnoreCase("closed")))).collect(Collectors.toList());
                        }

                        session.setAttribute("user", user);
                        session.setAttribute("unsolveIssue", unsolveIssue);
                        session.setAttribute("issuesUser", issuesUser);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("client.jsp");
                        requestDispatcher.forward(request, response);
                    } else if (user.getRole().equalsIgnoreCase("admin")) {
                        List<User> listOfUser = userService.getUsers();
                        List<Issue> listIssue = issueService.getTickets();
                        List<User> supplierUser = userService.userSupplier();
                        List<Issue> unassignedIssues = null;
                        if (listIssue != null) {
                            unassignedIssues = listIssue.stream().filter(issue -> "unassigned".equalsIgnoreCase(issue.getAssignedStatus()))
                                    .collect(Collectors.toList());
                        }

                        session.setAttribute("user", user);
                        session.setAttribute("listIssue", listIssue);
                        session.setAttribute("unassignedIssues", unassignedIssues);
                        session.setAttribute("supplierUser", supplierUser);
                        session.setAttribute("suppliers", suppliers);
                        session.setAttribute("listOfUser", listOfUser);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.jsp");
                        requestDispatcher.forward(request, response);
                    } else if (user.getRole().equalsIgnoreCase("supplier")) {

                        List<Issue> listIssue = issueService.getTickets();
                        List<Assign> assignList = assignTicketService.assignedIssueByIssue(user.getUserID());
                        List<Issue> unsolveIssue = null;
                        List<Issue> issuesUser = null;
                        if (listIssue != null) {
                            issuesUser = listIssue.stream().filter(issue -> issue.getUserid() == user.getUserID()).collect(Collectors.toList());
                            unsolveIssue = issuesUser.stream().filter(ticket -> ticket.getUserid() == user.getUserID() && (!(ticket.getStatus().equalsIgnoreCase("resolved"))) && (!(ticket.getStatus().equalsIgnoreCase("closed")))).collect(Collectors.toList());
                        }

                        List<Assign> unsolveAssignList = null;
                        if (assignList != null) {
                            unsolveAssignList = assignList.stream().filter(ticket -> (!(ticket.getIssue().getStatus().equalsIgnoreCase("resolved"))) && (!(ticket.getIssue().getStatus().equalsIgnoreCase("closed")))).collect(Collectors.toList());
                        }
                        session.setAttribute("user", user);
                        session.setAttribute("unsolveIssue", unsolveIssue);
                        session.setAttribute("issuesUser", issuesUser);
                        session.setAttribute("assignList", assignList);
                        session.setAttribute("unsolveAssignList", unsolveAssignList);

                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplierDashboard.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        message = "Please conntact the admin to give you role. You do not have role in the system";
                        request.setAttribute("message", message);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                        requestDispatcher.forward(request, response);
                    }

                } else {
                    message = "Incorret email or password";
                    request.setAttribute("message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                message = "Please enter your email and password";
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin-singup.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("supplier".equalsIgnoreCase(action)) {

            if (supplierService.addSupplier(userId, service)) {
                suppliers = supplierService.getSuppliers();
                session.setAttribute("suppliers", suppliers);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplier.jsp");
                requestDispatcher.forward(request, response);
            } else {
                message = "Error!! plaese fill the form again";
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("suppllierForm.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if ("logout".equalsIgnoreCase(action)) {

            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("signin-singup.jsp");
        }
    }
}
