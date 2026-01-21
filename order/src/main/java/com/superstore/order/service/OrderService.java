package com.superstore.order.service;

import com.superstore.order.model.Order;
import com.superstore.order.model.OrderItem;
import com.superstore.order.repository.OrderItemRepository;
import com.superstore.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("PRODUCT_SERVICE_URL")
    private final String productServiceUrl = null;

    @Value("USERACCOUNT_SERVICE_URL")
    private final String userServiceUrl = null;


    public Order createOrder(Order order) {

        logger.info("Validating User Id : "+order.getUserId());
        // Validate user ID
        if (!validateUserId(order.getUserId())) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        Order orderCreated = orderRepository.save(order);

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            // Validate product ID
            if (!validateProductId(item.getProductId())) {
                orderRepository.delete(order);
                throw new IllegalArgumentException("Invalid product ID");
            }
            totalPrice = totalPrice.add(item.getItemPrice());
            item.setOrder(order); // Link the item to the order
            orderItemRepository.save(item);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderItems(order.getOrderItems()); //Ensure the items are saved.
        return orderCreated;
    }

    private boolean validateUserId(Long userId) {
        try {
            logger.info("userServiceUrl : "+userServiceUrl + userId);
            restTemplate.getForObject(userServiceUrl + userId, Object.class);
            return true; // User exists
        } catch (Exception e) {
            return false; // User does not exist or service is unavailable
        }
    }

    private boolean validateProductId(Long productId) {
        try {
            logger.info("productServiceUrl : "+productServiceUrl + productId);
            restTemplate.getForObject(productServiceUrl + productId, Object.class);
            return true; // Product exists
        } catch (Exception e) {
            return false; // Product does not exist or service is unavailable
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Add other methods for updating, deleting, etc.

}
