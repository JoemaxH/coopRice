package com.mycompany.mavenproject1;


import HibernateUtil.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Max
 */
public class Main {
    public static void main(String[] args){
        System.out.println("**********CREATE*********");
        List allusers = list();
        SystemUser su = new SystemUser(allusers.size(), "Tack", "Flauer");
        su = save(su);
        System.out.println("Total users" );
        
        System.out.println("**********READ*********");
        SystemUser su2 = read(2);
        System.out.println("id: "+su2.getId()+" username: "+ su2.getUsername());
        System.out.println("********UPDATE**********");
        SystemUser su3 = new SystemUser(2, "Rack", "Flauer");
        update(su3);
        read(su3.getId());
        System.out.println("id: "+su2.getId()+" username: "+ su2.getUsername());
        delete(su3);
    }


    private static SystemUser save(SystemUser su){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        if(userExists(su.getUsername()) == null){
            int id = (int) session.save(su);
            su.setId(id);
            session.getTransaction().commit();
            session.close();
        } else{
            //something about user already exists
            System.out.println("Username already exists!" );
        }
        
        
        return su;
    }
    public static SystemUser userExists(String userName){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(SystemUser.class);
        Criterion cond1 = Restrictions.eq("username", userName);
        crit.add(Restrictions.and(cond1));
        return (SystemUser) crit.uniqueResult();
    }
    
    private static List list(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List userList = session.createQuery("FROM SystemUser").list();
        session.close();
        return userList;
    }
    private static SystemUser read(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SystemUser su = (SystemUser) session.get(SystemUser.class, id);
        session.close();
        return su;
    }
    private static SystemUser update(SystemUser su){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.merge(su);
        session.getTransaction().commit();
        session.close();
        return su;
    }
    private static void delete(SystemUser su){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(su);
        session.getTransaction().commit();
        session.close();
    }
    /*
    private static  read(int id){
        SessionFactory sf = HibernateUtil()getSessionFactory();
        
    }*/
}