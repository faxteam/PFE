/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.PFE_Form;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.PFE_FormFacadeRemote;

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
    
}
