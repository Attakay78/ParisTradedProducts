import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParisTradedProductTest {
    ProductPricingService productPricingService;
    Product product1;
    Product product2;
    Product product3;
    ParisTradedProductsImplementation parisTradedProductsImplementation = new ParisTradedProductsImplementation();

    @BeforeEach
    void setup(){
        productPricingService = mock(ProductPricingService.class);
        when(productPricingService.price(anyString(), anyString())).thenReturn(3.5);
        when(productPricingService.price(anyString(), anyString(), anyInt(), anyInt())).thenReturn(5.0);
        when(productPricingService.price(anyString(), anyString(), anyInt())).thenReturn(6.1);

        product1 = mock(Product.class);
        when(product1.getId()).thenReturn("1");

        product2 = mock(Product.class);
        when(product2.getId()).thenReturn("2");
    }

    @Test
    void testAddProduct() throws ProductAlreadyRegisteredException {
        parisTradedProductsImplementation.addNewProduct(product1);
        assertThrows(ProductAlreadyRegisteredException.class, ()-> parisTradedProductsImplementation.addNewProduct(product1));
    }

    @Test
    void testTrade() throws ProductNotRegisteredException {
        assertThrows(ProductNotRegisteredException.class, () -> parisTradedProductsImplementation.trade(product3, 23));
    }

    @Test
    void testTotalTradeQuantityForDay() throws ProductAlreadyRegisteredException, ProductNotRegisteredException {
        parisTradedProductsImplementation.addNewProduct(product1);
        parisTradedProductsImplementation.trade(product1, 6);

        parisTradedProductsImplementation.addNewProduct(product2);
        parisTradedProductsImplementation.trade(product2, 10);

        assertEquals(16, parisTradedProductsImplementation.totalTradeQuantityForDay());
    }

    @Test
    void testTotalValueOfDaysTradedProducts() throws ProductAlreadyRegisteredException, ProductNotRegisteredException {
        Product amazonStock = new Stock("AMZN", "NASDAQ", "11", productPricingService);
        parisTradedProductsImplementation.addNewProduct(amazonStock);
        parisTradedProductsImplementation.trade(amazonStock, 10);

        Product crudeOil = new Future("NASDAQ", "CL",10, 2021, "12", productPricingService);
        parisTradedProductsImplementation.addNewProduct(crudeOil);
        parisTradedProductsImplementation.trade(crudeOil, 5);

            Product tesla = new Option("TSLA", "NASDAQ", 30,"13", productPricingService);
        parisTradedProductsImplementation.addNewProduct(tesla);
        parisTradedProductsImplementation.trade(tesla, 20);

        assertEquals(182, parisTradedProductsImplementation.totalValueOfDaysTradedProducts());
    }
}
