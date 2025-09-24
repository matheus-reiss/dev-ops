package br.com.shoppingcart.core;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal getDiscountFor(BigDecimal subtotal);
}
