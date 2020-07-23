package aivars.adf.order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderCreateRequest req);
    Order getOrder(long id);
    List<Order> searchOrders(OrderSearchRequest req);

}
