package mft.model.da;

import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDa {
    private Connection connection;
    private PreparedStatement statement;
    private Jdbc jdbc = new Jdbc();

    public UserDa() throws Exception {
        connection = jdbc.getConnection();
    }

//    TODO : (save, edit, remove, findAll, findById)

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
