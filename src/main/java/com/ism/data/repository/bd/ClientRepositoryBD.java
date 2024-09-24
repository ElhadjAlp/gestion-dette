package com.ism.data.repository.bd;

import java.util.List;
import java.util.ArrayList;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Client;
import com.ism.data.entites.User;
import com.ism.data.repository.ClientRepository;
import com.ism.data.repository.UserRepository;

import java.sql.*;


public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepository {
    UserRepository userRepository;

    public ClientRepositoryBD(UserRepository userRepository) {
        this.tableName = "client";
        this.userRepository = userRepository;
    }

    @Override
    public Client selectByTelephone(String telephone) {
        Client client = null;

        try {
            String sql = String.format("select * from %s where telephone like ?", this.tableName);
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

            String sql = String.format("INSERT INTO  %s (`surname`, `telephone`, `adresse`,user_id) VALUES (?,?,?,?)",
                    this.tableName);
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
            String sql = String.format("select * from %s", this.tableName);
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

            User user = this.userRepository.selectByID(userId);
            client.setUser(user);

        return client;

    }
}
