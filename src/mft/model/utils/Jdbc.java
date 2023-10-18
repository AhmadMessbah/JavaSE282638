package mft.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jdbc {
    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "javase",
                "java123"
        );
    }

    public int nextId(String sequenceName) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT "+ sequenceName+".NEXTVAL AS NEXT_ID FROM DUAL");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }
}
