package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {// класс отвечает за то насколько корректно отработают SQL запросы
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Connection connection = Util.connectionIdbc();// Соед.с БД
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `pp_1_1_3-4_JDBC_Hibernate`.`user` " +
                    // создать таблицу, если она еще не существует
                    "(`id` INT NOT NULL AUTO_INCREMENT, " +
                    "`name` VARCHAR(45), " +
                    "`lastName` VARCHAR(45), " +
                    "`age` TINYINT(3)," +
                    "PRIMARY KEY (id))");// метод executeUpdate, количество строк измененных в таблице
            System.out.println("Таблица пользователей успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void dropUsersTable() {
        try(Connection connection = Util.connectionIdbc();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS `pp_1_1_3-4_JDBC_Hibernate`. `user` ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.connectionIdbc();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `pp_1_1_3-4_JDBC_Hibernate`. " +
                    "`user`(name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connectionIdbc()) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM user WHERE id = ?")) {
                statement.setLong(1, id);
                 statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.connectionIdbc();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `pp_1_1_3-4_JDBC_Hibernate`.`user`");
            // выполнение запроса на выбор всех записей из таблицы user
            while (resultSet.next()) {
                // строки
                User user = new User( resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.connectionIdbc();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
