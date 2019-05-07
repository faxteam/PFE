/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Classe;
import entities.Employee;
import entities.Student;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import iservice.StudentFacadeRemote;
import javax.persistence.NoResultException;

/**
 *
 * @author Xandrayice
 */
@Stateless
@LocalBean
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

    @Override
    public boolean uniqueStundet(Student student) {
        Student studentExist = new Student();

        try {
            studentExist = (Student) em.createQuery("SELECT S FROM Student S Where S.professionalEmail = :email AND S.ident = :id")
                    .setParameter("email", student.getProfessionalEmail().toLowerCase())
                    .setParameter("id", student.getIdent().toLowerCase())
                    .getSingleResult();
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }

	@Override
	public List<Student> findListByClass(List<Classe> classes) {

        
        try {
            System.out.println("try");
            TypedQuery<Student> query = 
                    (TypedQuery<Student>) em.
                    createQuery("SELECT S FROM Student S GROUP BY S.classe HAVING S.classe IN (:classe)", Student.class)
                    .setParameter("classe", classes);
            List<Student> students = query.getResultList();
            return students;
        }catch(NoResultException ex){
            return new ArrayList<>();
        }
        //return new ArrayList<Student>();
    
	}

	@Override
	public List<Student> findBySite(Employee e) {

        return (this.getEntityManager().createQuery("SELECT S FROM Student S Where s.class_id.opt_id.departement_id.site_id = :site")
                .setParameter("site", e.getDepartement().getSite().getSite_id()).getResultList());
    }

	
    
}
