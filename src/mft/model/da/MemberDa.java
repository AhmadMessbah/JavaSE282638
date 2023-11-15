package mft.model.da;

import mft.model.entity.Member;
import mft.model.utils.Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDa implements AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public MemberDa() throws Exception {
        connection = Jdbc.getConnection();
    }

    public Member save(Member member) throws Exception {
        member.setId(Jdbc.nextId("MEMBER_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO MEMBER_TBL(ID,NAME,FAMILY) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, member.getId());
        preparedStatement.setString(2, member.getName());
        preparedStatement.setString(3, member.getFamily());
        preparedStatement.execute();
        return member;
    }
    public Member edit(Member member) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE MEMBER_TBL SET NAME=?, FAMILY=? WHERE ID=?"
        );
        preparedStatement.setString(1, member.getName());
        preparedStatement.setString(2, member.getFamily());
        preparedStatement.setInt(3, member.getId());
        preparedStatement.execute();
        return member;
    }

    public void remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM MEMBER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Member> findAll() throws Exception {
        preparedStatement = connection.prepareStatement(
                "select * from MEMBER_TBL ORDER BY FAMILY"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Member> memberList = new ArrayList<>();
        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .build();
            memberList.add(member);
        }
        return memberList;
    }

    public Member findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM MEMBER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = null;
        while (resultSet.next()) {
            member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .build();
        }
        return member;
    }

    public Member findByNameAndFamily(String name,String family) throws Exception {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM MEMBER_TBL WHERE NAME LIKE ? AND FAMILY LIKE ?"
        );
        preparedStatement.setString(1, name);
        preparedStatement.setString(2,family);
        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = null;
        while (resultSet.next()) {
            member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .build();
        }
        return member;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
