package mft.model.da;

import mft.model.entity.Member;
import mft.model.utils.Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDa {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public MemberDa() throws Exception {
        Jdbc jdbc = new Jdbc();
        connection=jdbc.getConnection();
    }

    public void save(Member member) throws Exception {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO MEMBER_TBL(ID,NAME,FAMILY) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, member.getId());
        preparedStatement.setString(2, member.getName());
        preparedStatement.setString(3, member.getFamily());
        preparedStatement.execute();
        close();
    }

    public List<Member> findAll() throws Exception {
        preparedStatement=connection.prepareStatement(
            "select * from MEMBER_TBL"
    );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Member> memberList = new ArrayList<>();

        while (resultSet.next()){
            Member member = Member.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .build();
            memberList.add(member);
        }
        close();
        return memberList;
    }
    public Member findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "select * from MEMBER_TBL where ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = null;
        while (resultSet.next()) {
            member = Member.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .build();
        }
        close();
        return member;
    }
    public void close()throws Exception{
        preparedStatement.close();
        connection.close();
    }
}
