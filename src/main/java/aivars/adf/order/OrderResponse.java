package aivars.adf.order;

import aivars.adf.customer.CustomerResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderResponse {

    private Long id;
    private Instant createdAt;
    private CustomerResponse customer;
    private Integer itemCount;
    private BigDecimal totalPrice;

}
