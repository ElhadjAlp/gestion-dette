package com.ism.data.repository.bd;

import com.ism.core.Repository.impl.RepositoryBDImpl;
import com.ism.data.entites.Payment;
import com.ism.data.repository.PaymentRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryBD extends RepositoryBDImpl<Payment> implements PaymentRepository {
    
    public PaymentRepositoryBD() {
        this.tableName = "payments"; 
    }

    @Override
    public void insert(Payment payment) {
        try {
            String sql = String.format("INSERT INTO %s (montant, date, dette_id) VALUES (?, ?, ?)", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setDouble(1, payment.getMontant());
            this.ps.setTimestamp(2, Timestamp.valueOf(payment.getDate())); 
            this.ps.setInt(3, payment.getDette().getId()); 

            this.executeUpdate();
            ResultSet rs = this.ps.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getInt(1)); 
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
    public List<Payment> selectAll() {
        List<Payment> payments = new ArrayList<>();

        try {
            String sql = String.format("SELECT * FROM %s", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);

            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                payments.add(this.convertToObject(rs));
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
        return payments;
    }

    @Override
    public Payment selectById(int id) {
        Payment payment = null;

        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, id);

            ResultSet rs = this.ps.executeQuery();
            if (rs.next()) {
                payment = this.convertToObject(rs);
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
        return payment;
    }

    @Override
    public Payment convertToObject(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setMontant(rs.getDouble("montant"));
        payment.setDate(rs.getTimestamp("date").toLocalDateTime()); 
        
        return payment;
    }

    @Override
    public List<Payment> findDetteById(int detteId) {
        List<Payment> paymentsForDette = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM %s WHERE dette_id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setInt(1, detteId);

            ResultSet rs = this.ps.executeQuery();
            while (rs.next()) {
                paymentsForDette.add(this.convertToObject(rs));
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
        return paymentsForDette;
    }

    @Override
    public void update(Payment payment) {
        try {
            String sql = String.format("UPDATE %s SET montant = ?, date = ?, dette_id = ? WHERE id = ?", this.tableName);
            this.getConnection();
            this.initPreparedStatement(sql);
            this.ps.setDouble(1, payment.getMontant());
            this.ps.setTimestamp(2, Timestamp.valueOf(payment.getDate()));
            this.ps.setInt(3, payment.getDette().getId());
            this.ps.setInt(4, payment.getId()); 

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
    public void delete(int id) {
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
