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
import pojo.TblOrder;



/**
 *
 * @author aleak
 */
public class DAOorder {
    public List<TblOrder> getBy(String uMen,String uJml,String uHrg,String uTtl) 
   {
       Transaction trans = null;
       TblOrder odr = new TblOrder();
       List<TblOrder> order = new ArrayList();
       
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       try{
           trans = session.beginTransaction();
           Query query = session.createQuery("from TblUser where namaMenu=:uMen AND jumlahOrder=:uJml AND harga=:uHrg AND String total=:uTtl");
           query.setString("uMen", uMen);
           query.setString("uJml", uJml);
           query.setString("uHrg", uHrg);
           query.setString("uTtl", uTtl);
           odr = (TblOrder) query.uniqueResult();
           order = query.list();
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
       return order;
   }
    
    
    public void add_order(TblOrder order)
   {
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       try{
           trans = session.beginTransaction();
           session.save(order);
           trans.commit();
       }catch(Exception e){
           System.out.println(e);
       }
   }
    
    
    public List<TblOrder> retrieveTblOrder()
   {
       List odr1 = new ArrayList();
       TblOrder odr = new TblOrder();
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       try{
           trans = session.beginTransaction();
           Query query = session.createQuery("from TblOrder");
            odr1 = query.list();
            odr1.add(odr.getNoOrder());
            odr1.add(odr.getNamaMenu());
            odr1.add(odr.getJumlahOrder());
            odr1.add(odr.getHarga());
            odr1.add(odr.getTotal());
            trans.commit();  
       }catch(Exception e){
           System.out.println(e);
       }
       return odr1;
   }
    
    
    
    public List<TblOrder> getbyID(String idO)
   {
       TblOrder odr = new TblOrder();
       List<TblOrder> iOdr = new ArrayList();
       
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           Query query = session.createQuery("from TblOrder where no_order= :id");
           query.setString("id", idO);
           odr = (TblOrder) query.uniqueResult();
           iOdr = query.list();
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
       return iOdr;
   }
    
    
    public void deleteOrder(Integer idO)
   {
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           TblOrder odr = (TblOrder) session.load(TblOrder.class, new Integer(idO));
           session.delete(odr);
           trans.commit();
       }catch (Exception e){
           System.out.println(e);
       }
   }
   
   public void editOrder(TblOrder odr)
   {
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           session.update(odr);
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
   }
   
   public void resetOrder(TblOrder odr)
   {
       Transaction trans = null;
       Session session = CoffeeShopUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           session.clear();
           trans.commit();
       }catch(Exception e){
           System.out.println(e);
       }
   }
}
