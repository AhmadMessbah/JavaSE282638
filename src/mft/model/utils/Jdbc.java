package mft.model.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jdbc {
    private static BasicDataSource basicDataSource = new BasicDataSource();
    public static Connection  getConnection() throws Exception {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("javase");
        basicDataSource.setPassword("myjava");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxTotal(20);
        return basicDataSource.getConnection();
    }

    public static int nextId(String sequenceName) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT "+ sequenceName+".NEXTVAL AS NEXT_ID FROM DUAL");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }
}
