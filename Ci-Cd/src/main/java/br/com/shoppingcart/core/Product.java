package br.com.shoppingcart.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private final String id;
    private final String name;
    private final BigDecimal price;

    public Product(String id, String name, BigDecimal price){
        if (id == null || id.isBlank()){
        throw new IllegalArgumentException("id must not be blank");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name must not be blank");
        }
        if(price == null){
            throw new IllegalArgumentException("price must not be null");
        }
        if (price.compareTo(BigDecimal.ZERO)< 0){
            throw new IllegalArgumentException("price must be >= 0");
        }

        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ". price=" + price +
                '}';
    }
}
