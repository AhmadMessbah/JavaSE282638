package mft.model.da;

import mft.model.entity.Member;
import mft.model.entity.User;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDa implements AutoCloseable {
    private final Connection connection;
    private PreparedStatement statement;


    public UserDa() throws Exception {
        connection = Jdbc.getConnection();
    }

    public User save(User user) throws Exception {
        user.setId(Jdbc.nextId("USER_SEQ"));
        statement = connection.prepareStatement(
                "INSERT INTO USER_TBL(ID, MEMBER_ID, USERNAME, PASSWORD,NICK_NAME,IMAGE, STATUS) VALUES (?,?,?,?,?,?,?)"
        );
        statement.setInt(1, user.getId());
        statement.setInt(2, user.getMember().getId());
        statement.setString(3, user.getUserName());
        statement.setString(4, user.getNickName());
        statement.setString(5, user.getImage());
        statement.setString(6, user.getPassword());
        statement.setBoolean(7, user.isStatus());
        statement.execute();
        return user;
    }

    public User edit(User user) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE USER_TBL SET USERNAME=?, PASSWORD=?, NICK_NAME=?, IMAGE=? WHERE ID=?"
        );
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getNickName());
        statement.setString(4, user.getImage());
        statement.setInt(5, user.getId());
        statement.execute();
        return user;
    }

    public int remove(int id) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE USER_TBL SET DELETED=1 WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        return id;
    }

    public List<User> findAll() throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE STATUS=1 ORDER BY USERNAME"
        );
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .build();

            User user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .member(member)
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .nickName(resultSet.getString("NICK_NAME"))
                    .image(resultSet.getString("IMAGE"))
                    .status(resultSet.getBoolean("STATUS"))
                    .build();
            userList.add(user);
        }
        return userList;
    }

    public User findById(int id) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("ID"))
//                    .name(resultSet.getString("NAME"))
//                    .family(resultSet.getString("FAMILY"))
                    .build();

            user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .member(member)
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .nickName(resultSet.getString("NICK_NAME"))
                    .image(resultSet.getString("IMAGE"))
                    .status(resultSet.getBoolean("STATUS"))
                    .build();
        }
        return user;
    }

    public User findByUserName(String userName) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=?"
        );
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .nickName(resultSet.getString("NICK_NAME"))
                    .image(resultSet.getString("IMAGE"))
                    .status(resultSet.getBoolean("STATUS"))
                    .build();
        }
        return user;
    }

    // Login.
    public User findByUserNameAndPassword(String username, String password) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD=?"
        );
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .build();

            user = User.builder()
                    .id(resultSet.getInt("MEMBER_ID"))
                    .member(member)
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .nickName(resultSet.getString("NICK_NAME"))
                    .image(resultSet.getString("IMAGE"))
                    .status(resultSet.getBoolean("STATUS"))
                    .build();
        }
        return user;
    }

    public List<User> findByStatus(boolean status) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE STATUS=?"
        );
        statement.setBoolean(1, status);
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        if (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .build();

            User user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .member(member)
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .nickName(resultSet.getString("NICK_NAME"))
                    .image(resultSet.getString("IMAGE"))
                    .status(resultSet.getBoolean("STATUS"))
                    .build();
            userList.add(user);
        }
        return userList;
    }

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
