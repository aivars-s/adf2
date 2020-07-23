package aivars.adf.order;

import aivars.adf.customer.CustomerResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private Instant createdAt;
    private CustomerResponse customer;
    private Integer itemCount;
    private BigDecimal totalPrice;

}
