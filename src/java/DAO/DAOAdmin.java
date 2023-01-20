/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.CoffeeShopUtil;
import pojo.TblAdmin;


/**
 *
 * @author 62822
 */
public class DAOAdmin {
    public List<TblAdmin> getBY(String uName,String uPass)
    {
        Transaction trans = null;
        TblAdmin us = new TblAdmin();
        List<TblAdmin> user = new ArrayList();
        
        Session session = CoffeeShopUtil.getSessionFactory().openSession();
        try {
           trans = session.beginTransaction();
           Query query = session.createQuery("from TblAdmin where username=:uName AND password=:uPass");
           query.setString("uName", uName);
           query.setString("uPass", uPass);
           us = (TblAdmin) query.uniqueResult();
           user = query.list();
           trans.commit();
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
       
       
    }
}