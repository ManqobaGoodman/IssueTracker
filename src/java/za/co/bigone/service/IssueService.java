/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.util.List;
import za.co.bigone.model.Issue;

/**
 *
 * @author 27769
 */
public interface IssueService {

    public Issue getTicket(int issueId);

    public List<Issue> getTickets();

    public boolean addTicket(Issue issue);

    public boolean updateTicket(String assignedStatus, int issueId);

    public boolean statusTicket(String status, int issueId);
}
