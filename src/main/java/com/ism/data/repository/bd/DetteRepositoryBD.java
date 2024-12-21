package com.ism.data.repository.bd;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Dette;
import com.ism.data.repository.DetteRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetteRepositoryBD extends RepositoryBDImpl<Dette> implements DetteRepository {

    public DetteRepositoryBD() {
        this.tableName = "dettes";
    }

    @Override
    public Dette selectById(int id) {
        Dette dette = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);
            ResultSet rs = this.executeQuery();

            if (rs.next()) {
                dette = this.convertToObject(rs);
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
        return dette;
    }

    @Override
    public List<Dette> selectAll() {
        List<Dette> dettes = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM %s", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            ResultSet rs = this.executeQuery();

            while (rs.next()) {
                dettes.add(this.convertToObject(rs));
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
        return dettes;
    }

    @Override
    public Dette convertToObject(ResultSet rs) throws SQLException {
        Dette dette = new Dette(null, 0, 0, null, null);
        dette.setId(rs.getInt("id"));
        dette.setMontant(rs.getDouble("montant"));
        dette.setMontantVerser(rs.getDouble("montant_verser"));
        dette.setMontantRestant(dette.getMontant() - dette.getMontantVerser());
        return dette;
    }
    @Override
    public void insert(Dette dette) {
        try {
            String sql = String.format("INSERT INTO %s (montant, montant_verser) VALUES (?, ?)", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setDouble(1, dette.getMontant());
            this.ps.setDouble(2, dette.getMontantVerser());
    
            this.executeUpdate();
            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                dette.setId(rs.getInt(1)); 
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
public void updateDette(Dette dette) {
    try {
        String sql = String.format("UPDATE %s SET montant = ?, montant_verser = ? WHERE id = ?", this.tableName);
        this.getConnection();
        this.initPreparedStatement(sql);
        this.ps.setDouble(1, dette.getMontant());
        this.ps.setDouble(2, dette.getMontantVerser());
        this.ps.setInt(3, dette.getId());

        this.executeUpdate();
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
public void deleteDette(int id) {
    try {
        String sql = String.format("DELETE FROM %s WHERE id = ?", this.tableName);
        this.getConnection();
        this.initPreparedStatement(sql);
        this.ps.setInt(1, id);

        this.executeUpdate();
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

}

