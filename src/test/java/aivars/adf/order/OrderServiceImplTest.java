package aivars.adf.order;

import aivars.adf.customer.Customer;
import aivars.adf.customer.CustomerRepository;
import aivars.adf.exception.NotFoundException;
import aivars.adf.product.Product;
import aivars.adf.product.ProductRepository;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createOrder() {
        OrderCreateRequest req = getCreateOrderRequest();

        Customer expectedCustomer = getExpectedCustomer();
        Product expectedProduct = getExpectedProduct();
        OrderItem expectedOrderItem = getExpectedOrderItem();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(expectedCustomer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));
        when(orderRepository.save(any(Order.class))).thenReturn(getExpectedOrder());

        Order order = orderService.createOrder(req);

        assertEquals(expectedCustomer, order.getCustomer());

        expectedOrderItem.setOrder(order);
        OrderItem actualOrderItem = order.getItems().stream().filter(expectedOrderItem::equals).findFirst().orElse(null);

        assertNotNull(actualOrderItem);
        assertEquals(expectedProduct, actualOrderItem.getProduct());
    }

    @Test
    public void createOrder_customerNotFound() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.createOrder(getCreateOrderRequest()));
    }

    @Test
    public void createOrder_productNotFound() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(getExpectedCustomer()));
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.createOrder(getCreateOrderRequest()));
    }

    @Test
    public void getOrder() {
        Order expected = getExpectedOrder();
        when(orderRepository.findById(anyLong(), any(EntityGraph.class))).thenReturn(Optional.of(expected));
        assertEquals(expected, orderService.getOrder(1));
    }

    @Test
    public void getOrder_notFound() {
        when(orderRepository.findById(anyLong(), any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.getOrder(1));
    }

    @Test
    public void searchOrders() {
        OrderSearchRequest req = new OrderSearchRequest();
        req.setCustomer(singletonList(1L));
        req.setProduct(singletonList(1L));

        List<Order> expected = getExpectedOrders();
        when(orderRepository.findAll(any(Specification.class), any(Sort.class), any(EntityGraph.class)))
                .thenReturn(expected);
        assertEquals(expected, orderService.searchOrders(req));
    }

    private OrderCreateRequest getCreateOrderRequest() {
        OrderCreateRequest req = new OrderCreateRequest();
        req.setCustomer(1L);
        req.setProduct(singletonList(1L));
        req.setAmount(singletonList(1));
        return req;
    }

    private Customer getExpectedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        return customer;
    }

    private Order getExpectedOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomer(getExpectedCustomer());
        order.addItems(singletonList(getExpectedOrderItem()));
        return order;
    }

    private List<Order> getExpectedOrders() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomer(getExpectedCustomer());
        return singletonList(order);
    }

    private OrderItem getExpectedOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(getExpectedProduct());
        return orderItem;
    }

    private Product getExpectedProduct() {
        Product product = new Product();
        product.setId(1L);
        return product;
    }

}
