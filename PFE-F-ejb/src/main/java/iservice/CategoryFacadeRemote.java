/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.Categorys;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Xandrayice
 */
@Remote
public interface CategoryFacadeRemote {

	void create(Categorys category);

	void edit(Categorys category);

	void remove(Categorys category);

	Categorys find(Object id);

	List<Categorys> findAll();

	List<Categorys> findRange(int[] range);

	int count();


	//SIRINE+OUSSAMA: DEPARTEMENT SUPERVISOR

	void accept_category_proposed(long category_id);
	void refuse_category_proposed(long id);
	public List<Categorys> listCategorie();
	public List<Categorys> findbyid(long id);
	public Categorys findByName(String nom);
	Categorys findbyidOne(long id);

	//nejmi/PROFESSOR


	void ajouter_categorie(Categorys c);
	List<Categorys> categorie_accepter();
	List<Categorys> existe(String name_category);




}
