package aivars.adf.order;

import aivars.adf.common.BaseEntity;
import aivars.adf.product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, exclude = {"order", "product"})
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderItemId id = new OrderItemId();

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    public OrderItem(Product product, int amount) {
        setProduct(product);
        this.amount = amount;
        this.unitPrice = product.getUnitPrice();
    }

    public void setOrder(Order order) {
        this.order = order;
        id.setOrderId(order.getId());
    }

    public void setProduct(Product product) {
        this.product = product;
        this.id.setProductId(product.getId());
    }

}
