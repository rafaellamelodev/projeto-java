package dao;

import connections.DatabaseConnection;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public boolean inserir(Aluno a) throws SQLException {
        String sql = "INSERT INTO alunos (matricula, nome, cpf, email) VALUES (?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getMatricula());
            ps.setString(2, a.getNome());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getEmail());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Aluno a) throws SQLException {
        String sql = "UPDATE alunos SET matricula=?, nome=?, cpf=?, email=? WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getMatricula());
            ps.setString(2, a.getNome());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getEmail());
            ps.setInt(5, a.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM alunos WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Aluno buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, matricula, nome, cpf, email FROM alunos WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
                return null;
            }
        }
    }

    public List<Aluno> listarTodos() throws SQLException {
        String sql = "SELECT id, matricula, nome, cpf, email FROM alunos ORDER BY id DESC";
        List<Aluno> lista = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        }
        return lista;
    }

    private Aluno map(ResultSet rs) throws SQLException {
        Aluno a = new Aluno();
        a.setId(rs.getInt("id"));
        a.setMatricula(rs.getString("matricula"));
        a.setNome(rs.getString("nome"));
        a.setCpf(rs.getString("cpf"));
        a.setEmail(rs.getString("email"));
        return a;
    }
}
