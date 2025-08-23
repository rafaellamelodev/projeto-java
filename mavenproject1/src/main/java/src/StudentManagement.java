package src;

import connections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

        
public class StudentManagement {
      public boolean StudentManagement(String matricula, String nome, String cpf, String email) throws SQLException{
        /*cria uma variável que armazena a query que será 
        utilizada para criar um objeto na tabela users*/
        String query = "INSERT INTO alunos (matricula, nome, cpf, email)"
                + "VALUES (?,?,?,?)";        
        
        /*dentro de um try-catch, iremos:
        Connection -> classe paracriar um objeto que vai tentar se 
        conectar ao banco
        PreparedStatement -> executar as consultas no banco
        */
        try (Connection conexao = DatabaseConnection.getConnection();
             PreparedStatement objeto = conexao.prepareStatement(query)) {
            
            /*definindo valores de cada atributo na consulta*/
            objeto.setString(1, matricula);
            objeto.setString(2, nome);
            objeto.setString(3, cpf);
            objeto.setString(4, email);
            
            /*executando o cadastro do usuario e retornando
            sucesso ou falha*/            
            objeto.executeUpdate(); /*executa o comando de atualização*/
            return true;    /*retorno verdadeiro significa sucesso*/
        } catch (SQLException e){
            e.printStackTrace(); /*imprime o erro no console*/
            return false; /*retorno falso significa falha*/
        } 
      }
      
      public boolean deletarUsuario (String cpf) throws SQLException{
        
          String query = "DELETE FROM alunos WHERE cpf = ?";
          
          try (Connection conexao = src.DatabaseConnection.getConnection();
                  PreparedStatement consulta = conexao.prepareStatement(query)) {
              consulta.setString(1, cpf);
              
              
              int linhasAlteradas = consulta.executeUpdate();
              return linhasAlteradas > 0;
          }catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
          
      }      

    public boolean deletarConta(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
