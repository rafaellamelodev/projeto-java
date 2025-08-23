/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import connections.*;

/**
 *
 * @author D18_11
 */
public class UserOperations {
    
         public boolean loginUser (String email, String senha){
          String query = "SELECT * FROM users WHERE email = ?";
          try (Connection conexao = DatabaseConnection.getConnection();
               PreparedStatement login = conexao.prepareStatement(query)) {
            login.setString(1, email);
            
            
            ResultSet consulta = login.executeQuery();
            
            if (consulta.next()){
                String senhaArm = consulta.getString("senha");
                
                if(senha.equals(senhaArm)){
                    return true;
                }
            } return false;
              
          } catch (SQLException e) {
             e.printStackTrace(); 
            return false; 
        }
       
     
      }
    
}
