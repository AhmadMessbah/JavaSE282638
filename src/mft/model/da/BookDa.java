package mft.model.da;

import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDa {
    private Connection connection;
    private PreparedStatement statement;
    private Jdbc jdbc = new Jdbc();

    public BookDa() throws Exception {
        connection = jdbc.getConnection();
    }

//    TODO : (save, edit, remove, findAll, findById)

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
