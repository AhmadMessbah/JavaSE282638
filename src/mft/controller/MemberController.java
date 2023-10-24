package mft.controller;

import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Member;

import java.util.List;
import java.util.regex.Pattern;

public class MemberController {
    public static Member save(String name, String family) throws Exception {
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", family)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.save(member);
                Logger.info("SAVE MEMBER", member.toString(), 1);
                return member;
            } else {
                Logger.error("SAVE MEMBER", "INVALID DATA", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("SAVE MEMBER", e.getMessage(), 1);
            return null;
        }
    }

    public static Member edit(String name, String family) throws Exception {
        try {
            if (Pattern.matches("[\\w]{2,30}", name) && Pattern.matches("[\\w]{2,30}", family)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.edit(member);
                Logger.info("EDIT MEMBER", member.toString(), 1);
                return member;
            } else {
                Logger.error("EDIT MEMBER", "INVALID DATA", 1);
                return null;
            }
        } catch (Exception e) {
            Logger.error("EDIT MEMBER", e.getMessage(), 1);
            return null;
        }
    }
    public static Member remove(int id) throws Exception {
        try {
            Member member = MemberBl.findById(id);
            MemberBl.remove(id);
            Logger.info("REMOVE MEMBER", member.toString(), 1);
            return member;
        } catch (Exception e) {
            Logger.error("REMOVE MEMBER", e.getMessage(), 1);
            return null;
        }
    }

    public static List<Member> findAll() throws Exception {
        try {
            List<Member> memberList = MemberBl.findAll();
            Logger.info("FIND MEMBER", "ALL", 1);
            return memberList;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), 1);
            return null;
        }
    }

    public static Member findById(int id) throws Exception {
        try {
            Member member =MemberBl.findById(id);
            Logger.info("FIND MEMBER", "ID", 1);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), 1);
            return null;
        }
    }
    public static Member findByNameAndFamily(String name, String family) throws Exception {
        try {
            Member member =MemberBl.findByNameAndFamily(name, family);
            Logger.info("FIND MEMBER", "NAME", 1);
            Logger.info("FIND MEMBER", "FAMILY", 2);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), 1);
            return null;
        }
    }
}
