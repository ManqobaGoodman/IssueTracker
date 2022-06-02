/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.util.List;
import za.co.bigone.model.Supplier;

/**
 *
 * @author 27769
 */
public interface SupplierService {
    public List<Supplier> getSuppliers();

    public Supplier getSupplier(int userId);

    public boolean addSupplier(int userId, String service);

    public boolean updateSuppier(int userId, String service);
}
