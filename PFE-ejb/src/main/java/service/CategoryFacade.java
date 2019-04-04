/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
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
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

   

   

    
}
