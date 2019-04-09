/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Departement;
import entities.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.EmployeeFacadeRemote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeRemote {

    @PersistenceContext(unitName = "pfe-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public Boolean uniqueEmployee(Employee employee) {
        Employee employeeExist = new Employee();

        try {
            employeeExist = (Employee) em.createQuery("SELECT E FROM Employee E Where E.email = :email or E.login = :login")
                    .setParameter("email", employee.getEmail().toLowerCase())
                    .setParameter("login", employee.getLogin().toLowerCase())
                    .getSingleResult();
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }

    @Override
    public List<Employee> findByDepartement(Departement dep) {
        try {
            TypedQuery<Employee> query =  (TypedQuery<Employee>) 
                    em.createQuery("SELECT E FROM Employee E Where E.departement = :dep")
                    .setParameter("dep", dep);
            List<Employee> employees = query.getResultList();
            return employees;
            
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Employee> findByRole(Employee.Role role) {
        try {
            TypedQuery<Employee> query =  (TypedQuery<Employee>) 
                    em.createQuery("SELECT E FROM Employee E Where E.role = :role")
                    .setParameter("role", role);
            List<Employee> employees = query.getResultList();
            return employees;
            
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Departement findEmployeeDepartement(Employee employee) {
        Departement departement = new Departement();
        
        try{
            departement = (Departement) em.createQuery("SELECT E.departement FROM Employee E where E= :e")
                    .setParameter("e", employee);
            return departement;
        }catch(NoResultException ex)
        {
            return new Departement();
        }
        
    }

    @Override
    public void createNew(Employee employee) {
        //em.saveOrUpdate(employee);
    }

    @Override
    public Employee findByName(String name) {
        Employee employeeExist = new Employee();

        try {
            employeeExist = (Employee) em.createQuery("SELECT E FROM Employee E Where E.email = :email ", Employee.class)
                    .setParameter("email",name)
                    .getSingleResult();
            return employeeExist;
        } catch (NoResultException ex) {
            return new Employee();
        }
    }

}
