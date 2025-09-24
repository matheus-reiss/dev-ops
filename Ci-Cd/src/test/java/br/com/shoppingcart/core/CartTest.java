package br.com.shoppingcart.core;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {


    private static void assertMoneyEquals(String expected, BigDecimal actual) {
        assertTrue("Expected " + expected + " but was " + actual,
                actual.compareTo(new BigDecimal(expected)) == 0);
    }

    @Test
    public void givenEmptyCart_whenGetTotalAndCount_thenBothAreZero() {
        Cart cart = new Cart();

        BigDecimal total = cart.getTotal();
        int count = cart.getProductCount();

        assertMoneyEquals("0.00", total);
        assertEquals(0, count);
    }

    @Test
    public void givenOneProduct_whenAdd_thenTotalAndCountUpdate() {
        Cart cart = new Cart();
        Product p1 = new Product("1", "Mouse", new BigDecimal("100"));

        cart.addProduct(p1);

        assertMoneyEquals("100.00", cart.getTotal());
        assertEquals(1, cart.getProductCount());
    }

    @Test
    public void givenTwoDifferentProducts_whenAdd_thenTotalIsSumOfPrices() {
        Cart cart = new Cart();
        Product p1 = new Product("1", "Mouse", new BigDecimal("100.00"));
        Product p2 = new Product("2", "Teclado", new BigDecimal("150.50"));

        cart.addProduct(p1);
        cart.addProduct(p2);

        assertMoneyEquals("250.50", cart.getTotal());
        assertEquals(2, cart.getProductCount());
    }

    @Test
    public void givenSameProductTwice_whenAdd_thenCountsTwiceAndTotalDoubles() {
        Cart cart = new Cart();
        Product p1 = new Product("1", "Mouse", new BigDecimal("100"));

        cart.addProduct(p1);
        cart.addProduct(p1);

        assertMoneyEquals("200.00", cart.getTotal());
        assertEquals(2, cart.getProductCount());
    }

    @Test
    public void givenExistingProduct_whenRemove_thenTotalAndCountUpdate() {
        Cart cart = new Cart();
        Product p1 = new Product("1", "Mouse", new BigDecimal("100.00"));
        Product p2 = new Product("2", "Teclado", new BigDecimal("150.50"));
        cart.addProduct(p1);
        cart.addProduct(p2);

        boolean removed = cart.removeProduct(p1);

        assertTrue(removed);
        assertMoneyEquals("150.50", cart.getTotal());
        assertEquals(1, cart.getProductCount());
    }

    @Test
    public void givenNonExistingProduct_whenRemove_thenReturnsFalseAndStateUnchanged() {
        Cart cart = new Cart();
        Product p1 = new Product("1", "Mouse", new BigDecimal("100.00"));
        Product p2 = new Product("2", "Teclado", new BigDecimal("150.50"));
        cart.addProduct(p1);

        boolean removed = cart.removeProduct(p2);

        assertFalse(removed);
        assertMoneyEquals("100.00", cart.getTotal());
        assertEquals(1, cart.getProductCount());
    }

    @Test
    public void givenCartWithItems_whenClear_thenTotalAndCountAreZero() {
        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Mouse", new BigDecimal("100.00")));
        cart.addProduct(new Product("2", "Teclado", new BigDecimal("150.50")));

        cart.clear();

        assertMoneyEquals("0.00", cart.getTotal());
        assertEquals(0, cart.getProductCount());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void givenItemsView_whenTryToModify_thenThrowsUnsupportedOperation() {
        Cart cart = new Cart();
        cart.addProduct(new Product("1", "Mouse", new BigDecimal("100.00")));

        List<Product> items = cart.getItems();
        items.add(new Product("2", "Teclado", new BigDecimal("150.50")));
    }
}
