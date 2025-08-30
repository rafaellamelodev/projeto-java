package src;

import connections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connections.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.util.*;

public class StudentManagement {

    public boolean StudentManagement(String matricula, String nome, String cpf, String email) throws SQLException {
        /*cria uma variável que armazena a query que será 
        utilizada para criar um objeto na tabela users*/
        String query = "INSERT INTO alunos (matricula, nome, cpf, email)"
                + "VALUES (?,?,?,?)";

        /*dentro de um try-catch, iremos:
        Connection -> classe paracriar um objeto que vai tentar se 
        conectar ao banco
        PreparedStatement -> executar as consultas no banco
         */
        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement objeto = conexao.prepareStatement(query)) {

            /*definindo valores de cada atributo na consulta*/
            objeto.setString(1, matricula);
            objeto.setString(2, nome);
            objeto.setString(3, cpf);
            objeto.setString(4, email);

            /*executando o cadastro do usuario e retornando
            sucesso ou falha*/
            objeto.executeUpdate();
            /*executa o comando de atualização*/
            return true;
            /*retorno verdadeiro significa sucesso*/
        } catch (SQLException e) {
            e.printStackTrace();
            /*imprime o erro no console*/
            return false;
            /*retorno falso significa falha*/
        }
    }

    public boolean deletarUsuario(String cpf) throws SQLException {

        String query = "DELETE FROM alunos WHERE cpf = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement consulta = conexao.prepareStatement(query)) {
            consulta.setString(1, cpf);

            int linhasAlteradas = consulta.executeUpdate();
            return linhasAlteradas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deletarConta(String cpf) throws SQLException {
        return deletarUsuario(cpf);
    }

    public boolean editarUsuario(String matricula, String nome, String cpf, String email, String novaSenha) {
        String query = "UPDATE users SET matricula = ?, nome = ?, cpf = ?, email = ?, senha = ? WHERE email = ?";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement consulta = conexao.prepareStatement(query)) {

            // Hash da senha antes de salvar
            String senhaHash = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

            // Define os valores
            consulta.setString(1, matricula);
            consulta.setString(2, nome);
            consulta.setString(3, cpf);
            consulta.setString(4, email);
            consulta.setString(5, senhaHash);

            // Usa o email antigo (identificador do usuário) no WHERE
            consulta.setString(6, email);

            int linhasAlteradas = consulta.executeUpdate();
            return linhasAlteradas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarUsuario(String nome, String matricula, String cpf, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<String[]> listarAlunos() {
        List<String[]> alunos = new ArrayList<>();

        String query = "SELECT matricula, nome, cpf, email FROM alunos";

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement consulta = conexao.prepareStatement(query)) {

            ResultSet rs = consulta.executeQuery();
            // Itera sobre o ResultSet e adiciona cada aluno à lista
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");

                alunos.add(new String[]{matricula, nome, cpf, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;

    }

}
