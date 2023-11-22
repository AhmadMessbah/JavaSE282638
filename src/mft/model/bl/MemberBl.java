package mft.model.bl;

import mft.model.da.MemberDa;
import mft.model.entity.Member;

import java.util.List;

public class MemberBl {
        public static Member save(Member member) throws Exception {
            try (MemberDa memberDa = new MemberDa()) {
                if (memberDa.findByNameAndFamily(member.getName(),member.getFamily())==null){
                    return memberDa.save(member);
                }else return null;
            }
        }

        public static Member edit(Member member) throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            return memberDa.edit(member);
        }
    }

    public static Member remove(int id) throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            Member member = memberDa.findById(id);
            memberDa.remove(id);
            return member;
        }
    }

    public static List<Member> findAll() throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            return memberDa.findAll();
        }
    }

    public static Member findById(int id) throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            return memberDa.findById(id);
        }
    }
    public static Member findByNameAndFamily(String name, String family) throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            return memberDa.findByNameAndFamily(name,family);
        }
    }
}
