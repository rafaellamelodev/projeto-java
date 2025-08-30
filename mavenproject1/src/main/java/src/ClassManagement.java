/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import connections.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author D18_11
 */
public class ClassManagement {

  

        public List<String> listarUsuarios() throws SQLException {

            List<String> listaUsuarios = new ArrayList<>();
            String query = "SELECT codigo FROM turmas";

            try (Connection conexao = DatabaseConnection.getConnection(); 
                    PreparedStatement objeto = conexao.prepareStatement(query); 
                    ResultSet resultado = objeto.executeQuery()) {

                while (resultado.next()) {

                    String codigo = resultado.getString("codigo");

                    listaUsuarios.add(codigo);
                }

                for (String nome : listaUsuarios) {
                    System.out.println("codigo: " + nome);
                }
                System.out.println("turmas carregadass: " + listaUsuarios.size());
            }
            return listaUsuarios;
        }
        
        public List<String[]> listarAlunos(String turma) {
        List<String[]> alunos = new ArrayList<>();

        String query = "SELECT nome, nota_final FROM alunos WHERE turma = ?"; 

        try (Connection conexao = DatabaseConnection.getConnection(); PreparedStatement consulta = conexao.prepareStatement(query)) {

            ResultSet rs = consulta.executeQuery();  
            consulta.setString(1, turma);
         
    
            while (rs.next()) {
              
                String nome = rs.getString("nome");
              
                String nota_final = rs.getString("nota_final");

                alunos.add(new String[]{nome, nota_final});
               
                              
               
            }
            
              for (String[] aluno : alunos) {
                    System.out.println("nome: " + aluno);
                }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;

    }

    }
