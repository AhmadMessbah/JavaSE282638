package mft.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "javase",
                "java123"
        );
    }
}
