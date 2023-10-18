package mft.test;

import mft.model.da.MemberDa;
import mft.model.entity.Member;

public class MemberTest {
    public static void main(String[] args) throws Exception {
//        Connection connection = Jdbc.getConnection();
//        Member member = Member.builder().name("ahmad").family("messbah").build();
//
//        member.setId(Jdbc.nextId("MEMBER_SEQ"));
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO MEMBER_TBL(ID,NAME,FAMILY) VALUES (?,?,?)"
//        );
//        preparedStatement.setInt(1, member.getId());
//        preparedStatement.setString(2, member.getName());
//        preparedStatement.setString(3, member.getFamily());
//        preparedStatement.execute();
//        preparedStatement.close();
//        connection.close();


        Member member = Member.builder().id(3).name("ahad").family("ahadvand").build();
        MemberDa memberDa = new MemberDa();
        //System.out.println(memberDa.save(member));
        //memberDa.remove(1);
//        System.out.println(memberDa.findByNameAndFamily("ahmad","messbah"));
        System.out.println(memberDa.edit(member));
//        System.out.println(memberDa.findAll());
//        System.out.println(memberDa.findById(4));
    }
}
