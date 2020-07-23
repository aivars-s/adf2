package aivars.adf.order;

import aivars.adf.common.LongIdEntity;
import aivars.adf.customer.Customer;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, exclude = {"customer", "items"})
@NamedEntityGraph(
        name = "Order.detailed",
        attributeNodes = {
                @NamedAttributeNode("customer"),
                @NamedAttributeNode(value = "items", subgraph = "subgraph.item"),
        },
        subgraphs = {
                @NamedSubgraph(name = "subgraph.item", attributeNodes = {
                        @NamedAttributeNode("product")
                })
        }
)
@NamedEntityGraph(
        name = "Order.list",
        attributeNodes = {
                @NamedAttributeNode("customer"),
                @NamedAttributeNode("items"),
                @NamedAttributeNode("itemCount"),
                @NamedAttributeNode("totalPrice")
        }
)
public class Order extends LongIdEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;

    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(item.order_id) from order_items item where item.order_id = id)")
    @Setter(AccessLevel.NONE)
    private Integer itemCount;

    @Basic(fetch = FetchType.LAZY)
    @Formula("(select sum(item.unit_price * item.amount) from order_items item where item.order_id = id)")
    @Setter(AccessLevel.NONE)
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    public void addItem(OrderItem item) {
        if (items.add(item)) {
            item.setOrder(this);
        }
    }

    public void addItems(List<OrderItem> items) {
        items.forEach(this::addItem);
    }

}
