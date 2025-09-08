package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        logger.info("Users table has been created");

        userService.saveUser("Punya", "Kushat", (byte) 5);
        System.out.println("User Punya has been saved");
        userService.saveUser("Puny", "Kusha", (byte) 4);
        System.out.println("User Puny has been saved");
        userService.saveUser("Pun", "Kush", (byte) 3);
        System.out.println("User Pun has been saved");
        userService.saveUser("Pu", "Kus", (byte) 2);
        System.out.println("User Pu has been saved");

        List<User> users = userService.getAllUsers();
        System.out.println("All users");

        if (users != null && !users.isEmpty()) {
            System.out.println("users");
            for (User user : users) {
                if (user != null) {
                    System.out.println(user.toString());

                } else {
                    System.out.println("Users table is empty");
                }
                userService.cleanUsersTable();
                logger.info("Users table has been cleaned");

                userService.dropUsersTable();
                logger.info("Users table has been cleaned");
                // реализуйте алгоритм здесь
            }
        }
    }
}

