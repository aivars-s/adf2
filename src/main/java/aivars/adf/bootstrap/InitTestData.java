package aivars.adf.bootstrap;

import aivars.adf.customer.Customer;
import aivars.adf.customer.CustomerRepository;
import aivars.adf.order.OrderCreateRequest;
import aivars.adf.order.OrderRepository;
import aivars.adf.order.OrderService;
import aivars.adf.product.Product;
import aivars.adf.product.ProductRepository;
import aivars.adf.user.User;
import aivars.adf.user.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Profile("!integration-test")
@Slf4j
public class InitTestData implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder encoder;
    private final ObjectMapper mapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final Random random;

    @Value("${application.test-data.orders.count}")
    private Integer orderCount;

    @Value("${application.test-data.orders.max-item-count}")
    private Integer maxOrderItemCount;

    @Value("${application.test-data.orders.max-item-amount}")
    private Integer maxOrderItemAmount;

    @SneakyThrows
    public InitTestData(PasswordEncoder encoder,
                        ObjectMapper mapper,
                        CustomerRepository customerRepository,
                        OrderRepository orderRepository,
                        OrderService orderService,
                        ProductRepository productRepository,
                        UserRepository userRepository
    ) {
        this.encoder = encoder;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.random = SecureRandom.getInstanceStrong();
        this.random.setSeed(1337);
    }

    @SneakyThrows
    private void initCustomers() {
        log.info("Generating test customers");
        List<Customer> customers = mapper.readValue(new ClassPathResource("data/customers.json").getInputStream(),
                new TypeReference<List<Customer>>() {});
        customerRepository.saveAll(customers);
        log.info("Generated {} test customers", customers.size());
    }

    private void initOrders() {
        log.info("Generating test orders");

        long customerCount = customerRepository.count();
        long productCount = productRepository.count();

        IntStream.range(0, orderCount).mapToObj(i -> {
            OrderCreateRequest req = new OrderCreateRequest();
            req.setCustomer(Math.abs(random.nextLong()) % customerCount + 1);

            int itemCount = random.nextInt(maxOrderItemCount) + 1;

            req.setProduct(IntStream.range(0, itemCount).mapToObj(j -> Math.abs(random.nextLong()) % productCount + 1)
                    .collect(Collectors.toList()));

            req.setAmount(IntStream.range(0, itemCount).mapToObj(j -> random.nextInt(maxOrderItemAmount) + 1)
                    .collect(Collectors.toList()));

            return req;
        }).forEach(orderService::createOrder);

        log.info("Generated {} test orders", orderRepository.count());
    }

    @SneakyThrows
    private void initProducts() {
        log.info("Generating test products");
        List<Product> products = mapper.readValue(new ClassPathResource("data/products.json").getInputStream(),
                new TypeReference<List<Product>>() {});
        productRepository.saveAll(products);
        log.info("Generated {} test products", products.size());
    }

    @SneakyThrows
    private void initUsers() {
        log.info("Generating test users");
        List<User> users = mapper.readValue(new ClassPathResource("data/users.json").getInputStream(),
                new TypeReference<List<User>>() {});
        users.forEach(user -> {
            log.info("Username: {}, password: {}, role: {}", user.getUsername(), user.getPassword(), user.getRole());
            user.setPassword(encoder.encode(user.getPassword()));
        });
        userRepository.saveAll(users);
        log.info("Generated {} test users", users.size());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initUsers();
        initCustomers();
        initProducts();
        initOrders();
    }

}
