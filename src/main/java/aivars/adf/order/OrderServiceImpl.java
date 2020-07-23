package aivars.adf.order;

import aivars.adf.common.Attributes;
import aivars.adf.common.Resource;
import aivars.adf.customer.Customer;
import aivars.adf.customer.CustomerRepository;
import aivars.adf.exception.NotFoundException;
import aivars.adf.product.Product;
import aivars.adf.product.ProductRepository;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository repository;

    @Override
    public Order createOrder(OrderCreateRequest req) {

        Customer customer = customerRepository.findById(req.getCustomer())
                .orElseThrow(() -> new NotFoundException(Resource.CUSTOMER, req.getCustomer()));

        List<OrderItem> items = new ArrayList<>();

        req.getCreateOrderItemRequests().forEach(item -> {
            Product product = productRepository.findById(item.getProduct())
                    .orElseThrow(() -> new NotFoundException(Resource.PRODUCT, item.getProduct()));
            items.add(new OrderItem(product, item.getAmount()));
        });

        Order order = new Order();
        order.setCustomer(customer);
        order.addItems(items);

        return repository.save(order);
    }

    @Override
    public Order getOrder(long id) {
        return repository
                .findById(id, EntityGraphs.named("Order.detailed"))
                .orElseThrow(() -> new NotFoundException(Resource.ORDER, id));
    }

    @Override
    public List<Order> searchOrders(OrderSearchRequest req) {
        return repository.findAll(req.toSpecification(), Sort.by(Sort.Order.desc(Attributes.CREATED_AT)),
                EntityGraphs.named("Order.list"));
    }

}
