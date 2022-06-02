/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.model;

import java.io.Serializable;

/**
 *
 * @author 27769
 */
public class User implements Serializable{
    private int userID;
    private String firstName;
    private String lasName;
    private String email;
    private String telephone;
    private String role;

    public User() {
    }

    public User(String firstName, String lasName, String email, String telephone) {
        this.firstName = firstName;
        this.lasName = lasName;
        this.email = email;
        this.telephone = telephone;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lasName
     */
    public String getLasName() {
        return lasName;
    }

    /**
     * @param lasName the lasName to set
     */
    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", firstName=" + firstName + ", lasName=" + lasName + ", email=" + email + ", telephone=" + telephone + ", role=" + role + '}';
    }
    
    
}
