package com.ism.data.repository.bd;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Article; 
import com.ism.data.entites.Detail;
import com.ism.data.entites.Dette; // Assurez-vous que l'entité Dette est importée
import com.ism.data.repository.DetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailRepositoryBD extends RepositoryBDImpl<Detail> implements DetailRepository {
    private final Connection connection;

    public DetailRepositoryBD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Detail selectById(int id) {
        Detail detail = null;
        String query = "SELECT * FROM details WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detail = mapDetail(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }

    @Override
    public List<Detail> selectAll() {
        List<Detail> details = new ArrayList<>();
        String query = "SELECT * FROM details";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                details.add(mapDetail(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public void insert(Detail detail) {
        String query = "INSERT INTO details (prixVente, qteVendu, article_id, dette_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, detail.getPrixVente());
            ps.setInt(2, detail.getQteVendu());
            ps.setInt(3, detail.getArticle().getId()); 
            ps.setInt(4, detail.getDette().getId()); 
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM details WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Detail mapDetail(ResultSet rs) throws SQLException {
        Detail detail = new Detail();
        detail.setId(rs.getInt("id"));
        detail.setPrixVente(rs.getDouble("prixVente"));
        detail.setQteVendu(rs.getInt("qteVendu"));

        int articleId = rs.getInt("article_id");
        Article article = new Article();
        article.setId(articleId); 
        detail.setArticle(article);

        int detteId = rs.getInt("dette_id");
        Dette dette = new Dette(null, detteId, detteId, null, null); 
        dette.setId(detteId);
        detail.setDette(dette);

        return detail;
    }

    @Override
    public Detail convertToObject(ResultSet rs) throws SQLException {
        return mapDetail(rs);  
    }
}
