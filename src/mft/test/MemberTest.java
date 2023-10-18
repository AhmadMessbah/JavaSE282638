package mft.test;

import mft.model.da.MemberDa;
import mft.model.entity.Member;

public class MemberTest {
    public static void main(String[] args) throws Exception {
        Member member = Member.builder().id(2).name("ahmad").family("messbah").build();
        MemberDa memberDa = new MemberDa();
//        System.out.println(memberDa.save(member));
//        System.out.println(memberDa.findAll());
    }
}
