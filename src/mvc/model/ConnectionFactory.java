package mvc.model;

import java.sql.Connection;
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
            props.setProperty("serverTimezone", "UTC");
            props.setProperty("allowPublicKeyRetrieval", "true");
            String connectionString = "jdbc:mysql://localhost/";
            System.out.println("Conexão com o banco de dados estabelecida.");
            return java.sql.DriverManager.getConnection(connectionString, props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
