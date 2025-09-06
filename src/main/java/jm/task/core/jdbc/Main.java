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

        userService.saveUser ("Punya", "Kushat", (byte) 5);
        userService.saveUser ("Puny", "Kusha", (byte) 4);
        userService.saveUser ("Pun", "Kush", (byte) 3);
        userService.saveUser ("Pu", "Kus", (byte) 2);

        List<User> users = userService.getAllUsers();
        logger.info("All users");

        if (users != null) {
        for (User user : users)
            logger.info(user.toString());
        } else {
            logger.info("Users table is empty");
        }
        userService.cleanUsersTable();
        logger.info("Users table has been cleaned");

        userService.dropUsersTable();
        logger.info("Users table has been cleaned");
        // реализуйте алгоритм здесь
    }
}
