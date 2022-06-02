/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.DAO;

import java.util.List;
import za.co.bigone.model.Supplier;
import za.co.bigone.model.User;

/**
 *
 * @author 27769
 */
public interface SuppierDAO {

    public List<Supplier> getSuppliers();

    public Supplier getSupplier(int userId);

    public boolean addSupplier(int userId, String service);

    public boolean updateSuppier(int userId, String service);
}
