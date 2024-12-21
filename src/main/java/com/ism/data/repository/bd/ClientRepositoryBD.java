package com.ism.data.repository.bd;

import java.util.List;
import java.util.ArrayList;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Client;
import com.ism.data.entites.Dette;
import com.ism.data.entites.User;
import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.UserRepository;

import java.sql.*;

public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepository {
    private UserRepository userRepository;

    // Constructeur avec injection de dépendance
    public ClientRepositoryBD(UserRepository userRepository) {
        this.tableName = "clients";
        this.userRepository = userRepository;
    }

    // Méthode setter comme alternative pour injecter UserRepository
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Client selectByTelephone(String telephone) {
        Client client = null;

        try {
            String sql = String.format("SELECT * FROM %s WHERE telephone LIKE ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setString(1, telephone);

            ResultSet rs = this.executeQuery();
            if (rs.next()) {
                client = this.convertToObject(rs);
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
        return client;
    }

    @Override
    public Client selectBySurname(String surname) {
        return null;
    }

    @Override
    public void insert(Client data) {
        User user = data.getUser();
        try {
            if (user != null) {
                userRepository.insert(user);
            }

            String sql = String.format("INSERT INTO %s (surname, telephone, adresse, user_id) VALUES (?, ?, ?, ?)", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setString(1, data.getSurname());
            this.ps.setString(2, data.getTelephone());
            this.ps.setString(3, data.getAdresse());
            if (user != null) {
                this.ps.setInt(4, user.getId());
            } else {
                this.ps.setNull(4, Types.INTEGER);
            }
            this.executeUpdate();
            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                data.setId(rs.getInt(1));
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
    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();

        try {
            String sql = String.format("SELECT * FROM %s", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);

            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                clients.add(this.convertToObject(rs));
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
        return clients;
    }

    @Override
    public Client convertToObject(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setSurname(rs.getString("surname"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresse(rs.getString("adresse"));

        int userId = rs.getInt("user_id");
        if (this.userRepository != null) {
            User user = this.userRepository.selectByID(userId);
            client.setUser(user);
        } else {
            System.err.println("userRepository est null, impossible de récupérer l'utilisateur associé !");
        }

        return client;
    }

    @Override
    public void updateDette(Dette dette) {
        try {
            String sql = "UPDATE dettes SET montant = ?, montantVerser = ? WHERE id = ?";
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setDouble(1, dette.getMontant());
            this.ps.setDouble(2, dette.getMontantVerser());
            this.ps.setInt(3, dette.getId());
            this.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur de mise à jour : " + e.getMessage());
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteDette(int id) {
        try {
            String sql = "DELETE FROM dettes WHERE id = ?";
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);
            this.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur de suppression : " + e.getMessage());
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Client selectById(int clientId) {
        Client client = null;

        try {
            String sql = String.format("SELECT * FROM client WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, clientId);

            ResultSet rs = this.executeQuery();
            if (rs.next()) {
                client = this.convertToObject(rs);
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
        return client;
    }
}
