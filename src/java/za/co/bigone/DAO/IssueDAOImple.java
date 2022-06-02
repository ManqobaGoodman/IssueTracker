/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Issue;

/**
 *
 * @author 27769
 */
public class IssueDAOImple implements IssueDAO {
    
    DBPoolManagerBasic dbm;
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public IssueDAOImple(DBPoolManagerBasic dbm) {
        this.dbm = dbm;
        
    }
    
    @Override
    public Issue getTicket(int issueId) {
        
        Issue issue = null;
        
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM issue_table WHERE issueid =?");
            preparedStatement.setInt(1, issueId);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                issue = new Issue();
                issue.setIssueID(resultSet.getInt("issueId"));
                issue.setTypeofissue(resultSet.getString("typeofissue"));
                issue.setStreetName(resultSet.getString("StreetName"));
                issue.setIssueDiscription(resultSet.getString("IssueDiscription"));
                issue.setAssignedStatus(resultSet.getString("assignedStatus"));
                issue.setTown(resultSet.getString("town"));
                issue.setStatus(resultSet.getString("IssueStatus"));
                issue.setIssuePicture(resultSet.getString("issuePicture"));
                issue.setZipCode(resultSet.getInt("zipCode"));
            }
        } catch (SQLException ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
        }
        
        return issue;
    }
    
    @Override
    public List<Issue> getTickets() {
        Issue issue;
        List<Issue> issues = null;
        
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM issue_table");

            //getting the results
            resultSet = preparedStatement.executeQuery();
            issues = new ArrayList<>();
            while (resultSet.next()) {
                issue = new Issue();
                issue.setIssueID(resultSet.getInt("issueId"));
                issue.setUserid(resultSet.getInt("userid"));
                issue.setTypeofissue(resultSet.getString("typeofissue"));
                issue.setIssueDiscription(resultSet.getString("IssueDiscription"));
                issue.setStreetName(resultSet.getString("StreetName"));
                issue.setAssignedStatus(resultSet.getString("assignedStatus"));
                issue.setTown(resultSet.getString("town"));
                issue.setStatus(resultSet.getString("IssueStatus"));
                issue.setIssuePicture(resultSet.getString("issuePicture"));
                issue.setZipCode(resultSet.getInt("zipCode"));
                issues.add(issue);
            }
        } catch (SQLException ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
        }
        
        return issues;
    }
    
    @Override
    public boolean addTicket(Issue issue) {
        boolean isRegister = false;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("INSERT INTO issue_table (userid,typeofissue, IssueDiscription, StreetName, town, zipCode, createtime)\n"
                    + "VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP())");
            preparedStatement.setInt(1, issue.getUserid());
            preparedStatement.setString(2, issue.getTypeofissue());
            preparedStatement.setString(3, issue.getIssueDiscription());
            preparedStatement.setString(4, issue.getStreetName());
            preparedStatement.setString(5, issue.getTown());
            preparedStatement.setInt(6, issue.getZipCode());
            
            isRegister = preparedStatement.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
        }
        
        return isRegister;
    }
    
    @Override
    public boolean updateTicket(String assignedStatus, int issueId) {
        boolean isUpdate = false;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("UPDATE issue_table SET assignedStatus = ? WHERE issueId = ?");
            preparedStatement.setString(1, assignedStatus);
            preparedStatement.setInt(2, issueId);
            
            isUpdate = preparedStatement.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
        }

        return isUpdate;
    }

    @Override
    public boolean statusTicket(String status, int issueId) {
        boolean isUpdate = false;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("UPDATE issue_table SET IssueStatus = ? WHERE issueId = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, issueId);
            
            isUpdate = preparedStatement.executeUpdate() > 0;
            
            
        } catch (SQLException ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error!!! " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error!!! " + ex.getMessage());
                }
            }
        }

        return isUpdate;
    }
    
}
