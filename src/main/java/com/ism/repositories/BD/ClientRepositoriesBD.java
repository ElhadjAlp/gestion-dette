package com.ism.repositories.BD;

import com.ism.entities.Client;
import com.ism.repositories.interfaces.IClientRepository;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientRepositoriesBD implements IClientRepository<Client> {

    public void insert(Client client) {
        String insertSQL = "INSERT INTO client (surname, phone, address) VALUES (?, ? ,? )";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_dette", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement.setString(1, client.getSurname());
            preparedStatement.setString(2, client.getPhone());
            preparedStatement.setString(3, client.getAddress());
            int nbre = preparedStatement.executeUpdate();
            if (nbre > 0) {
                System.out.println("Client inséré avec succès.");
            } else {
                System.out.println("Échec de l'insertion du client.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion ou de l'insertion dans la base de données : " + e.getMessage());
        }
    }

    @Override
   public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
        String selectSQL = "SELECT * FROM client";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_dette", "root", "");
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(selectSQL)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (rs.next()) {
                Client client = new Client();
                client.setSurname(rs.getString("surname"));
                client.setPhone(rs.getString("phone"));
                client.setAddress(rs.getString("address"));
                clients.add(client);
            }

            System.out.println("Connexion à la BD établie et récupération des clients réussie.");

        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion ou de l'exécution de la requête : " + e.getMessage());
        }

        return clients;
    }

    @Override
    public Client findByPhone(String phone) {
        Client client = null;
        String selectSQL = "SELECT * FROM client WHERE phone = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_dette", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(selectSQL)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement.setString(1, phone);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setSurname(rs.getString("surname"));
                client.setPhone(rs.getString("phone"));
                client.setAddress(rs.getString("address"));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion ou de l'exécution de la requête : " + e.getMessage());
        }

        return client;
    }
}
