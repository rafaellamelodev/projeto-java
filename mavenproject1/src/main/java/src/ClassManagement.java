/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import connections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import connections.*;

import java.util.*;

/**
 *
 * @author D18_11
 */
public class ClassManagement {
    
    
   public List<String> listarDisciplinas() throws SQLException {
       
    List<String> disciplinas = new ArrayList<>();
    String query = "SELECT nome FROM disciplinas";
    
    try (Connection conexao = DatabaseConnection.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            disciplinas.add(rs.getString("nome"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return disciplinas;
}
}
