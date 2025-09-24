package br.com.shoppingcart.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import  java.util.Objects;

public class Cart {
    private final List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "product must not be null");
        items.add(product);
    }

    public boolean removeProduct(Product product) {
        Objects.requireNonNull(product, "product must not be null");
        return items.remove(product);
    }

    public int getProductCount() {
        return items.size();
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product p : items) {
            total = total.add(p.getPrice());
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    public  List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotalWith(DiscountService discountService) {
        BigDecimal subtotal = getTotal();
        BigDecimal discount = discountService.getDiscountFor(subtotal);
        if(discount == null){
            discount = BigDecimal.ZERO;
        }

        BigDecimal total = subtotal.subtract(discount);
        return total.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : total;
    }
}
