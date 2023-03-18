package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;




public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();


        userService.saveUser("Антон", "Семёнов", (byte) 18);
        userService.saveUser("Семён", "Антонов", (byte) 35);
        userService.saveUser("Игорь", "Михайлов", (byte) 54);
        userService.saveUser("Алла", "Соколова", (byte) 23);
        userService.removeUserById(3);
        userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();


    }

}
