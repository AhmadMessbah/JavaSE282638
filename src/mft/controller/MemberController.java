package mft.controller;

import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Member;

import java.util.List;
import java.util.regex.Pattern;

public class MemberController {
    public static Member save(String name, String family) throws Exception {
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.save(member);
                Logger.info("SAVE MEMBER", member.toString(), BaseController.user.getId());
                return member;
            } else {
                Logger.error("SAVE MEMBER", "INVALID DATA", BaseController.user.getId());
                return null;
            }
        } catch (Exception e) {
            Logger.error("SAVE MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static Member edit(String name, String family) throws Exception {
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.edit(member);
                Logger.info("EDIT MEMBER", member.toString(), BaseController.user.getId());
                return member;
            } else {
                Logger.error("EDIT MEMBER", "INVALID DATA", BaseController.user.getId());
                return null;
            }
        } catch (Exception e) {
            Logger.error("EDIT MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static Member remove(int id) throws Exception {
        try {
            Member member = MemberBl.findById(id);
            MemberBl.remove(id);
            Logger.info("REMOVE MEMBER", member.toString(), BaseController.user.getId());
            return member;
        } catch (Exception e) {
            Logger.error("REMOVE MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static List<Member> findAll() throws Exception {
        try {
            List<Member> memberList = MemberBl.findAll();
            Logger.info("FIND MEMBER", "ALL", BaseController.user.getId());
            return memberList;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static Member findById(int id) throws Exception {
        try {
            Member member = MemberBl.findById(id);
            Logger.info("FIND MEMBER", "ID", BaseController.user.getId());
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }

    public static Member findByNameAndFamily(String name, String family) throws Exception {
        try {
            Member member = MemberBl.findByNameAndFamily(name, family);
            Logger.info("FIND MEMBER By Name/Family", name + "/" + family, BaseController.user.getId());
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), BaseController.user.getId());
            return null;
        }
    }
}
