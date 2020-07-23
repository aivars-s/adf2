package aivars.adf.order;

import aivars.adf.customer.CustomerMapper;
import aivars.adf.product.ProductMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductMapper.class})
public interface OrderMapper {

    OrderResponse toOrderResponse(Order source);
    List<OrderResponse> toOrderResponse(Iterable<Order> source);

    OrderDetailedResponse toOrderDetailedResponse(Order source);

    OrderDetailedResponse.OrderItemResponse toOrderItemResponse(OrderItem source);
    List<OrderDetailedResponse.OrderItemResponse> toOrderItemResponse(Iterable<OrderItem> source);

}
