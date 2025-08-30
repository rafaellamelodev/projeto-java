package dao;

import connections.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAO {

    public boolean autenticar(String login, String senhaEmTexto) throws SQLException {
        String sql = "SELECT senha_hash FROM users WHERE login = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("senha_hash");
                    return BCrypt.checkpw(senhaEmTexto, hash);
                }
                return false;
            }
        }
    }

    public boolean criarUsuario(String login, String senhaEmTexto, String nome) throws SQLException {
        String sql = "INSERT INTO users (login, senha_hash, nome) VALUES (?,?,?)";
        String hash = BCrypt.hashpw(senhaEmTexto, BCrypt.gensalt(10));
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, hash);
            ps.setString(3, nome);
            return ps.executeUpdate() > 0;
        }
    }
}
