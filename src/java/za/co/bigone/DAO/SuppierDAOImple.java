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
import za.co.bigone.model.Supplier;
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public class SuppierDAOImple implements SuppierDAO {

    DBPoolManagerBasic dbm;
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SuppierDAOImple(DBPoolManagerBasic dbm) {
        this.dbm = dbm;

    }

    @Override
    public List<Supplier> getSuppliers() {
        Supplier supplier;
        User user;
        List<Supplier> suppliers = new ArrayList<>();
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT users_table.userid,users_table.firstname,users_table.lastname,users_table.email,users_table.telephone,users_table.role,suppier_table.service FROM \n"
                    + "users_table \n"
                    + "INNER  JOIN \n"
                    + "suppier_table ON\n"
                    + "users_table.userid = suppier_table.userId WHERE users_table.role = 'supplier'");

            //getting the results
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String service = resultSet.getString("service");
                user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
                supplier = new Supplier(service, user);
                suppliers.add(supplier);
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

        return suppliers;
    }

    @Override
    public Supplier getSupplier(int userId) {
        Supplier supplier = null;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT users_table.userid,users_table.firstname,users_table.lastname,users_table.email,users_table.telephone,users_table.role,suppier_table.service FROM \n"
                    + "users_table \n"
                    + "INNER  JOIN \n"
                    + "suppier_table ON\n"
                    + "? = ? WHERE users_table.role = 'supplier'");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String service = resultSet.getString("service");
                User user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
                supplier = new Supplier(service, user);
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

        return supplier;
    }

    @Override
    public boolean addSupplier(int userId, String service) {
        boolean isRegister = false;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("INSERT INTO suppier_table (userId,service,createdtime) \n"
                    + "VALUES\n"
                    + "(?,?,CURRENT_TIMESTAMP())");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, service);
            

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
    public boolean updateSuppier(int userId, String service) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
