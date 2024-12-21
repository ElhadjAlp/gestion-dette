package com.ism.data.repository.bd;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Article;
import com.ism.data.entites.Detail;
import com.ism.data.entites.Dette;
import com.ism.data.repository.DetailRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailRepositoryBD extends RepositoryBDImpl<Detail> implements DetailRepository {

    public DetailRepositoryBD() {
        this.tableName = "details";
    }

    @Override
    public Detail selectById(int id) {
        Detail detail = null;

        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);

            ResultSet rs = this.executeQuery();
            if (rs.next()) {
                detail = this.mapDetail(rs); 
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
        return detail;
    }

    @Override
    public List<Detail> selectAll() {
        List<Detail> details = new ArrayList<>();

        try {
            String sql = String.format("SELECT * FROM %s", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);

            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                details.add(this.mapDetail(rs)); 
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
        return details;
    }

    @Override
    public void insert(Detail detail) {
        try {
            String sql = String.format("INSERT INTO %s (prixVente, qteVendu, article_id, dette_id) VALUES (?, ?, ?, ?)", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setDouble(1, detail.getPrixVente());
            this.ps.setInt(2, detail.getQteVendu());
            this.ps.setInt(3, detail.getArticle().getId());
            this.ps.setInt(4, detail.getDette().getId());
            this.executeUpdate();

            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                detail.setId(rs.getInt(1));
            }
            rs.close();
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
    public void delete(int id) {
        try {
            String sql = String.format("DELETE FROM %s WHERE id = ?", this.tableName);
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
        Dette dette = new Dette();
        dette.setId(detteId);
        detail.setDette(dette);

        return detail;
    }

    @Override
    public Detail convertToObject(ResultSet rs) throws SQLException {
        return mapDetail(rs); 
    }
}
