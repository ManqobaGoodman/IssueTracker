/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.DAO;

import java.util.List;
import za.co.bigone.model.Assign;

/**
 *
 * @author 27769
 */
public interface AssignDAO {

    public List<Assign> assignedIssues();

    public List<Assign> assignedIssueByIssue(int userId);

    public Assign assignIssue(int issueId);

    public boolean addAssignIssue(int userId, int issueId);
}
