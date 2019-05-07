/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Facade.AbstractFacade;
import entities.Categorys;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import iservice.CategoryFacadeRemote;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xandrayice
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Categorys> implements CategoryFacadeRemote {

	@PersistenceContext(unitName = "pfe-ejb")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CategoryFacade() {
		super(Categorys.class);
	}
	
	//SIRINE+OUSSAMA :DEPARTEMENT SUPERVISOR
	@Override
	public void accept_category_proposed(long category_id) {
		Categorys c=em.find(Categorys.class, category_id);
		c.setCategory_available(true);

	}

	@Override
	public void refuse_category_proposed(long id) {
		Categorys c=em.find(Categorys.class, id);
		em.remove(c);

	}

	@Override
	public List<Categorys> listCategorie() {
		return em.createQuery("select c from Category c where c.category_available="+false,Categorys.class).getResultList();

	}

	@Override
	public List<Categorys> findbyid(long id) {
		return em.createQuery("select f from Category f where f.category_id ="+id,Categorys.class).getResultList();

	}
	@Override
	public Categorys findbyidOne(long id) {
return em.find(Categorys.class, id);
	}

	@Override
	public Categorys findByName(String nom) {
		Categorys c=em.createQuery("select c from Category c where c.category_name like '%' || :category_name || '%'",Categorys.class).setParameter("category_name", nom).getSingleResult();
		return c;
	}

	@Override
	public void ajouter_categorie(Categorys c) {
  		em.persist(c);
  		
	}

	@Override
	public List<Categorys> categorie_accepter() {
		List<Categorys>emp=em.createQuery("select f from Category f where f.category_available="+true,Categorys.class).getResultList();
		return emp;
	}

	@Override
	public List<Categorys> existe(String name_category) {
		List<Categorys> list=em.createQuery("select f from Category f where f.category_name like '%' || :category_name || '%'",Categorys.class).setParameter("category_name", name_category).getResultList();
		return list;
	}






}
