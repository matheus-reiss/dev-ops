package br.com.shoppingcart.core;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class ProductTest {

    private static void assertMoneyEquals(String expected, BigDecimal actual) {
        assertEquals(0, actual.compareTo(new BigDecimal(expected)));
    }

    @Test
    public void givenValidData_whenCreateProduct_thenFieldsAreSet() {
        Product p = new Product("1", "Mouse", new BigDecimal("99.90"));

        assertEquals("1", p.getId());
        assertEquals("Mouse", p.getName());
        assertMoneyEquals("99.90", p.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenBlankId_whenCreateProduct_thenThrowsException() {
        new Product(" ", "Mouse", new BigDecimal("50.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullId_whenCreateProduct_thenThrowsException() {
        new Product(null, "Mouse", new BigDecimal("50.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenBlankName_whenCreateProduct_thenThrowsException() {
        new Product("1", "   ", new BigDecimal("50.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullName_whenCreateProduct_thenThrowsException() {
        new Product("1", null, new BigDecimal("50.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullPrice_whenCreateProduct_thenThrowsException() {
        new Product("1", "Mouse", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegativePrice_whenCreateProduct_thenThrowsException() {
        new Product("1", "Mouse", new BigDecimal("-10.00"));
    }

    @Test
    public void givenIntegerPrice_whenCreateProduct_thenPriceIsNormalized() {
        Product p = new Product("1", "Mouse", new BigDecimal("10"));

        assertMoneyEquals("10.00", p.getPrice());
    }
}
