/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.util.List;
import za.co.bigone.DAO.AssignDAO;
import za.co.bigone.DAO.AssignDAOImple;
import za.co.bigone.DAO.IssueDAOImple;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Assign;

/**
 *
 * @author 27769
 */
public class AssignTicketServiceImple implements AssignTicketService {
    AssignDAO assignDAO;
    public AssignTicketServiceImple(DBPoolManagerBasic dbm) {
        this.assignDAO = new AssignDAOImple(dbm);
    }

    @Override
    public List<Assign> assignedIssues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Assign> assignedIssueByIssue(int userId) {
       return this.assignDAO.assignedIssueByIssue(userId);
    }

    @Override
    public Assign assignIssue(int issueId) {
        return assignDAO.assignIssue(issueId);
    }

    @Override
    public boolean addAssignIssue(int userId, int issueId) {
        return assignDAO.addAssignIssue(userId, issueId);
    }

}
