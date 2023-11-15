package mft.model.da;


import mft.model.entity.Book;
import mft.model.entity.Log;
import mft.model.entity.User;
import mft.model.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogDa implements AutoCloseable{
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
        statement.executeQuery();
        return log;
    }

    public List<Log> findAll() throws Exception {
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement("SELECT * FROM LOG_TBL");
        ResultSet resultSet = statement.executeQuery();
        List<Log> logList = new ArrayList<>();
        while (resultSet.next()) {
            Log log = Log.builder()
                    .id(resultSet.getInt("ID"))
                    .action(resultSet.getString("ACTION"))
                    .data(resultSet.getString("DATA"))
                    .logTimeStamp(resultSet.getTimestamp("LOG_TIMESTAMP").toLocalDateTime())
                    .build();
            logList.add(log);
        }
        return logList;
    }

    // todo : find by user id/ find by action / find by datetime range

    public Log findByID(long id) throws Exception {
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement("SELECT * FROM LOG_TBL WHERE ID=?"
        );
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        Log log=Log.builder()
                .id(resultSet.getInt("ID")).
                build();

        return null;
    }

    public Log findByUserId(long userId) throws Exception {
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement("SELECT * FROM LOG_TBL WHERE USER_ID=?"
        );
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
        Log log=Log.builder()
                .id(resultSet.getInt("User_ID")).
                build();
        return null;
    }
    public Log findByAction(String action) throws Exception {
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement("SELECT * FROM LOG_TBL WHERE ACTION=?"
        );
        ResultSet resultSet= statement.executeQuery();
        Log log=Log.builder()
                .action(resultSet.getString("ACTION"))
                .build();
        return null;
    }
    public Log findByDateTimeRange(String dateTimeRange) throws Exception {
        connection = Jdbc.getConnection();
        statement = connection.prepareStatement("SELECT * FROM LOG_TBL WHERE DATA=?"
        );
        ResultSet resultSet= statement.executeQuery();
        Log log=Log.builder()
                .logTimeStamp(resultSet.getTimestamp("DATA").toLocalDateTime())
                .build();
        return null;
    }
    @Override
    public void close() throws Exception {
        statement.close();
        connection.close();
    }

}
