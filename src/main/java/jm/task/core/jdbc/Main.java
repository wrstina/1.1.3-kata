package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("Users table has been created");

        userService.saveUser ("Punya", "Kushat", (byte) 5);
        userService.saveUser ("Puny", "Kusha", (byte) 4);
        userService.saveUser ("Pun", "Kush", (byte) 3);
        userService.saveUser ("Pu", "Kus", (byte) 2);

        List<User> users = userService.getAllUsers();
        System.out.println("All users");

        if (users != null) {
        for (User user : users)
            System.out.println(user);
        } else {
            System.out.println("Users table is empty");
        }
        userService.cleanUsersTable();
        System.out.println("Users table has been cleaned");

        userService.dropUsersTable();
        System.out.println("Users table has been cleaned");
        // реализуйте алгоритм здесь
    }
}
