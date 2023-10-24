package com.vinisantosdev.apireports.apireports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReturnData2 {

    @Id
    private Long id;

    @Column
    private String titular;

    @Column
    private String conta;
}

