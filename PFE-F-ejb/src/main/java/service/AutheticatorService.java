/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Admin;
import entities.Employee;
import entities.Student;
import iservice.AutheticatorInterfaceRemote;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tryvl
 */
@Stateful
@LocalBean
public class AutheticatorService implements AutheticatorInterfaceRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AutheticatorService() {
    }

    @Override
    public Employee getAutheticatedEmployee(String username, String password) {
        Employee employee = new Employee();

        try {
            employee = (Employee) em.createQuery("SELECT E FROM Employee E Where E.login = :login")
                    .setParameter("login", username).getSingleResult();
        } catch (NoResultException ex) {
            employee.setEmployee_id(0L);
            return employee;
        }

        try {
            employee = (Employee) em.createQuery("SELECT E FROM Employee E Where E.login = :login AND E.password = :pwd")
                    .setParameter("login", username).setParameter("pwd", password).getSingleResult();
        } catch (NoResultException ex) {
            employee.setEmployee_id(-1L);
            return employee;
        }
        return employee;
    }

    @Override
    public Admin getAutheticatedAdmin(String username, String password) {
        Admin admin = new Admin();

        try {
            admin = (Admin) em.createQuery("SELECT E FROM Admin E Where E.login = :login")
                    .setParameter("login", username).getSingleResult();

            try {
                admin = (Admin) em.createQuery("SELECT E FROM Admin E Where E.login = :login AND E.password = :pwd")
                        .setParameter("login", username).setParameter("pwd", password).getSingleResult();
            } catch (NoResultException ex) {
                Admin Error = new Admin();
                Error.setEmail("*");
                return Error;
            }

        } catch (NoResultException ex) {
            Admin Error = new Admin();
            Error.setEmail("-");
            return Error;
        }

        return admin;
    }

    @Override
    public Student getAutheticatedStudent(String username, String password) {
    	Student student = new Student();

         try {
        	 student = (Student) em.createQuery("SELECT S FROM Student S Where S.professionalEmail = :login")
                     .setParameter("login", username).getSingleResult();
         } catch (NoResultException ex) {
        	 student.setStudent_id(0L);;
             return student;
         }

         try {
        	 student = (Student) em.createQuery("SELECT S FROM Student S Where S.professionalEmail = :login AND S.password = :pwd")
                     .setParameter("login", username).setParameter("pwd", password).getSingleResult();
         } catch (NoResultException ex) {
        	 student.setStudent_id(-1L);
             return student;
         }
         return student;
    }
    
    
    public Employee logInToEmp(String login, String password){
    	Employee e = new Employee();
    	try{
    		
    	 e = (Employee) em.createQuery("select e from Employee e where e.login = :login and password = :password")
    			.setParameter("login", login).setParameter("password", password).getSingleResult();
    	return e;}
     catch (NoResultException ex) {
        e.setEmployee_id(0L);
        return e;
    }
    }

}
