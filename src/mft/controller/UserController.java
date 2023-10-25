package mft.controller;

import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.bl.UserBl;
import mft.model.entity.Member;
import mft.model.entity.User;

import java.util.regex.Pattern;

public class UserController {
    public static String save(int memberId, String userName, String password) {
        String message;
        try {
            if (Pattern.matches("[a-zA-Z\\d\\s]{2,30}", userName) && Pattern.matches("[a-zA-Z\\d\\s]{2,30}", password)){
                User user =
                        User.builder()
                                .userName(userName)
                                .password(password)
                                .member(MemberBl.findById(memberId))
                                .status(true)
                                .build();
                UserBl.save(user);
                message = user + " Saved";
                Logger.info("Save-User",user.toString(),1);
            }else {
                message = "Invalid Data";
                Logger.info("Save-Error",message,1);
            }
        } catch (Exception e) {
            message = "Error : " + e.getMessage() ;
            Logger.info("Save-Error",e.getMessage(),1);
        }
        return message;
    }
}
