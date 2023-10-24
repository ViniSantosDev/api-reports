package com.vinisantosdev.apireports.apireports.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tabela1")
public class ReturnData1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titular")
    private String titular;

    @Column(name = "conta")
    private String conta;
}
