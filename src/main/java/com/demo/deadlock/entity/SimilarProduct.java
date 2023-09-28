package com.demo.deadlock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "similar_product", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id", "similar_product_id" }) })
//@Table(name = "similar_product")
public class SimilarProduct {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "similar_product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product similarProduct;
}
