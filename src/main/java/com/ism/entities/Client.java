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


public class Client {
    private String surname;
    private String phone;
    private String address;

    private Account account;

    private final List<Debt> debtList = new ArrayList<>();

    public void addDebt(Debt debt) {
        debtList.add(debt);
    }


}

