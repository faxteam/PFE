/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Departement;
import entities.PFE_Form;
import entities.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.PFE_FormFacadeRemote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class PFE_FormFacade extends AbstractFacade<PFE_Form> implements PFE_FormFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PFE_FormFacade() {
        super(PFE_Form.class);
    }

    @Override
    public PFE_Form findById(Long id) {
        PFE_Form forms = new PFE_Form();
        
        try{
             forms = 
                    (PFE_Form) em.
                    createQuery("SELECT P FROM PFE_Form P GROUP BY P.student HAVING P.form_id = :id", PFE_Form.class)
                    .setParameter("id", id);
            return forms;
        }catch(NoResultException ex){
            return new PFE_Form();
        }
    }
    
    
}
