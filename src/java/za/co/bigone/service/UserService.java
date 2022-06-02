/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.util.List;
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public interface UserService {

    public User logIn(String email, String password);

    public boolean isRegistered(String email);

    public boolean resigisterUser(String fistName, String lastName, String email, String telephone, String password, String role);

    public boolean isUpdated(int userId, String fistName, String lastName, String email, String telephone);

    public boolean isDelete(int userId);

    public User getUser(int userId);

    public List<User> getUsers();

    public List<User> userSupplier();
}
