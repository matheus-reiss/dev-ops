package br.com.shoppingcart.core;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CartMockTest {

    private static void assertMoneyEquals(String expected, BigDecimal actual) {
        assertEquals(0, actual.compareTo(new BigDecimal(expected)));
    }

    @Test
    public void givenCartWithItems_whenApplyingDiscountService_thenTotalIsReduced() {

        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Mouse",   new BigDecimal("100.00")));
        cart.addProduct(new Product("2", "Teclado", new BigDecimal("150.50")));
        DiscountService discount = mock(DiscountService.class);
        when(discount.getDiscountFor(new BigDecimal("250.50")))
                .thenReturn(new BigDecimal("50.50"));


        BigDecimal total = cart.getTotalWith(discount);


        assertMoneyEquals("200.00", total);
        verify(discount, times(1)).getDiscountFor(new BigDecimal("250.50"));
        verifyNoMoreInteractions(discount);
    }

    @Test
    public void givenDiscountGreaterThanSubtotal_whenApply_thenTotalClampedToZero() {

        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Cabo", new BigDecimal("10.00")));
        DiscountService discount = mock(DiscountService.class);
        when(discount.getDiscountFor(new BigDecimal("10.00")))
                .thenReturn(new BigDecimal("50.00"));


        BigDecimal total = cart.getTotalWith(discount);


        assertMoneyEquals("0.00", total);
    }

    @Test
    public void givenNullDiscountFromService_whenApply_thenTreatedAsZero() {

        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Capa", new BigDecimal("30.00")));
        DiscountService discount = mock(DiscountService.class);
        when(discount.getDiscountFor(new BigDecimal("30.00")))
                .thenReturn(null);


        BigDecimal total = cart.getTotalWith(discount);


        assertMoneyEquals("30.00", total);
    }

    @Test
    public void givenZeroDiscount_whenApply_thenTotalEqualsSubtotal_andInteractionVerified() {

        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Adaptador", new BigDecimal("25.00")));
        DiscountService discount = mock(DiscountService.class);
        when(discount.getDiscountFor(new BigDecimal("25.00")))
                .thenReturn(BigDecimal.ZERO);


        BigDecimal total = cart.getTotalWith(discount);


        assertMoneyEquals("25.00", total);
        verify(discount).getDiscountFor(new BigDecimal("25.00"));
        verifyNoMoreInteractions(discount);
    }
}
