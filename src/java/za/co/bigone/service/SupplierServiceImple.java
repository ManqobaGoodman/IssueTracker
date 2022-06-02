/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.util.List;
import za.co.bigone.DAO.SuppierDAO;
import za.co.bigone.DAO.SuppierDAOImple;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Supplier;

/**
 *
 * @author 27769
 */
public class SupplierServiceImple implements SupplierService{
    SuppierDAO suppierDAO;
    public SupplierServiceImple(DBPoolManagerBasic dbm) {
        this.suppierDAO = new SuppierDAOImple(dbm);
    }

    @Override
    public List<Supplier> getSuppliers() {
        return suppierDAO.getSuppliers();
    }

    @Override
    public Supplier getSupplier(int userId) {
        return suppierDAO.getSupplier(userId);
    }

    @Override
    public boolean addSupplier(int userId, String service) {
        return suppierDAO.addSupplier(userId, service);
    }

    @Override
    public boolean updateSuppier(int userId, String service) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
