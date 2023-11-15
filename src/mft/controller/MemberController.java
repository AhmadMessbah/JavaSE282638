package mft.controller;

import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Member;

import java.util.List;

public class MemberController {
    public static Member save(String name, String family){
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.save(member);
                Logger.info("SAVE MEMBER", member.toString(), 0);
                return member;
            } else {
                Logger.error("SAVE MEMBER", "INVALID DATA", 0);
                return null;
            }
        } catch (Exception e) {
            Logger.error("SAVE MEMBER", e.getMessage(), 0);
            return null;
        }
    }

    public static Member edit(String name, String family) {
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).build();
                MemberBl.edit(member);
                Logger.info("EDIT MEMBER", member.toString(),0);
                return member;
            } else {
                Logger.error("EDIT MEMBER", "INVALID DATA",0);
                return null;
            }
        } catch (Exception e) {
            Logger.error("EDIT MEMBER", e.getMessage(),0);
            return null;
        }
    }

    public static Member remove(int id)  {
        try {
            Member member = MemberBl.findById(id);
            MemberBl.remove(id);
            Logger.info("REMOVE MEMBER", member.toString(),0);
            return member;
        } catch (Exception e) {
            Logger.error("REMOVE MEMBER", e.getMessage(),0);
            return null;
        }
    }

    public static List<Member> findAll()  {
        try {
            List<Member> memberList = MemberBl.findAll();
            Logger.info("FIND MEMBER", "ALL",0);
            return memberList;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(), 0);
            return null;
        }
    }

    public static Member findById(int id)  {
        try {
            Member member = MemberBl.findById(id);
            Logger.info("FIND MEMBER", "ID",0);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(),0);
            return null;
        }
    }

    public static Member findByNameAndFamily(String name, String family)  {
        try {
            Member member = MemberBl.findByNameAndFamily(name, family);
            Logger.info("FIND MEMBER By Name/Family", name + "/" + family,0);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(),0);
            return null;
        }
    }
}
