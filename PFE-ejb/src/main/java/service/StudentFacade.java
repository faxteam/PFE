/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.StudentFacadeRemote;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> implements StudentFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }
    
}
