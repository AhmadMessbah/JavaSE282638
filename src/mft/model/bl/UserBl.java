package mft.model.bl;

import mft.controller.exception.AccessDeniedException;
import mft.controller.exception.DuplicateUserNameException;
import mft.controller.exception.NoContentException;
import mft.model.da.UserDa;
import mft.model.entity.User;

import java.util.List;

public class UserBl {
    public static User save(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            if (userDa.findByUserName(user.getUserName()) == null) {
                return userDa.save(user);
            }
            throw new DuplicateUserNameException("Duplicate Username");
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
            if (user != null) {
                userDa.remove(id);
                return user;
            }
            throw new NoContentException("No User !");
        }
    }

    public static List<User> findAll() throws Exception {
        try (UserDa userDa = new UserDa()) {
            List<User> userList = userDa.findAll();
            if(userList.size()>0) {
                return userList;
            }
            throw new NoContentException("There is no User !");
        }
    }

    public static User findById(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findById(id);
            if (user != null) {
                return user;
            }
            throw new NoContentException("No User !");
        }
    }

    public static User findByUserName(String username, int memberId) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findByUserName(username);
            if (user != null) {
                user.setMember(MemberBl.findById(memberId));
                return user;
            }
            throw new NoContentException("No User !");
        }
    }

    public static User findByUserNameAndPassword(String username, String password) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user = userDa.findByUserNameAndPassword(username, password);
            if (user != null) {
                user.setMember(MemberBl.findById(user.getMember().getId()));
                return user;
            }
            throw new AccessDeniedException();
        }
    }

    public static List<User> findByStatus(boolean status) throws Exception {
        try (UserDa userDa = new UserDa()) {
            List<User> userList = userDa.findByStatus(status);
            if(userList.size()>0) {
                return userList;
            }
            throw new NoContentException("There is no User !");
        }
    }
}
