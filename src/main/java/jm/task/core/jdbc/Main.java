package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class Main {
    static UserService userService = new UserServiceImpl();
    private static User user1 = new User("Vasya", "Pupkin", (byte) 15);
    private static User user2 = new User("Petr", "Petkin", (byte) 18);
    private static User user3 = new User("Masha", "Mashkina", (byte) 21);
    private static  User user4 = new User("Don", "Karleone", (byte) 88);
    private static  User user5 = new User("Mister", "Bin", (byte) 55);
    public static void main(String[] args) {

         List<User> userList;
        userService.createUsersTable();
       userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());
      userService.removeUserById(7);
        userList= userService.getAllUsers();
        Iterator<User> iterator= userList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
