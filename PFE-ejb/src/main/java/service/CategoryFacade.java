/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.CategoryFacadeRemote;
import java.util.List;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class CategoryFacade implements CategoryFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

   

    @Override
    public long create(Category category) {
        em.persist(category);
        return category.getCategory_id();
    }

    @Override
    public void edit(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = em.createQuery("from Category",Category.class).getResultList();
        return categories;
        
    }
}
