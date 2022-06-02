/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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

/**
 *
 * @author 27769
 */
@MultipartConfig()
@WebServlet(name = "IssueServlet", urlPatterns = {"/IssueServlet"})
public class IssueServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typeofIssue = request.getParameter("action");
        if ("getIssue".equalsIgnoreCase(typeofIssue)) {
            getIssue(request, response);
        }
    }

    /**
     *
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

        String typeofIssue = request.getParameter("action");

        if ("createTicket".equalsIgnoreCase(typeofIssue)) {
            insetIssue(request, response);
        } else if ("assign".equalsIgnoreCase(typeofIssue)) {
            assignTicket(request, response);
        } else if ("resolve".equalsIgnoreCase(typeofIssue)) {
            resolveIssue(request, response);
        }

    }

    private void insetIssue(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        HttpSession session = request.getSession();
        IssueService issueService = new IssueServiceImple(dbpool);

        String typeofIssue = request.getParameter("typeofissue");
        String sreetName = request.getParameter("sreet");
        String town = request.getParameter("town");
        String zipCodeString = request.getParameter("zipCode");
        String IssueDiscription = request.getParameter("IssueDiscription");
        Part filecover = request.getPart("filecover");

        int zipCode = 0;
        String picture = null;
        if (zipCodeString != null) {
            try {
                zipCode = Integer.parseInt(zipCodeString);
            } catch (NumberFormatException nfe) {
                System.out.println("Error!! " + nfe.getMessage());
            }
        }

        if (typeofIssue != null && sreetName != null && town != null && IssueDiscription != null) {
            User user = (User) session.getAttribute("user");
            Issue issue = new Issue(user.getUserID(), zipCode, typeofIssue, IssueDiscription, sreetName, town, picture);
            if (issueService.addTicket(issue)) {
                List<Issue> listOfIssue = issueService.getTickets();

                if ("client".equalsIgnoreCase(user.getRole())) {
                    List<Issue> listIssue = issueService.getTickets();
                    List<Issue> unsolveIssue = null;
                    List<Issue> issuesUser = null;
                    if (listIssue != null) {
                        issuesUser = listIssue.stream().filter(issues -> issues.getUserid() == user.getUserID()).collect(Collectors.toList());
                        unsolveIssue = issuesUser.stream().filter(ticket -> ticket.getUserid() == user.getUserID() && (!(ticket.getStatus().equalsIgnoreCase("resolved"))) && (!(ticket.getStatus().equalsIgnoreCase("closed")))).collect(Collectors.toList());
                    }
                    session.setAttribute("unsolveIssue", unsolveIssue);
                    session.setAttribute("issuesUser", issuesUser);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("client.jsp");
                    requestDispatcher.forward(request, response);
                } else if ("supplier".equalsIgnoreCase(user.getRole())) {

                    List<Issue> listIssue = issueService.getTickets();
                    List<Issue> unsolveIssue = null;
                    List<Issue> issuesUser = null;
                    if (listIssue != null) {
                        issuesUser = listIssue.stream().filter(issues -> issues.getUserid() == user.getUserID()).collect(Collectors.toList());
                        unsolveIssue = issuesUser.stream().filter(ticket -> ticket.getUserid() == user.getUserID() && (!(ticket.getStatus().equalsIgnoreCase("resolved"))) && (!(ticket.getStatus().equalsIgnoreCase("closed")))).collect(Collectors.toList());
                    }
                    session.setAttribute("unsolveIssue", unsolveIssue);
                    session.setAttribute("issuesUser", issuesUser);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplierDashboard.jsp");
                    requestDispatcher.forward(request, response);
                } else if ("admin".equalsIgnoreCase(user.getRole())) {

                    session.setAttribute("listOfIssue", listOfIssue);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("issues.jsp");
                    requestDispatcher.forward(request, response);
                }

            }
        }
    }

    private void resolveIssue(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        HttpSession session = request.getSession();
        IssueService issueService = new IssueServiceImple(dbpool);
        AssignTicketService assignTicketService = new AssignTicketServiceImple(dbpool);
        User user = (User) session.getAttribute("user");
        String stringIssueID = request.getParameter("issueId");

        String status = request.getParameter("status");

        int issueId = 0;
        if (stringIssueID != null) {
            try {
                issueId = Integer.parseInt(stringIssueID);
            } catch (NumberFormatException nfe) {
                System.out.println("Error!! " + nfe.getMessage());
            }
        }

        if (status == null) {
            String msg = "Please select the status";
            request.setAttribute("msg", msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("issues.jsp");
            requestDispatcher.forward(request, response);
        }

        //update the status
        if (issueService.statusTicket(status, issueId) == false) {
            String msg = "Please note an error had occured. Please try again";
            request.setAttribute("msg", msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("issues.jsp");
            requestDispatcher.forward(request, response);
        }

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

        session.setAttribute("unsolveIssue", unsolveIssue);
        session.setAttribute("unsolveAssignList", unsolveAssignList);
        session.setAttribute("issuesUser", issuesUser);
        session.setAttribute("assignList", assignList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplierDashboard.jsp");
        requestDispatcher.forward(request, response);

    }

    private void getIssue(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        HttpSession session = request.getSession();
        IssueService issueService = new IssueServiceImple(dbpool);
        SupplierService supplierService = new SupplierServiceImple(dbpool);

        String stringIssueID = request.getParameter("issueId");

        int issueId = 0;
        if (stringIssueID != null) {
            try {
                issueId = Integer.parseInt(stringIssueID);
            } catch (NumberFormatException nfe) {
                System.out.println("Error!! " + nfe.getMessage());
            }
        }

        Issue issue = issueService.getTicket(issueId);
        List<Supplier> suppliers = supplierService.getSuppliers();

        if (issue != null) {

            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (user.getRole().equalsIgnoreCase("client")) {
                    request.setAttribute("issue", issue);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientCreateticket.jsp");
                    requestDispatcher.forward(request, response);
                } else if (user.getRole().equalsIgnoreCase("admin")) {
                    request.setAttribute("issue", issue);
                    request.setAttribute("suppliers", suppliers);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("issueForm.jsp");
                    requestDispatcher.forward(request, response);
                } else if (user.getRole().equalsIgnoreCase("supplier")) {
                    request.setAttribute("issue", issue);
                    request.setAttribute("suppliers", suppliers);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("supplierIssueForm.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("issues.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    private void assignTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        DBPoolManagerBasic dbpool = (DBPoolManagerBasic) context.getAttribute("dbconn");
        HttpSession session = request.getSession();
        IssueService issueService = new IssueServiceImple(dbpool);
        SupplierService supplierService = new SupplierServiceImple(dbpool);
        AssignTicketService assignTicketService = new AssignTicketServiceImple(dbpool);
        String msg;
        String stringIssueID = request.getParameter("issueId");
        String stringUserID = request.getParameter("userId");

        int issueId = 0;
        int userId = 0;
        if (stringIssueID != null && stringUserID != null) {
            try {
                issueId = Integer.parseInt(stringIssueID);
                userId = Integer.parseInt(stringUserID);
            } catch (NumberFormatException nfe) {
                System.out.println("Error!! " + nfe.getMessage());
            }
        }

        Assign assign = assignTicketService.assignIssue(issueId);

        if (assign != null) {
            msg = "Please that the ticke has been assined";
            Issue issue = issueService.getTicket(issueId);
            List<Supplier> suppliers = supplierService.getSuppliers();
            request.setAttribute("msg", msg);
            request.setAttribute("issue", issue);
            request.setAttribute("suppliers", suppliers);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("issueForm.jsp");
            requestDispatcher.forward(request, response);
        } else {
            if (assignTicketService.addAssignIssue(userId, issueId)) {

                if (issueService.updateTicket("assinged", issueId)) {
                    List<Issue> listIssue = issueService.getTickets();

                    List<Issue> unassignedIssues = null;
                    if (listIssue != null) {
                        unassignedIssues = listIssue.stream().filter(issue -> "unassigned".equalsIgnoreCase(issue.getAssignedStatus()))
                                .collect(Collectors.toList());
                    }
                    session.setAttribute("unassignedIssues", unassignedIssues);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("issues.jsp");
                    requestDispatcher.forward(request, response);
                }

            } else {
                msg = "An error had occured. Please save again";
                Issue issue = issueService.getTicket(issueId);
                List<Supplier> suppliers = supplierService.getSuppliers();
                request.setAttribute("msg", msg);
                request.setAttribute("issue", issue);
                request.setAttribute("suppliers", suppliers);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("issueForm.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

}
