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
import za.co.bigone.model.Assign;
import za.co.bigone.model.Issue;
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public class AssignDAOImple implements AssignDAO{
    
    DBPoolManagerBasic dbm;
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final IssueDAO issueDAO;
    private final UserDAO userDAO;

    public AssignDAOImple(DBPoolManagerBasic dbm) {
        this.dbm = dbm;
        this.issueDAO = new  IssueDAOImple(dbm);
        this.userDAO = new UserDAOImple(dbm);

    }

    @Override
    public List<Assign> assignedIssues() {
        Assign assign;
        User user;
        Issue issue;
        List<Assign> assigns = null;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM assign_table");

            //getting the results
            resultSet = preparedStatement.executeQuery();
            assigns = new ArrayList<>();
            while (resultSet.next()) {
                assign = new Assign();
               int userId = resultSet.getInt("userid");
               int issueId = resultSet.getInt("issueId");
               user = userDAO.getUser(userId);
               issue = issueDAO.getTicket(issueId);
               
               assign.setIssue(issue);
               assign.setIssueId(issueId);
               assign.setUser(user);
               assign.setUserId(userId);
               assigns.add(assign);
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

        return assigns;
    }

    @Override
    public List<Assign> assignedIssueByIssue(int userId) {
        
        Assign assign;
        User user;
        Issue issue;
        List<Assign> assigns = null;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM assign_table WHERE userid = ?");
            preparedStatement.setInt(1, userId);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            assigns = new ArrayList<>();
            while (resultSet.next()) {
                assign = new Assign();
               int issueId = resultSet.getInt("issueId");
               user = userDAO.getUser(userId);
               issue = issueDAO.getTicket(issueId);
               
               assign.setIssue(issue);
               assign.setIssueId(issueId);
               assign.setUser(user);
               assign.setUserId(userId);
               assigns.add(assign);
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

        return assigns;
    }

    @Override
    public Assign assignIssue(int issueId) {
        Assign assign = null;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM assign_table WHERE issueId = ?");
            preparedStatement.setInt(1, issueId);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userid");
                assign = new Assign(issueId, issueId);
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

        return assign;
    }

    @Override
    public boolean addAssignIssue(int userId, int issueId) {
        boolean isRegister = false;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("INSERT INTO assign_table (userid,issueId,assignedTime) VALUES (?,?,CURRENT_TIMESTAMP())");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, issueId);
            
            isRegister = preparedStatement.executeUpdate()>0;
           
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
    
}
