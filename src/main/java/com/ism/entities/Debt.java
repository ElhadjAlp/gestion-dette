package com.ism.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Debt {
    private static int counter;
    private int id;
    private String date;
    private int amount;
    private int amountSent;
    private int amountMissing;

    private Client client;

    private final List<Payment> paymentList = new ArrayList<>();
    private final List<Detail> detailList = new ArrayList<>();

    public void addPayment(Payment payment) {
        paymentList.add(payment);
    }

    public void addDetail(Detail detail) {
        detailList.add(detail);
    }

    public Debt() {
        id = ++counter;
    }
}