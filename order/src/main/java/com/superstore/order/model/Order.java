package com.superstore.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String orderStatus;
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Order() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public Order(Long userId, String status, BigDecimal totalPrice, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderStatus = status;
        this.totalPrice = totalPrice;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.orderItems = orderItems;
    }

}
