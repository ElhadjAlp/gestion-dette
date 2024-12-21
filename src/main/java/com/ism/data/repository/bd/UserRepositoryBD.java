package com.ism.data.repository.bd;

import java.util.List;
import java.util.ArrayList;
import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.User;
import com.ism.data.enums.RoleEnum;
import com.ism.data.repository.UserRepository;
import java.sql.*;

public class UserRepositoryBD extends RepositoryBDImpl<User> implements UserRepository {

    public UserRepositoryBD() {
        this.tableName = "users"; // Assurez-vous que le nom correspond exactement à celui de votre base
    }

    @Override
    public void insert(User data) {
        try {
            String sql = "INSERT INTO users (nom, prenom, login, password, role, etat) VALUES (?, ?, ?, ?, ?, '1');";
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setString(1, data.getNom());
            this.ps.setString(2, data.getPrenom());
            this.ps.setString(3, data.getLogin());
            this.ps.setString(4, data.getPassword());
            this.ps.setString(5, data.getRole().name());
            this.ps.executeUpdate();

            // Récupération de la clé générée
            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                data.setId(rs.getInt(1)); // 1 représente la première colonne retournée
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users"; // Assurez-vous que toutes les colonnes nécessaires sont bien présentes
            this.getConnection();
            this.initPreparedStatement(sql);
            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                users.add(this.convertToObject(rs));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur de chargement : " + e.getMessage());
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public User convertToObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id")); // Vérifiez que cette colonne existe bien
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(RoleEnum.getValue(rs.getString("role")));
        user.setEtat(rs.getBoolean("etat"));
        return user;
    }

    @Override
    public User selectByID(int id) {
        User result = null;
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);
            ResultSet rs = this.executeQuery();
            if (rs.next()) {
                result = this.convertToObject(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur de chargement : " + e.getMessage());
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public User selectByLogin(String login) {
        User user = null;
        try {
            String sql = "SELECT * FROM users WHERE login = ?";
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setString(1, login);
            ResultSet rs = this.executeQuery();
            if (rs.next()) {
                user = this.convertToObject(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur de chargement : " + e.getMessage());
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}