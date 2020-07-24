package aivars.adf.order;

import aivars.adf.product.ProductResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDetailedResponseTest {

    @Test
    public void getTotalPrice() {
        OrderDetailedResponse res = new OrderDetailedResponse();
        res.setItems(getItems());

        assertEquals(new BigDecimal("360.00"), res.getTotalPrice());
    }

    @Test
    public void getProducCurrentTotalPrice() {
        OrderDetailedResponse res = new OrderDetailedResponse();
        res.setItems(getItems());

        assertEquals(new BigDecimal("350.00"), res.getProductCurrentTotalPrice());
    }

    private List<OrderDetailedResponse.OrderItemResponse> getItems() {
        List<ProductResponse> products = getProducts();

        OrderDetailedResponse.OrderItemResponse first = new OrderDetailedResponse.OrderItemResponse();
        first.setProduct(products.get(0));
        first.setUnitPrice(new BigDecimal("90.00"));
        first.setAmount(2);

        OrderDetailedResponse.OrderItemResponse second = new OrderDetailedResponse.OrderItemResponse();
        second.setProduct(products.get(1));
        second.setUnitPrice(new BigDecimal("60.00"));
        second.setAmount(3);

        return asList(first, second);
    }

    private List<ProductResponse> getProducts() {
        ProductResponse first = new ProductResponse();
        first.setId(1L);
        first.setUnitPrice(new BigDecimal("100.00"));

        ProductResponse second = new ProductResponse();
        second.setId(2L);
        second.setUnitPrice(new BigDecimal("50.00"));

        return asList(first, second);
    }

}
