package aivars.adf.order;

import aivars.adf.customer.CustomerResponse;
import aivars.adf.product.ProductResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class OrderControllerTestUtil {

    public static CustomerResponse getCommonCustomer() {
        CustomerResponse customer = new CustomerResponse();
        customer.setId(1L);
        customer.setFirstName("First");
        customer.setLastName("Test");
        customer.setEmail("first.test@test.com");
        return customer;
    }

    public static List<OrderDetailedResponse.OrderItemResponse> getExpectedOrderItems() {
        List<ProductResponse> products = getExpectedProducts();
        ProductResponse firstProduct = products.get(0);
        ProductResponse secondProduct = products.get(1);

        OrderDetailedResponse.OrderItemResponse first = new OrderDetailedResponse.OrderItemResponse();
        first.setProduct(firstProduct);
        first.setAmount(3);
        first.setUnitPrice(firstProduct.getUnitPrice());

        OrderDetailedResponse.OrderItemResponse second = new OrderDetailedResponse.OrderItemResponse();
        second.setProduct(secondProduct);
        second.setAmount(3);
        second.setUnitPrice(secondProduct.getUnitPrice());

        return asList(first, second);
    }

    public static OrderDetailedResponse getExpectedOrder() {
        OrderDetailedResponse order = new OrderDetailedResponse();
        order.setId(1L);
        order.setCreatedAt(Instant.parse("2020-07-24T04:00:00Z"));
        order.setCustomer(getCommonCustomer());
        order.setItems(getExpectedOrderItems());

        return order;
    }

    public static List<ProductResponse> getExpectedProducts() {
        ProductResponse first = new ProductResponse();
        first.setId(1L);
        first.setBrand("Brand");
        first.setCategory("Category");
        first.setName("Test First");
        first.setUnitPrice(new BigDecimal("100.00"));

        ProductResponse second = new ProductResponse();
        second.setId(2L);
        second.setBrand("Brand");
        second.setCategory("Category");
        second.setName("Test Second");
        second.setUnitPrice(new BigDecimal("150.00"));

        return asList(first, second);
    }

    public static List<OrderResponse> getExpectedSearchOrders() {
        OrderResponse order = new OrderResponse();
        order.setId(1L);
        order.setCreatedAt(Instant.parse("2020-07-24T04:00:00Z"));
        order.setItemCount(2);
        order.setTotalPrice(new BigDecimal("750.00"));
        order.setCustomer(getCommonCustomer());

        return singletonList(order);
    }

}
