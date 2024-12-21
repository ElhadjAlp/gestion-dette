package com.ism.data.repository;

import com.ism.data.entites.Payment;
import java.util.List;
public interface PaymentRepository {
      void insert(Payment payment);
    void update(Payment payment);
    void delete(int id);
    Payment selectById(int id);
    List<Payment> selectAll();
    List<Payment> findDetteById(int detteId);
}
