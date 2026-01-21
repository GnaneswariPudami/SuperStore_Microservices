package com.superstore.order.repository;

import com.superstore.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    OrderRepository extends JpaRepository<Order, Long> {
    // Custom queries if needed
}
