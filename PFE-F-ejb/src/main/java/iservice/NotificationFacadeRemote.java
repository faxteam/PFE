/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.Notifications;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Xandrayice
 */
@Remote
public interface NotificationFacadeRemote {

    void create(Notifications notification);

    void edit(Notifications notification);

    void remove(Notifications notification);

    Notifications find(Object id);

    List<Notifications> findAll();

    List<Notifications> findRange(int[] range);

    int count();
    
}
