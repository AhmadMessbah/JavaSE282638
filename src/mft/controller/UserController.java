package mft.controller;

import mft.controller.exception.NoContentException;
import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.bl.UserBl;
import mft.model.da.UserDa;
import mft.model.entity.Member;
import mft.model.entity.User;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    public static String save(int memberId, String userName, String password) {
        String message;
        try {
            if (Validator.checkName(userName, 30) && Validator.checkName(password, 30)) {
                User user = User.builder().userName(userName).password(password).member(MemberBl.findById(memberId)).status(true).build();
                UserBl.save(user);
                message = user + " Saved";
                Logger.info("Save-User", user.toString(), 1);
            } else {
                message = "Invalid Data";
                Logger.info("Save-Error", message, 1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.info("Save-Error", e.getMessage(), 1);
        }
        return message;
    }

    public static User edit(String username, String password) {
        try {
            if (Validator.checkName(username, 30) && Validator.checkName(password, 30)) {
                User user = User.builder().userName(username).password(password).build();
                Logger.info("EDIT USER", user.toString(), BaseController.user.getId());
                return user;
            } else {
                Logger.error("EDIT USER", "INVALID DATA", BaseController.user.getId());
                return null;

            }
        } catch (Exception e) {
            Logger.error("Edit User", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static User remove(int id) {
        try {
            User user = User.builder().id(id).build();
            Logger.info("REMOVE USER", user.toString(), BaseController.user.getId());
            return user;
        } catch (Exception e) {
            Logger.error("REMOVE USER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static List<User> findAll() {
        try {
            List<User> userList = UserBl.findAll();
            Logger.info("FIND MEMBER", "ALL", BaseController.user.getId());
            return userList;
        } catch (Exception e) {
            Logger.error("FIND USER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static User findById(int id) {
        try {
            User user = User.builder().id(id).build();
            Logger.info("FIND USER", "USER ID", BaseController.user.getId());
            return user;

        } catch (Exception e) {
            Logger.error("FIND USER", "NOT FOUND", BaseController.user.getId());
            return null;

        }
    }

    public static User findByUserName(String username, int memberId) {
        try {
            User user = User.builder().userName(username).id(memberId).build();
            Logger.info("FIND USERNAME", "USERNAME", BaseController.user.getId());
            return user;
        } catch (Exception e) {
            Logger.error("FIND USERNAME", "USERNAME NOT FOUND", BaseController.user.getId());
            return null;
        }
    }

    public static User findByUserNameAndPassword(String username, String password) {
        try {
            User user = User.builder().userName(username).password(password).build();
            Logger.info("FIND USER BY USERNAME/PASSWORD", "USER", BaseController.user.getId());
            return user;

        } catch (Exception e) {
            Logger.error("FIND USER BY USERNAME/PASSWORD", "USER FOUND", BaseController.user.getId());
            return null;
        }
    }

        public static List<User> findByStatus(boolean status) {
            try {
                List<User> userList = UserBl.findByStatus(status);
                Logger.info("FIND USER BY STATUS", "USER FOUND", BaseController.user.getId());
                return userList;
            }catch (Exception e){
                Logger.error("FIND USER BY STATUS", "USER NOT FOUND", BaseController.user.getId());
                return null;
            }

        }
    }

    public static User login(String userName, String password) {
        try {
            User user = UserBl.findByUserNameAndPassword(userName, password);
            Logger.info("Login", user.toString(), user.getId());
            return user;
        } catch (Exception e) {
            Logger.info("Login-Error", e.getMessage(), 1);
        }
        return null;
    }
}
