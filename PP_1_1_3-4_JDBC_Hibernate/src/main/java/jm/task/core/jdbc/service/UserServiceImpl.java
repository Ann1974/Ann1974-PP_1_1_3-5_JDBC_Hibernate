package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService { // бизнес - логика
    // связанная с сущностью "пользователь"
    // класс использует объекты userDaoJDBCImpl или userDaoHibernate для вызова методов

    UserDaoJDBCImpl userDaoJDBCImpl  = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


    public void createUsersTable(){
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        userDaoHibernate.getAllUsers();
        List <User> listUsers = userDaoHibernate.getAllUsers();
       if (listUsers != null && !listUsers.isEmpty()){
           listUsers.forEach(System.out::print);

       }
       return listUsers;
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
