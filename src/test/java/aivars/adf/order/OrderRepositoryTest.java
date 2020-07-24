package aivars.adf.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = {"classpath:sql/customers.sql", "classpath:sql/products.sql", "classpath:sql/orders.sql"})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DirtiesContext
    public void searchRequest_default() {
        List<Long> expected = asList(1L, 2L, 3L);

        OrderSearchRequest req = new OrderSearchRequest();
        List<Long> ids = orderRepository.findAll(req.toSpecification())
                .stream().mapToLong(Order::getId).boxed().collect(toList());

        assertEquals(expected, ids);
    }

    @Test
    @DirtiesContext
    public void searchRequest_withCustomers() {
        List<Long> expected = asList(1L, 2L);

        OrderSearchRequest req = new OrderSearchRequest();
        req.setCustomer(expected);
        List<Long> ids = orderRepository.findAll(req.toSpecification())
                .stream().mapToLong(Order::getId).boxed().collect(toList());

        assertEquals(expected, ids);
    }

    @Test
    @DirtiesContext
    public void searchRequest_withProduct() {
        List<Long> expected = asList(1L, 3L);

        OrderSearchRequest req = new OrderSearchRequest();
        req.setProduct(singletonList(1L));
        List<Long> ids = orderRepository.findAll(req.toSpecification())
                .stream().mapToLong(Order::getId).boxed().collect(toList());

        assertEquals(expected, ids);
    }

}
