/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import za.co.bigone.model.Issue;
import za.co.bigone.model.User;



/**
 *
 * @author Student24
 */
public interface MailService {
    boolean sentMail(User user,String subject, Issue issue);
}
