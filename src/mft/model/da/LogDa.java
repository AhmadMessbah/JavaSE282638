package mft.model.da;


import mft.model.entity.Log;
import mft.model.entity.User;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class LogDa {
    private Connection connection;
    private PreparedStatement statement;

    public LogDa() throws Exception {
        connection = Jdbc.getConnection();
    }


    public Log save(Log log) throws Exception {
        log.setId(Jdbc.nextId("LOG_SEQ"));
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement(
                "INSERT INTO LOG_TBL (ID,ACTION,DATA,USER_ID,LOG_TIMESTAMP)VALUES (?,?,?,?,?)");
        statement.setInt(1, log.getId());
        statement.setString(2, log.getAction());
        statement.setString(3, log.getData());
        statement.setInt(4, log.getUser().getId());
        statement.setTimestamp(5, Timestamp.valueOf(log.getLogTimeStamp()));
        return log;
    }

    public List<Log> findAll() throws Exception {
        connection = Jdbc.getConnection();
//        statement = connection.prepareStatement("")
        return null;
    }

    // todo : find by user id/ find by action / find by datetime range

    public Log findByID(long id) throws Exception {
        connection = Jdbc.getConnection();
//        statement = connection.prepareStatement("")
        return null;
    }

    public void close() throws Exception {
        statement.close();
        connection.close();
    }
}
