package mft.model.bl;

import mft.model.da.UserDa;
import mft.model.entity.User;

import java.util.List;

public class UserBl {
    public static User save(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.save(user);
        }
    }


    public static User edit(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.edit(user);

        }
    }

    public static User remove(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findById(id);
            userDa.remove(id);
            return user;

        }
    }

    public static List<User> findAll() throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findAll();
        }
    }

    public static User findById(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findById(id);
        }
    }

//    public static User findByUserName(String username) throws Exception {
//        try (UserDa userDa = new UserDa()) {
//            return userDa.findByUserName(username);
//        }
//    }

    public static User findByUserNameAndPassword(String username, String password) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findByUserNameAndPassword(username, password);
        }
    }

    public static User findByStatus(boolean status) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findByStatus(status);
        }

    }
}
