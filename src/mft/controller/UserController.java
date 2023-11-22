package mft.controller;


import mft.controller.exception.AccessDeniedException;
import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.bl.UserBl;
import mft.model.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserController {

    public static Map<String, String> save(int memberId, String userName, String password, String nickname, String image) {
        String message;
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(userName, 30) && Validator.checkName(password, 30)) {
                User user = User.builder()
                        .userName(userName)
                        .password(password)
                        .nickName(nickname)
                        .member(MemberBl.findById(memberId))
                        .status(true)
                        .image(image)
                        .build();
                UserBl.save(user);
                message = user + " Saved";
                Logger.info("Save-User", user.toString(), 1);
                result.put("status", "true");
                result.put("message", user.toString() + "saved");

            } else {
                message = "Invalid Data";
                Logger.error("Save-Error", message, 1);
                result.put("status", "false");
                result.put("message", "invalid data");
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage();
            Logger.error("Save-Error", e.getMessage(), 1);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static Map<String, String> edit(int id, String username, String password, String nickname, String image) {
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(username, 30) && Validator.checkName(password, 30)) {
                User user = UserBl.edit(User.builder().id(id).userName(username).password(password).build());
                Logger.info("EDIT USER", user.toString(), 0);
                result.put("status", "true");
                result.put("message", user.toString() + "edited");
            } else {
                System.out.println("Invalid");
                Logger.error("EDIT USER", "INVALID DATA", 0);
                result.put("status", "false");
                result.put("message", "invalid data");

            }
        } catch (Exception e) {
            Logger.error("Edit User", e.getMessage(), 0);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static Map<String, String> remove(int id) {
        Map<String, String> result = new HashMap<>();
        try {
            User user = UserBl.remove(id);
            Logger.info("REMOVE USER", user.toString(), 0);
            result.put("status", "removed");
            result.put("message", user.toString() + "removed");
        } catch (Exception e) {
            Logger.error("REMOVE USER", e.getMessage(), 0);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static List<User> findAll() {
        try {
            List<User> userList = UserBl.findAll();
            Logger.info("FIND MEMBER", "ALL", 0);
            return userList;
        } catch (Exception e) {
            Logger.error("FIND USER", e.getMessage(), 0);
            return null;
        }
    }

    public static User findById(int id) {
        try {
            User user = User.builder().id(id).build();
            Logger.info("FIND USER", "USER ID", 0);
            return user;

        } catch (Exception e) {
            Logger.error("FIND USER", "NOT FOUND", 0);
            return null;

        }
    }

    public static User findByUserName(String username, int memberId) {
        try {
            User user = UserBl.findByUserName(username, memberId);
            Logger.info("FIND USERNAME", "USERNAME", 0);
            return user;
        } catch (Exception e) {
            Logger.error("FIND USERNAME", "USERNAME NOT FOUND", 0);
            return null;
        }
    }

    public static User findByUserNameAndPassword(String username, String password) {
        try {
            User user = UserBl.findByUserNameAndPassword(username, password);
            Logger.info("FIND USER BY USERNAME/PASSWORD", "USER", 0);
            return user;

        } catch (Exception e) {
            Logger.error("FIND USER BY USERNAME/PASSWORD", "USER FOUND", 0);
            return null;
        }
    }

    public static List<User> findByStatus(boolean status) {
        try {
            List<User> userList = UserBl.findByStatus(status);
            Logger.info("FIND BY STATUS", String.valueOf(status), 0);
            return userList;
        } catch (Exception e) {
            Logger.error("FIND BY STATUS - ERROR", String.valueOf(status), 0);
            return null;
        }

    }


    public static User login(String userName, String password) {
        try {
            User user = UserBl.findByUserNameAndPassword(userName, password);
            if (user != null) {
                Logger.info("Login", userName, user.getId());
                return user;
            } else throw new AccessDeniedException();
        } catch (Exception e) {
            Logger.info("Login-Error", userName + "/" + password, 1);
            return null;
        }
    }
}
