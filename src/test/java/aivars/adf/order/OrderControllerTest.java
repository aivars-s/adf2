package aivars.adf.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import static aivars.adf.order.OrderControllerTestUtil.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderMapper mapper;

    @Mock
    private OrderService orderService;

    @Test
    public void searchOrders() {
        OrderSearchRequest req = new OrderSearchRequest();

        when(orderService.searchOrders(any(OrderSearchRequest.class)))
                .thenReturn(singletonList(new Order()));

        when(mapper.toOrderResponse(any(Iterable.class))).thenReturn(getExpectedSearchOrders());

        assertEquals("search", controller.searchOrders(req, new ConcurrentModel()));
    }

    @Test
    public void getOrder() {
        when(orderService.getOrder(anyLong())).thenReturn(new Order());
        when(mapper.toOrderDetailedResponse(any(Order.class))).thenReturn(getExpectedOrder());

        assertEquals("details", controller.getOrder(1, new ConcurrentModel()));
    }

    @Test
    public void getOrderForm() {
        assertEquals("create", controller.getOrderForm(new ConcurrentModel()));
    }

    @Test
    public void postOrderForm() {
        OrderCreateRequest req = new OrderCreateRequest();
        req.setCustomer(1L);
        req.setProduct(singletonList(1L));
        req.setAmount(singletonList(1));

        Order res = new Order();
        res.setId(1L);

        when(orderService.createOrder(any())).thenReturn(res);

        assertEquals("redirect:/orders/1", controller.postOrderForm(req, new ConcurrentModel()));
    }

}
