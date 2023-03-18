package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Transaction transaction = null;

    public UserDaoHibernateImpl() {


    }

    @Override
    public void createUsersTable() {// +
        try (Session session = Util.openSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45)," +
                    "`lastName` VARCHAR(45)," +
                    "`age` TINYINT(3)," +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public void dropUsersTable() { // +
        try (Session session = Util.openSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {// +
        Session session = Util.openSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) { //+
        try (Session session = Util.openSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM user WHERE id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
            session.close();
        }

    }


    @Override
    public List<User> getAllUsers() { // +
        return (List<User>) Util.openSessionFactory().openSession().createQuery("From User").list();

    }


    @Override
    public void cleanUsersTable() { //+
        Session session = Util.openSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("TRUNCATE `pp_1_1_3-4_jdbc_hibernate`.`user`").executeUpdate();
        transaction.commit();
        session.close();


    }

}
