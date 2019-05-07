/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Categorys;
import entities.Departement;
import entities.Employee;
import entities.Site;
import entities.Student;

import javax.ejb.LocalBean;
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
@LocalBean
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
			employeeExist = (Employee) em.createQuery("SELECT E FROM Employee E Where E.email = :email or E.login = :login", Employee.class)
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
					em.createQuery("SELECT E FROM Employee E Where E.departement = :dep", Employee.class)
					.setParameter("dep", dep);
			List<Employee> employees = query.getResultList();
			return employees;

		} catch (NoResultException ex) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<Employee> findAllProfs() {
		try {
			TypedQuery<Employee> query =  (TypedQuery<Employee>) 
					em.createQuery("SELECT E FROM Employee E Where E.role = 'professor'", Employee.class);
			List<Employee> employees = query.getResultList();
			return employees;

		} catch (NoResultException ex) {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<Counter> findAllProfsByEnc() {
		try {
			TypedQuery<Counter> query =  (TypedQuery<Counter>) 
					em.createQuery("SELECT e.employee_id, count(e.employee_id) as nbr  FROM employee e inner join pfe_form p where e.employee_id = p.supervisor_employee_id group by e.employee_id", Counter.class);
			List<Counter> employees = query.getResultList();
			return employees;

		} catch (NoResultException ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Employee> findByRole(Employee.Role role) {
		try {
			TypedQuery<Employee> query =  (TypedQuery<Employee>) 
					em.createQuery("SELECT E FROM Employee E Where E.role = :role", Employee.class)
					.setParameter("role", role);
			List<Employee> employees = query.getResultList();
			return employees;

		} catch (NoResultException ex) {
			return new ArrayList<>();
		}
	}

	//SIRINE+OUSSAMA : DEPARTEMENT SUPERVISOR
	public List<Long> list_form_manquante(){
		List<Long> list_form=em.createQuery("select f.form_id from PFE_Form f where f.president_id=1",Long.class).getResultList();
		return list_form;
	}


	//Statisticcccc
	public List<Employee> orderby_supervisions() {
		List<Employee>list=em.createQuery("select f.supervisor from PFE_Form f group by f.supervisor order by count(f.form_id)",Employee.class).getResultList();

		return list;
	}

	//Statisticcccc
	@Override
	public long nbre_supervisions(long id_emp) {
		System.out.println("hayaaaaa amannn");
		long nbre=em.createQuery("select count(f) from PFE_Form f where f.supervisor="+id_emp,Long.class).getSingleResult();
		return nbre;
	}

	@Override
	public List<Employee> orderby_supervisons() {
		List<Employee>emp=em.createQuery("select f from Employee f where f.employee_id=(select supervisor from PFE_Form group by supervisor order by count(form_id))",Employee.class).getResultList();
		return emp;
	}
	@Override
	public void choisir_categorie(long id_c, long id_emp) {
		Categorys c=em.find(Categorys.class, id_c);
		Employee emp=em.find(Employee.class, id_emp);
		emp.setCategory(c);

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

	@Override
	public void chooseCategory(Employee e, Categorys c) {

     /*   ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> students_tmp = new ArrayList<>();
        Site s =e.getDepartement().getSite();
        StudentFacade sF = new StudentFacade();
        students_tmp = (ArrayList<Student>) sF.findAll();
        if (students_tmp != null){
            System.out.println(students_tmp.get(0));
        }else {
            System.out.println("null");
        }
        /*if (students_tmp == null){
            return null;
        }
        else{
            for (int i = 0 ; i< students_tmp.size(); i ++){
                if (students_tmp.get(i).getClasse().getOption().getDepartement().getSite() == s){
                    students.add(students_tmp.get(i));
                }
            }
            return students;
        }*/
       

    }
		

	@Override
	public ArrayList<Student> listFifthYearStudents(Employee e) {
		ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> students_tmp = new ArrayList<>();
        Site s =e.getDepartement().getSite();
        StudentFacade sF = new StudentFacade();
        students_tmp = (ArrayList<Student>) sF.findAll();
        if (students_tmp != null){
            System.out.println(students_tmp.get(0));
        }else {
            System.out.println("null");
        }
        /*if (students_tmp == null){
            return null;
        }
        else{
            for (int i = 0 ; i< students_tmp.size(); i ++){
                if (students_tmp.get(i).getClasse().getOption().getDepartement().getSite() == s){
                    students.add(students_tmp.get(i));
                }
            }
            return students;
        }*/
        return null;
    }

	public Long countSupervisor(long employee_id) {
		try {
			TypedQuery<Long> query =  (TypedQuery<Long>) 
					em.createQuery("select count(f) from PFE_Form f where f.supervisor =:id", Long.class).setParameter("id", employee_id);
			Long nbr = query.getSingleResult();
			return nbr;

		} catch (NoResultException ex) {
			return 0L;
		}
	
	}
	
	@Override
	public List<Long> getProfsOrdred() {
		List<Long>emp=em.createQuery("select p.supervisor.employee_id from PFE_Form p group by p.supervisor.employee_id order by count(form_id) desc",Long.class).getResultList();
		return emp;
	}

	}



