package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS maven.users" +
                    " (id int not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS maven.users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (HibernateException e) {
            e.printStackTrace();
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
                    session.delete(user);
                    System.out.println("пользователь с id: "+id+" удален");
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
        try(Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        userList = session.createQuery("From User ").list();
        session.getTransaction().commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE maven.users;").executeUpdate();
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
