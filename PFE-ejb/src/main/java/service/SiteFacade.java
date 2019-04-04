/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Site;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.SiteFacadeRemote;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class SiteFacade extends AbstractFacade<Site> implements SiteFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SiteFacade() {
        super(Site.class);
    }
    
}
