package mft.controller;

import mft.model.bl.Logger;
import mft.model.bl.MemberBl;
import mft.model.entity.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class MemberController {
//         todo : الگو
    public static Map<String, String> save(String name, String family, String father, String nationalCode, LocalDate birthDate){
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).father(father).nationalCode(nationalCode).birthDate(birthDate).build();
                MemberBl.save(member);
                Logger.info("SAVE MEMBER", member.toString(), 0);
                result.put("status", "true");
                result.put("message", member.toString()+" Saved");
            } else {
                Logger.error("SAVE MEMBER", "INVALID DATA", 0);
                result.put("status", "false");
                result.put("message","Invalid Data");
            }
        } catch (Exception e) {
            Logger.error("SAVE MEMBER", e.getMessage(), 0);
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, String> edit(int id, String name, String family, String father, String nationalCode, LocalDate birthDate) {
        Map<String, String> result = new HashMap<>();
        try {
            if (Validator.checkName(name, 30) && Validator.checkName(family, 30)) {
                Member member = Member.builder().name(name).family(family).father(father).nationalCode(nationalCode).birthDate(birthDate).build();
                MemberBl.edit(member);
                Logger.info("EDIT MEMBER", member.toString(),0);
                result.put("status", "true");
                result.put("message", member.toString()+" Saved");
            } else {
                Logger.error("EDIT MEMBER", "INVALID DATA",0);
                result.put("status", "false");
                result.put("message","Invalid Data");
            }
        } catch (Exception e) {
            Logger.error("EDIT MEMBER", e.getMessage(),0);
            result.put("status", "false");
            result.put("message",e.getMessage());
        }
        return result;
    }

    public static Map<String, String> remove(int id) {
        Map<String, String> result = new HashMap<>();
        try {
            Member member = MemberBl.findById(id);
            MemberBl.remove(id);
            Logger.info("REMOVE MEMBER", member.toString(), 1);
            result.put("status", "true");
            result.put("message", member.toString() + " Removed");
        } catch (Exception e) {
            Logger.error("REMOVE MEMBER", e.getMessage(), 1);
            result.put("status", "false");
            result.put("message", e.getMessage());
        }
        return result;
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

    public static Member findByNationalCode(String nationalCode)  {
        try {
            Member member = MemberBl.findByNationalCode(nationalCode);
            Logger.info("FIND MEMBER", "NATIONAL-CODE",0);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(),0);
            return null;
        }
    }

    public static Member findByMemberShipDate(String memberShipDate)  {
        try {
            Member member = MemberBl.findByMemberShipDate(memberShipDate);
            Logger.info("FIND MEMBER", "MEMBERSHIP_DATE-CODE",0);
            return member;
        } catch (Exception e) {
            Logger.error("FIND MEMBER", e.getMessage(),0);
            return null;
        }
    }
}