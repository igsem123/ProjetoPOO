package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public ConnectionFactory() {
    }

    public Connection getConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", "root");
            props.setProperty("password", "root");
            props.setProperty("useSSL", "false");
            props.setProperty("useTimezone", "true");
            props.setProperty("serverTimezone", "America/Sao_Paulo");
            props.setProperty("allowPublicKeyRetrieval", "true");
            String connectionString = "jdbc:mysql://localhost:3306/db_casamentos";
            return java.sql.DriverManager.getConnection(connectionString, props);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
