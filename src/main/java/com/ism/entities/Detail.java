package com.ism.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode



public class Detail {
    private int stock;
    private Article article;
    private Debt debt;
}