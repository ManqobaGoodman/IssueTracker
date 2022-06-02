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
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public class UserDAOImple implements UserDAO {

    DBPoolManagerBasic dbm;
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAOImple(DBPoolManagerBasic dbm) {
        this.dbm = dbm;

    }

    @Override
    public User logIn(String email, String password) {
        User user = null;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM users_table WHERE email =? AND passwords = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
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

        return user;
    }

    @Override
    public User getUser(int userId) {
        User user = null;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM users_table WHERE userid =?");
            preparedStatement.setInt(1, userId);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
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

        return user;

    }

    @Override
    public boolean isRegistered(String email) {
        boolean isRegister= false;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM users_table WHERE email =?");
            preparedStatement.setString(1, email);

            //getting the results
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               isRegister = true;
            System.out.println("IsRegistered: "+isRegister); 
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
        return isRegister;
    }

    @Override
    public boolean registerUser(String fistName, String lastName, String email, String telephone, String password, String role) {
        boolean isRegister = false;
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("INSERT INTO users_table (firstname,lastname,email,telephone,role,passwords,createdtime) VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP())");
            preparedStatement.setString(1, fistName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telephone);
            preparedStatement.setString(5, role);
            preparedStatement.setString(6, password);
            
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

    @Override
    public List<User> getUsers() {
        User user;
        List<User> users = new ArrayList<>();
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM users_table");


            //getting the results
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
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

        return users;
    }

    @Override
    public boolean isDelete(int userId) {

        boolean isDeleted = false;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("DELETE FROM users_table WHERE  userid=?");
            preparedStatement.setInt(1, userId);
            
            isDeleted = preparedStatement.executeUpdate() > 0;
            
            
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

        return isDeleted;
    }

    @Override
    public boolean isUpdated(int userId, String fistName, String lastName, String email, String telephone) {

         boolean isUpdate = false;

        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("UPDATE users_table SET firstname = ?,lastname = ?,email = ?,telephone = ? WHERE userid =?");
            preparedStatement.setString(1, fistName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telephone);
            preparedStatement.setInt(5, userId);
            
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
    public List<User> userSupplier() {
        User user;
        List<User> users = new ArrayList<>();
        try {
            con = dbm.getConnection();
            //selecting the results
            preparedStatement = con.prepareStatement("SELECT * FROM users_table WHERE role = 'supplier'");


            //getting the results
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLasName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
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

        return users;
    }

}
