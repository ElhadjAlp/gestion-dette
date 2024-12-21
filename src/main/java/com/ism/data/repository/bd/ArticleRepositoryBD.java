package com.ism.data.repository.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Article;
import com.ism.data.repository.ArticleRepository;

public class ArticleRepositoryBD extends RepositoryBDImpl<Article> implements ArticleRepository {

    public ArticleRepositoryBD() {
        this.tableName = "articles";
    }

    @Override
    public void insert(Article article) {
        try {
            String sql = String.format("INSERT INTO %s (libelle, reference, qteStock, prix) VALUES (?,?,?,?)", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setString(1, article.getLibelle());
            this.ps.setString(2, article.getReference());
            this.ps.setInt(3, article.getQteStock());
            this.ps.setDouble(4, article.getPrix());

            this.executeUpdate();
            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                article.setId(rs.getInt(1));
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
    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();

        try {
            String sql = String.format("SELECT * FROM %s", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);

            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                articles.add(this.convertToObject(rs));
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
        return articles;
    }

    @Override
    public Article selectById(int id) {
        Article article = null;

        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);

            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                article = this.convertToObject(rs);
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
        return article;
    }

    @Override
    public Article convertToObject(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setLibelle(rs.getString("libelle"));
        article.setReference(rs.getString("reference"));
        article.setQteStock(rs.getInt("qteStock"));
        article.setPrix(rs.getDouble("prix"));
        return article;
    }

    @Override
public Article findByArticle(Article article) {
    Article foundArticle = null;
    try {
        String sql = String.format("SELECT * FROM %s WHERE libelle = ? AND reference = ?", this.tableName);
        this.getConnection();
        this.initPreparedStatement(sql);
        this.ps.setString(1, article.getLibelle());
        this.ps.setString(2, article.getReference());

        ResultSet rs = this.ps.executeQuery();
        if (rs.next()) {
            foundArticle = this.convertToObject(rs);
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
    return foundArticle;
}

}

