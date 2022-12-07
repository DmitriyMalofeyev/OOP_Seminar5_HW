package personal.views;

import personal.controllers.UserController;
import personal.exception.CommandException;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() throws Exception {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        userController.saveUser(makeUser(false));
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        User user = userController.readUser(id);
                        System.out.println(user);
                        break;
                    case UPDATE:
                        userController.updateUser(makeUser(true));
                        break;
                    case LIST:
                        List<User> users = userController.readAllUsers();
                        for (User usr : users) {
                            System.out.println(usr);
                        }
                        break;
                    case DELETE:
                        String idForDelete = prompt("Идентификатор пользователя: ");
                        User userForDelete = userController.readUser(idForDelete);
                        userController.deleteUser(userForDelete);
                        break;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private User makeUser(boolean forUpdate) {
        String id = "";
        if (forUpdate) {
            id = prompt("Идентификатор пользователя: ");
        }
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        if (forUpdate) return new User(id, firstName, lastName, phone);
        return new User(firstName, lastName, phone);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}