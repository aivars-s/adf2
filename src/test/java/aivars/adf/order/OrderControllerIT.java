package aivars.adf.order;

import static aivars.adf.order.OrderControllerTestUtil.getExpectedOrder;
import static aivars.adf.order.OrderControllerTestUtil.getExpectedSearchOrders;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import aivars.adf.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:sql/customers.sql", "classpath:sql/products.sql", "classpath:sql/orders.sql"})
public class OrderControllerIT extends BaseIntegrationTest {

    @Test
    @DirtiesContext
    @SneakyThrows
    public void searchOrders() {
        OrderSearchRequest expectedForm = new OrderSearchRequest();
        expectedForm.setCustomer(singletonList(1L));

        mockMvc.perform(get("/orders")
                .queryParam("customer", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attribute("form", expectedForm))
                .andExpect(model().attribute("orders", getExpectedSearchOrders()));
    }

    @Test
    @DirtiesContext
    @SneakyThrows
    public void getOrder() {
        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attribute("order", getExpectedOrder()));
    }

    @Test
    @DirtiesContext
    @SneakyThrows
    public void getOrderForm() {
        mockMvc.perform(get("/orders/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attribute("order", new OrderCreateRequest()));
    }

    @Test
    @DirtiesContext
    @SneakyThrows
    public void postOrderForm() {
        mockMvc.perform(post("/orders/new")
                .queryParam("customer", "1")
                .queryParam("product", "1")
                .queryParam("amount", "2"))
                .andExpect(redirectedUrl("/orders/4"));
    }

}
