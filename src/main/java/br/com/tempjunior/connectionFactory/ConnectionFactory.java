package br.com.tempjunior.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory(Connection connection){}

    public static Connection getConnection(){
        if (connection == null){
            connection = innitConnection();
        }
        return connection;
    }

    private static Connection innitConnection(){
        try {
            return
                DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_treino", "root", "joselito157");
        }catch (SQLException e ){
            throw new RuntimeException(e);
        }

    }
}
