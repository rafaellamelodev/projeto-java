package dao;

import connections.DatabaseConnection;
import model.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    // Método para salvar uma nova disciplina
    public boolean salvar(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplinas (codigo, nome, carga_horaria) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, disciplina.getCodigo());
            ps.setString(2, disciplina.getNome());
            ps.setInt(3, disciplina.getCargaHoraria());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        disciplina.setId(rs.getInt(1)); // Obtém o ID gerado
                    }
                }
                return true;
            }
            return false;
        }
    }

    // Método para atualizar uma disciplina existente
    public boolean atualizar(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplinas SET codigo=?, nome=?, carga_horaria=? WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, disciplina.getCodigo());
            ps.setString(2, disciplina.getNome());
            ps.setInt(3, disciplina.getCargaHoraria());
            ps.setInt(4, disciplina.getId()); // Atualiza pelo ID
            return ps.executeUpdate() > 0; // Retorna true se a atualização foi bem-sucedida
        }
    }

    // Método para excluir uma disciplina
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM disciplinas WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id); // Exclui pelo ID
            return ps.executeUpdate() > 0; // Retorna true se a exclusão foi bem-sucedida
        }
    }

    // Método para listar todas as disciplinas
    public List<Disciplina> listarTodos() throws SQLException {
        String sql = "SELECT * FROM disciplinas";
        List<Disciplina> disciplinas = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }
}
