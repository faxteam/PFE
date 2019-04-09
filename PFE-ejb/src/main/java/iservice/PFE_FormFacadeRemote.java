/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.Departement;
import entities.PFE_Form;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Xandrayice
 */
@Remote
public interface PFE_FormFacadeRemote {

    void create(PFE_Form pFE_Form);

    void edit(PFE_Form pFE_Form);

    void remove(PFE_Form pFE_Form);

    PFE_Form find(Object id);

    List<PFE_Form> findAll();

    List<PFE_Form> findRange(int[] range);

    int count();
    
    public PFE_Form findById(Long id);
    
}
