package mft.model.da;

import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogDa {
    private Connection connection;
    private PreparedStatement statement;

    public LogDa() throws Exception {
        connection = Jdbc.getConnection();
    }

//    TODO : (save, edit, remove, findAll, findById)

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
