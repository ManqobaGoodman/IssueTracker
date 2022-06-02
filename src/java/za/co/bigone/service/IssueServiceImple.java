/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;


import java.util.List;
import za.co.bigone.DAO.IssueDAO;
import za.co.bigone.DAO.IssueDAOImple;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Issue;

/**
 *
 * @author 27769
 */
public class IssueServiceImple implements IssueService{
     IssueDAO issueDAO;

    public IssueServiceImple(DBPoolManagerBasic dbm) {
        this.issueDAO = new IssueDAOImple(dbm);
    }

    @Override
    public Issue getTicket(int issueId) {
        return issueDAO.getTicket(issueId);
    }

    @Override
    public List<Issue> getTickets() {
        return issueDAO.getTickets();
    }

    @Override
    public boolean addTicket(Issue issue) {
        return issueDAO.addTicket(issue);
    }

    @Override
    public boolean updateTicket(String assignedStatus, int issueId) {
        return issueDAO.updateTicket(assignedStatus,issueId);
    }

    @Override
    public boolean statusTicket(String status, int issueId) {
        return issueDAO.statusTicket(status, issueId);
    }
    
}
    