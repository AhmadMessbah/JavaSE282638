package mft.model.da;

import mft.model.entity.Member;
import mft.model.entity.User;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDa {
    private Connection connection;
    private PreparedStatement statement;

    public UserDa() throws Exception {
        connection = Jdbc.getConnection();
    }

    public User save(User user) throws Exception {
        statement = connection.prepareStatement(
                "INSERT INTO USER_TBL(id, member_id, username, password, status) VALUES (?,?,?,?)"
        );
        statement.setInt(1, user.getId());
        statement.setInt(2, user.getMember().getId());
        statement.setString(3, user.getUserName());
        statement.setString(4, user.getPassword());
        statement.setString(4, String.valueOf(user.isStatus()));
        statement.execute();
        statement.close();
        return user;
    }

    public User edit(User user) throws Exception {
        statement = connection.prepareStatement(
                "UPDATE USER_TBL SET ID=?, MEMBER_ID=?, USERNAME=?, PASSWORD=?, STATUS=? "
        );
        statement.setInt(1, user.getId());
        statement.setInt(2, user.getMember().getId());
        statement.setString(3, user.getUserName());
        statement.setString(4, user.getPassword());
        statement.setString(5, String.valueOf(user.isStatus()));
        statement.execute();
        statement.close();
        return user;
    }

    public void remove(int id) throws Exception {
        statement = connection.prepareStatement(
                "DELETE FROM USER_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        statement.close();
//        return User;
    }

    public List<User> findAll() throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL ORDER BY USERNAME"
        );
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getInt("member_id"))
                    .name(resultSet.getString("member_name"))
                    .family(resultSet.getString("member_family"))
                    .build();

            User user = User.builder()
                    .id(resultSet.getInt("USER_ID"))
                    .userName(resultSet.getString("USER_USERNAME"))
                    .password(resultSet.getString("USER_PASSWORD"))
                    .status(resultSet.getBoolean("USER_STATUS"))
                    .build();
        }

        close();
        return userList;

    }

    public User findById(int id) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        User user = User.builder()
                .id(resultSet.getInt("USER_ID"))
                .build();
        close();
        return user;

    }


    public String findByUserName(String userName) throws Exception {
        statement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=?"
        );
        statement.setString(1, userName);
        statement.execute();
        statement.close();
        return userName;
    }

//    public User findByUserNameAndPassword(String username, String paswword) throws Exception {
//        statement = connection.prepareStatement(
//                "SELECT * FROM USER_TBL WHERE USERNAME=? "
//        );
//        close();
//    }


    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
