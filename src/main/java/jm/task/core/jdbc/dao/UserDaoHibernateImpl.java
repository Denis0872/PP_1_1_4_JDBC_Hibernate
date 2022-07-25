package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getConnection();
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS maven.users" +
                    " (id int not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS maven.users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User  " + name + " добавлен в бд ");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            User user = session.get(User.class, id);
                    if(user!=null){
                        session.delete(user);
                        System.out.println("пользователь с id: "+id+" удален");
                    }
                    else {
                        System.out.println("пользователь с id: "+id+" не существует");
                    }
        //    session.delete(session.get(User.class, id));
            transaction.commit();
            }
        catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("From User ").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return userList;
    }
//    @Override
//    public List<User> getAllUsers() {
//        List<User>list = new ArrayList<>();
//        try(Session session = sessionFactory.openSession()) {
//            Query userList1 = session.createQuery("From User ");
//            list = userList1.getResultList();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE maven.users;").executeUpdate();
            transaction.commit();
            System.out.println("Таблица пуста");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
