/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author D18_11
 */
public class DatabaseConnection {
     private static final String URL = 
            "jdbc:mysql://localhost:3306/jschool";     
    private static final String USER = "root";
    private static final String SENHA = "admin";
    private static final String teste = "teste";
    
    
    /*método para tentar conectar ao banco de dados
    em caso de falha, uma exceção é lançada*/
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, SENHA);
}
}
