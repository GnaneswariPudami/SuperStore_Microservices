package com.superstore.product.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private int product_id;
    private String product_name;
    private int price;
}
