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
        
        public List<String[]> listarNotas() {
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
