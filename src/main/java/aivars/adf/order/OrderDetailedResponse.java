package aivars.adf.order;

import aivars.adf.customer.CustomerResponse;
import aivars.adf.product.ProductResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderDetailedResponse {

    private Long id;
    private CustomerResponse customer;
    private Instant createdAt;
    private List<OrderItemResponse> items;

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    public static class OrderItemResponse {

        private ProductResponse product;
        private Integer amount;
        private BigDecimal unitPrice;

        public BigDecimal getTotalPrice() {
            return unitPrice.multiply(BigDecimal.valueOf(amount));
        }

        public BigDecimal getProductCurrentTotalPrice() {
            return product.getUnitPrice().multiply(BigDecimal.valueOf(amount));
        }

    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(OrderItemResponse::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getProductCurrentTotalPrice() {
        return items.stream()
                .map(OrderItemResponse::getProductCurrentTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
