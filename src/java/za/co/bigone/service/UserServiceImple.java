/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import za.co.bigone.DAO.UserDAO;
import za.co.bigone.DAO.UserDAOImple;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public class UserServiceImple implements UserService {

    UserDAO userDAO;

    public UserServiceImple(DBPoolManagerBasic dbm) {
        this.userDAO = new UserDAOImple(dbm);
    }

    @Override
    public User logIn(String email, String password) {
        //c
        return userDAO.logIn(email, encryptPassword(password));
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    @Override
    public boolean isRegistered(String email) {
        return userDAO.isRegistered(email);
    }

    @Override
    public boolean resigisterUser(String fistName, String lastName, String email, String telephone, String password, String role) {
        return userDAO.registerUser(fistName, lastName, email, telephone, encryptPassword(password),role);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }



private String encryptPassword(String password){
        StringBuilder  stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            
            byte[] resultByToArray = messageDigest.digest();
            
            
            for (byte b : resultByToArray) {
                stringBuilder.append(String.format("%2x", b));
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error!!!! "+ex.getMessage());
        }catch (Exception ex) {
           System.out.println("Error!!!! "+ex.getMessage()); 
        }
        
        return stringBuilder.toString();
    }

    @Override
    public boolean isDelete(int userId) {
        return userDAO.isDelete(userId);
    }

    @Override
    public boolean isUpdated(int userId, String fistName, String lastName, String email, String telephone) {
        return userDAO.isUpdated(userId, fistName, lastName, email, telephone);
    }

    @Override
    public List<User> userSupplier() {
         return userDAO.userSupplier();
    }
}